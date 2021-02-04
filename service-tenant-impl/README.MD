#### 打包命令

---
    
    mvn clean install docker:build -P${local or dev or test or prod}

---

#### docker运行命令

---
    单独启动时要注意服务依赖
    docker run  -d --name service-tenant \ 
    -p 8760:8760 \
    -v F:/logs:/data/logs ac-cloud/service-tenant:${project.version}
---
