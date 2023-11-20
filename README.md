# ReCharger

## Local development
- `./gradlew clean build`
- `docker-compose up -d`
  - will start MySQL database
- `./gradlew boot run` or just start the main Spring class

## Google Cloud deployment
Currently - this app is deployed to google cloud as project [plucky-haven-400220](https://plucky-haven-400220.lm.r.appspot.com/swagger-ui/index.html)

To deploy, you need to change identifiers and credentials inside [application-gcp.yml](module-server/src/main/resources/application-gcp.yml) file:
```yaml
spring:
  datasource:
    url: "jdbc:mysql:///<SCHEMA_NAME>?cloudSqlInstance=<CONNECTION_NAME>&socketFactory=com.google.cloud.sql.mysql.SocketFactory"
    username: // if other then "root"
    password: // if configured
```

and then run `./gradlew :module-server:appengineDeploy`

### Database port-forward
If you need to connect to DB in GCloud, use

`cloud-sql-proxy --port <PORT> <CONNECTION_NAME>`

## Security (OAuth)
App is built for users authorized via Google Login. For that you need to create (or use existing) OAuth Credentials
in Google Cloud Console. Then just add `client-id` and `client-secret` into [application.yml](module-server/src/main/resources/application.yml):
```yaml
spring:
#  ...
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: "<GOOGLE_CLIENT_ID>"
            client-secret: "<GOOGLE_CLIENT_SECRET>"
```
