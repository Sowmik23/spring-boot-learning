package com.sowmik.springboot_api.batch.basicstructure;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    //Step
    @Autowired
    private StepBuilderFactory sbf;

    @Bean
    public Step step(){
        return sbf.get("step1")
                .<String, String>chunk(3)
                .reader(reader())
                .processor(processor())
                .writer(writter())
                .build();
    }

    @Autowired
    private JobBuilderFactory jbf;

    @Bean
    public Job job(){
        return jbf.get("job1")
                .incrementer(new RunIdIncrementer())
                .listener(myJobListener())
                .start(step())
                .build();
    }



    @Bean
    public Reader reader(){
        return new Reader();
    }

    @Bean
    public Writter writter(){
        return new Writter();
    }

    @Bean
    public Processor processor(){
        return new Processor();
    }

    @Bean
    public MyJobListener myJobListener(){
        return new MyJobListener();
    }
}
