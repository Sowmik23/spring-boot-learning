package com.sowmik.springboot_api.batch.CSVtoDB.model;


import com.sowmik.springboot_api.batch.CSVtoDB.model.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.security.PublicKey;

@Configuration
public class BatchConfigCSV {

    //step-03 : configure step and job
    @Autowired
    private StepBuilderFactory sbf2;

    @Autowired
    private JobBuilderFactory jbf2;

    @Bean
    public Job job2(){
        return jbf2.get("job2").incrementer(new RunIdIncrementer())
                .start(step2())
                .build();
    }

    @Bean
    public Step step2(){
        return sbf2.get("step2").<Product, Product>chunk(3)
                .reader(reader2())
                .processor(processor2())
                .writer(writer2())
                .build();
    }


    //step-01: write reader, processor and writer
    @Bean
    public ItemReader<Product> reader2(){
        FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("products.csv"));

        DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setNames("id", "name", "description", "price");
        BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Product.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader.setLineMapper(lineMapper);

        return reader;
    }

    @Bean
    public ItemProcessor<Product, Product> processor2(){
        //this is an arrow function
        //here we applied 10% discount for each product.
        return (product -> {
            product.setPrice(product.getPrice() - product.getPrice() * 10 / 100);
            return product;
        });
    }

    @Bean
    public ItemWriter<Product> writer2(){
        JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource());

        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Product>());
        writer.setSql("INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, PRICE) VALUES (:id, :name, :description, :price");

        return writer;
    }

    //step-02: configure the datasource
    //configure the datasource
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/showmikdb");
        ds.setUsername("root");
        ds.setPassword("12345678");

        return ds;
    }
}
