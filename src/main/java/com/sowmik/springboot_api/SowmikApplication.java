package com.sowmik.springboot_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SowmikApplication {

    public static void main(String[] args) {
        SpringApplication.run(SowmikApplication.class, args);
    }

}


// Topics:



/*

9. How to use Logging/logger?
    - see productRestController: here in getProductById we implemented that.

    - By default all logs are shown in console but how can we save these logs in a file?
    - set logging.file=logs/application.log

    - How to control log level(debug, error, fatal, info, off, trace, warn)?
    - set logging.level.root=error (suppose we want to see only errors)

Section 10: Health Checks and Metrics
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


 */

