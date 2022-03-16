package com.sowmik.springboot_api.batch.basicstructure;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<String> {

    private String [] courses = {"C", "C++", "Java", "Python", "Spring", "Spring Boot", "React", "Node"};
    private int cnt = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("Inside read");
        if(cnt<courses.length) return courses[cnt++];
        else cnt = 0;
        return null;
    } //here generic type is string as will will return, process and take string data
}
