package com.sowmik.springboot_api;

import com.sowmik.springboot_api.data_jpa.entities.Product;
import com.sowmik.springboot_api.data_jpa.entities.Student;
import com.sowmik.springboot_api.data_jpa.repository.StudentRepository;
import com.sowmik.springboot_api.messaging.MessageSender;
import com.sowmik.springboot_api.services.PaymentService;
import com.sowmik.springboot_api.services.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SowmikApplicationTests {

    /*
        These all are not unit testing...
        These are integration test.

     */





    //test here if our dependency injection works or not
    @Autowired
    PaymentService paymentService; //test 01

    @Autowired
    PaymentServiceImpl service; //test 02

    @Test
    void testDependencyInjection() {
        assertNotNull(paymentService);  //test 01
        assertNotNull(service.getDao()); //test 02
    }
    //now run this class and check output if our dependency test pass or not.


    //Again test Spring data jpa: Student Entity and StudentRepository
    @Autowired
    private StudentRepository repository;

//    @Test
//    public void testSaveStudent(){
//        Student student = new Student();
//        student.setId(1L);
//        student.setName("Sowmik Sarker");
//        student.setScore(27);
//        repository.save(student);
//
//        Student savedStudent = repository.findById(1L).get();
//        assertNotNull(savedStudent);
//    }


    //Section 7: Creating a REST Client

    @Value("${api.service.url}")
    private String baseUrl;

    @Test
    public void testGetProduct(){
       // System.out.println(baseUrl);
        RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject(baseUrl+"6", Product.class);
        assertNotNull(product);
        assertEquals("Spring Boot", product.getName());
    }

    @Test
    public void testCreateProduct(){
        RestTemplate restTemplate = new RestTemplate();
        Product product = new Product();

        product.setName("Sowmik Sarker");
        product.setDescription("Some description about this product...");
        product.setPrice(123212);

        Product newProduct = restTemplate.postForObject("http://localhost:8090/api/products", product, Product.class);

        assertNotNull(newProduct);
        assertNotNull(newProduct.getId());
        assertEquals("Sowmik Sarker", newProduct.getName());
    }

    @Test
    public void testUpdateProduct(){
        RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject(baseUrl+"10", Product.class);
        product.setName("JavaScript");
        restTemplate.put(baseUrl, product);
    }

    @Autowired
    JobLauncher launcher;

    @Autowired
    Job job;

    @Test
    public void testBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        launcher.run(job, jobParameters);
    }


    @Test
    public void testBatchConfigCSV() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        launcher.run(job, jobParameters);
    }


//    Test JMS messaging
    @Autowired
    MessageSender sender;

    @Test
    public void testSendAndReceiveJMSMessaging(){
        sender.send("Hello Spring JMS!");
    }


}
