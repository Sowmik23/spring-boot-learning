Section 0: Basics

        - Inversion of Control
        - Dependency Injection

Section 1: Spring Data JPA

        - Adding JPA dependency
        - Creating Model and Repository
        - Testing
        - Show SQL

Section 2: Create REST CRUD API

        - getAllProducts()
        - getProduct()
        - createProduct()
        - deleteProduct()


Section 3: Creating a REST Client

        - integration test @Test


Section 4: Profiles

        - dev profile
        - testing profile
        - activate profile
        - activate profile through VM

    9. How to use Logging/logger?

        - see productRestController: here in getProductById we implemented that.

        - By default all logs are shown in console but how can we save these logs in a file?
        - set logging.file=logs/application.log

        - How to control log level(debug, error, fatal, info, off, trace, warn)?
        - set logging.level.root=error (suppose we want to see only errors)
        - SEE ProductRestController, where I set logger for specific action.
        - see application.properties

        TODO: LOG-BACK PATTERN

Section 5: Health Checks and Metrics

        - How to know our application is ready for production?
        => It includes(Health checks, Application configuration, Application metrics, Key application events)
        ---> All is we need to do, enable Spring Boot Actuators for our project in pom.xml.
        - Once we do that, spring boot will expose out several restful endpoints using which we can access the health
        and metrics for our application.

        ===> To see all the health related information/all the health related endpoint/actuator related endpoint see
        - http://localhost:8090/api/actuator


     ==> Expose out more information about health of our application
        - just add this line in application.properties
        -> management.endpoint.health.show-details=always
        - and now see all the details here: http://localhost:8090/api/actuator/health

        +How to add build related info of the project?
        => go to pom.xml and in plugin section add this:
        <executions>
            <execution>
                <goals>
                    <goal>build-info</goal>
                </goals>
            </execution>
        </executions>

        see more info related to project health in this link:
        http://localhost:8090/api/actuator/info/

        => maybe you can not access build info using above url . TO see info you need to add below line in application.properties
        => management.endpoints.web.exposure.include=*

        - you can now see all the endpoints of actuator : here: http://localhost:8090/api/actuator/
        now test the above url's links


Section 6: Spring Security

        - add spring-boot-starter-security in pom.xml then spring boot will automatically add security.
        - then for login use username: user and password will be printed in log info.
        
        TODO: JWT-AUTHENTICATION IMPLEMENT


Section 7: Thymeleaf

        - add spring-boot-starter-thymeleaf in pom.xml.
        - sending data (see HelloController.java)
        - sending object data (see HelloController.java)


Section 8: Database Caching

        - Spring boot uses third party cash providers like Hazel cast, EH cache, JBoss cache. Hazel cast is most popular.
        - add spring-boot-starter-cache in pom.xml
        - create ProductCacheConfig
        - add @EnableCaching in main class that means here in this class above
        - enable cache in ProductRestController class
        - Product Model class implements Serializable

        TODO: TEST CACHING...


Section 9: Spring Batch

        - A Batch is a bunch of task
        - in properties file add:

        spring.batch.jdbc.initialize-schema=always
        spring.main.allow-circular-references=true

        @EnableBatchProcessing

        add: spring.batch.job.enabled=false in application.properties.

        ::Task: Read CSV to Database: read row by row and convert it to object.

        TODO: TEST FOR LARGE ZIP/VIDEO/AUDIO FILE
        //https://stackoverflow.com/questions/36263965/processing-a-large-file-using-spring-batch


Section 10: Unit Testing using MockMvc

        - Mockito
        - ProductRestControllerMvcUnitTest

        TODO: ENUM IN JAVA
        TODO: HOW TO MANAGE VERSION COMPATIBILITY FOR DIFFERENT THIRD PARTY LIBRARIES IN SPRING BOOT (POM.XML)


Section 11: Messaging and Spring JMS

        - Install ActiveMQ on you pc and start listening...
        - in pom.xml add
         <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-activemq</artifactId>
         </dependency>

        - create message sender
        - create message listener
        - write a test
        - enable @EnableJms in main function
        - configure some properties in application.properties
            springjms.myQueue=myQueue
            spring.activemq.broker-url=tcp://localhost:61616
            spring.activemq.user=admin
            spring.activemq.password=admin


Section 12: Swagger REST Documentation Quickstart

        - add dependeny in pom.xml
            <!--Dependency for swagger and swagger-ui in one dependency-->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-ui</artifactId>
            <version>1.6.6</version>
        </dependency>

        - Run the project
        - goto this url: localhost:8090/api/v3/api-docs
        - goto another url: localhost:8090/api/swagger-ui.html (beautiful ui)

        - you can customize the swagger-ui url by changing it in application .properties
        - like: springdoc.swagger-ui.path=/swagger-ui.html

        - For other Swagger Annotations....
        - use @OpenAPIDefinition  in man method

         - TODO: Advance configuration : read from Migration springfox documentation

Section-18: Validations
  - validation-api : implementation is provided by hibernate-validator
  - use spring-boot-starter-validation(the only dependency we need to add in pom.xml
  - Some validation annotations: @AssertFalse, @AssertTrue, @Past, @Future, @Max, @Min, @Size,
  - @NotNull, @Pattern
  - 1st step: add spring-boot-starter-validation in pom.xml
  - 2nd step: ADD @Valid in ProductRestController
  - 3rd step: goto Product Entity and and validation notations


Section-19: REST File Upload and Download