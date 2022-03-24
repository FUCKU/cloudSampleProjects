Question: Spring cloud config server on refresh -> logback config springProperty can not get.

Project configuration is strictly regulated. We put some configs in project common,and others services rely on  project common

## How to run these app

1. to start a configServer, docker file in project spring-cloud-sample-cloud-configs

    run command `docker-compose up`

2. modify bootstrap.yml in project spring-cloud-sample-common

    need to modify `bootstrap.yml` if you don't run configServer locally  

3. startup project spring-cloud-sample-demo-service

## Problems that need to be reproduced
Call the endpoint address 'http://ip+port/actuator/refresh' to refresh the properties, and you can see the following log in the console

For example:

application_name_IS_UNDEFINED ::: 2022-03-24 13:18:00.607  INFO [,307c860d8a5d55b8,307c860d8a5d55b8] [xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx] 18048 --- [   scheduling-1] c.c.c.ConfigServicePropertySourceLocator : Fetching config from server at : http://localhost:8888

application_name_IS_UNDEFINED is not right, the logs do not get the right value.

When application spring-cloud-sample-demo-service startup ,the logs in console is right ,we can see the right application name,but after call the refresh endpoint ,the logs changed and can not see the right application name.

In `logback-spring.xml` anything like <springProperty scope="context" ....../> seems does not get the value correctly.

```
Versions:
<spring-boot.version>2.5.10</spring-boot.version>
<spring-cloud.version>2020.0.5</spring-cloud.version>
```

## One More Question
when app startup I saw the logs:

``
cloud-sample-admin-config-service ::: 2022-03-24 13:35:31.413  INFO [,,] [xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx] 13916 --- [           main] c.c.c.ConfigServicePropertySourceLocator : Fetching config from server at : http://localhost:8888
cloud-sample-admin-config-service ::: 2022-03-24 13:35:35.014  INFO [,,] [xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx] 13916 --- [           main] c.c.c.ConfigServicePropertySourceLocator : Located environment: name=cloud-sample-admin-config-service, profiles=[dev], label=main, version=83224a4f63f26058eddb27b1f048e85f840f252e, state=null
cloud-sample-admin-config-service ::: 2022-03-24 13:35:35.016  INFO [,,] [xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx] 13916 --- [           main] b.c.PropertySourceBootstrapConfiguration : Located property source: [BootstrapPropertySource {name='bootstrapProperties-configClient'}, BootstrapPropertySource {name='bootstrapProperties-https://github.com/FUCKU/cloudSample/dev/cloud-sample-admin-config-service.yml'}, BootstrapPropertySource {name='bootstrapProperties-https://github.com/FUCKU/cloudSample/dev/application.yml'}]
cloud-sample-admin-config-service ::: 2022-03-24 13:35:35.044  INFO [,,] [xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx] 13916 --- [           main] com.cloud.sample.ServiceApplication      : The following 2 profiles are active: "dev", "api-docs"

``


But when we call the refresh endpoint, the logs as follows:
```
application_name_IS_UNDEFINED ::: 2022-03-24 13:36:10.555  INFO [,4fb57d6c34dd0612,4fb57d6c34dd0612] [xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx] 13916 --- [nio-8080-exec-1] c.c.c.ConfigServicePropertySourceLocator : Fetching config from server at : http://localhost:8888
application_name_IS_UNDEFINED ::: 2022-03-24 13:36:11.610  INFO [,4fb57d6c34dd0612,4fb57d6c34dd0612] [xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx] 13916 --- [nio-8080-exec-1] c.c.c.ConfigServicePropertySourceLocator : Located environment: name=application, profiles=[dev,api-docs], label=main, version=83224a4f63f26058eddb27b1f048e85f840f252e, state=null
application_name_IS_UNDEFINED ::: 2022-03-24 13:36:11.610  INFO [,4fb57d6c34dd0612,4fb57d6c34dd0612] [xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx] 13916 --- [nio-8080-exec-1] b.c.PropertySourceBootstrapConfiguration : Located property source: [BootstrapPropertySource {name='bootstrapProperties-configClient'}, BootstrapPropertySource {name='bootstrapProperties-https://github.com/FUCKU/cloudSample/dev/application.yml'}]
application_name_IS_UNDEFINED ::: 2022-03-24 13:36:11.632  INFO [,4fb57d6c34dd0612,4fb57d6c34dd0612] [xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx] 13916 --- [nio-8080-exec-1] o.s.boot.SpringApplication               : The following 2 profiles are active: "dev", "api-docs"
application_name_IS_UNDEFINED ::: 2022-03-24 13:36:11.640  INFO [,4fb57d6c34dd0612,4fb57d6c34dd0612] [xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx] 13916 --- [nio-8080-exec-1] o.s.boot.SpringApplication               : Started application in 1.646 seconds (JVM running for 44.815)

```

Is this a bug? Or what exactly am I going to do?

I tried several schemes, including changing the logback-spring.xml file to something else and loading it in the application.yml file, but nothing worked.