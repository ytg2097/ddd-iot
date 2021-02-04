
# DDD-IOT  

DDD的多租户IOT平台后台.

待完善...

## 涉及技术栈
- spring cloud gateway
- nacos
- seata
- feign
- [spring-coud-quick-starters](https://github.com/ytg2097/spring-coud-quick-starters)

## 服务介绍

**api后缀为服务的feign包, 供及其他服务集成, impl后缀为服务的实际执行者**

- api-gateway-platform
> 网关层. 采用oauth2认证, 使用spring cloud gateway 更加灵活高效的路由

- log-recorder
> 基础设施. 为其他服务提供的异步注解式操作日志收集, 通过kafka发送给service-log

- service-log
> 日志收集与处理

- service-tenant
> 租户服务. 

- service-device
> 提供自定义设备模型功能. 抽象了iot设备常见的各种行为, 如下行命令, 上行数据等.
> 提供规则引擎定义功能. 规则引擎暂时为模拟sql形式, 后续会改为drools
> 基础的设备分组, 设备标签等服务

- service-datahub
> 收集设备数据, 通过规则引擎的流转触发各种操作, 如短信通知, 站内消息, 反向命令控制等

- service-backed-config
> 平台的后台配置服务, 如租户套餐, 资源配置等


**服务暂时先合并到一起, 方便单人开发调试**

##Docker部署

#### 1. 安装docker环境与docker-compose

#### 2. 运行基础设施容器

1. 创建网络

        docker network create -d bridge ac-cloud

        
2. mysql

         docker run -p 3307:3306 --name mysql \
         -v /dockerdata/mysql/log:/var/log/mysql \
         -v /dockerdata/mysql/conf/my.cnf:/etc/mysql/my.cnf \
         -v /dockerdata/mysql/data:/var/lib/mysql \
         -v /dockerdata/mysql/backup:/home
         -e MYSQL_ROOT_PASSWORD=root \
         --restart=always \ 
         -d mysql:8.0.19
         
         docker run -p 3306:3306 --name mysql \
                  -v /docker/mysql/log:/var/log/mysql \
                  -v /docker/mysql/conf/my.cnf:/etc/mysql/my.cnf \
                  -v /docker/mysql/data:/var/lib/mysql \
                  -e MYSQL_ROOT_PASSWORD=root \
                  --restart=always \ 
                  -d mysql:8.0.19  
         
 docker run -p 3306:3306 --name mysql  -v F:/docker/mysql/log:/var/log/mysql  -v F:/docker/mysql/conf/my.cnf:/etc/mysql/my.cnf   -v F:/docker/mysql/data:/var/lib/mysql     -v F:/docker/mysql/backup:/home  -e MYSQL_ROOT_PASSWORD=root    --restart=always   -d mysql:8.0.19
            
        
          mysql -u root -p 
          use mysql; 
          update user set host = '%' where user = 'root';
          -- ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'root'; 
          select user,host,plugin from user;
          flush privileges;


3.  redis

    
        docker run -p 6380:6379 --name redis \
        --restart=always \ 
        -v /dockerdata/redis/data:/data \
        -v /dockerdata/redis/conf/redis.conf:/etc/redis/redis.conf \
        -d redis redis-server /etc/redis/redis.conf
        
                docker run -p 6379:6379 --name redis \
                --restart=always \ 
                -v /docker/redis/data:/data \
                -v /docker/redis/redis.conf:/etc/redis/redis.conf \
                -d redis redis-server /etc/redis/redis.conf
                
                docker run \
                -p 6379:6379 \
                -v /docker/redis/data:/data:rw \
                -v /docker/redis/redis.conf:/etc/redis/redis.conf:ro
                --privileged=true \
                --name myredis \
                -d redis redis-server /etc/redis/redis.conf 
        
          docker run -p 6379:6379 --name redis  --restart=always -v /docker/redis/data:/data  -v /docker/redis/redis.conf:/etc/redis/redis.conf  -d redis redis-server /etc/redis/redis.conf
       
3. nginx


        docker run --name nginx -p 9527:80 --restart=always  -v /dockerdata/nginx/conf/nginx.conf:/etc/nginx/nginx.conf -v /dockerdata/nginx/html:/usr/share/nginx/html -v /dockerdata/nginx/logs:/var/log/nginx -v /dockerdata/sharefile:/home/sharefile -d nginx
        docker run --name nginx -p 80:80 --restart=always  -v /docker/nginx/nginx.conf:/etc/nginx/nginx.conf -v /docker/nginx/html:/usr/share/nginx/html -v /docker/nginx/logs:/var/log/nginx  -d nginx
        
3. tomcat

docker run -d --name tomcat -p 8080:8080 -v /dockerdata/tomcat/webapps:/usr/local/tomcat/webapps -v /dockerdata/tomcat/logs:/usr/local/tomcat/logs -v /dockerdata/sharefile:/home/sharefile tomcat


4. nacos注册中心

        添加nacos_config数据库  运行db.sql
        docker run -d --name nacos -p 8848:8848 -e MODE=standalone -e MYSQL_SERVICE_HOST=192.168.120.25 -e MYSQL_SERVICE_PORT=3307 -e MYSQL_SERVICE_DB_NAME=nacos_config -e MYSQL_SERVICE_USER=root -e MYSQL_SERVICE_PASSWORD=root -e SPRING_DATASOURCE_PLATFORM=mysql -e MYSQL_DATABASE_NUM=1 nacos/nacos-server:latest

    
5. seata-sevrer 分布式事务

        1. 
               添加seata数据库  运行server.sql
                  docker run -d --name seata -p 8091:8091 -e SEATA_IP=192.168.120.25 -e SEATA_PORT=8091 -v /dockerdata/seata/registry.conf:/seata-server/resources/registry.conf ytg2097/seata:1.4.0
               
               运行nacos-config.sh 上传config.txt到nacos
               
           
        2.注意事项  
             
                + 分支事务异常被自己的ControllerAdvice捕获后, seata无法捕获异常, 不会触发全局事务回滚, 需要调用GlobalTransactionContext.reload(RootContext.getXID()).rollback();手动回滚
                + 涉及到分布式事务的方法要注意在本地事务提交之后再进行分支事务调用  否则会发生Unable to commit against JDBC Connection
                
                    示例:
                    ```java
                        /**
                             *  删除本地数据
                             *  同时删除租户服务中的关联菜单
                             * @param id
                             */
                            @GlobalTransactional
                            public void delete(String id){
                        
                                menuService.delete(id);
                        
                                RestResult restResult = roleClient.clearInvalidMenu(id);
                        
                                if (ResultAssert.assertFail(restResult)){
                                    try {
                                        GlobalTransactionContext.reload(RootContext.getXID()).rollback();
                                    } catch (TransactionException e) {
                                        log.info(e.getMessage());
                                    }
                                }
                            }
                    ```     
      
   
#### 运行各个服务  












