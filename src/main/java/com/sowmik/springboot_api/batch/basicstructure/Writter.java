package com.sowmik.springboot_api.batch.basicstructure;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class Writter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        System.out.println("Inside Writer");
        System.out.println("Writing data: "+list);
    }
}
