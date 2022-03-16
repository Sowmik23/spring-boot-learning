package com.sowmik.springboot_api.batch.basicstructure;

import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<String, String> {
    @Override
    public String process(String s) throws Exception {
        System.out.println("Inside process");
        return "PROCESSED "+s.toUpperCase();
    }
}
