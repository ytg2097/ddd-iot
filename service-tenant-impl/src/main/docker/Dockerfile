FROM java

VOLUME /data/logs

ADD /service-tenant.jar app.jar

ENTRYPOINT ["./wait-for-it.sh","java" , "-jar" , "app.jar"]

