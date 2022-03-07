package com.sowmik.springboot_api.data_jpa.entities;


import javax.persistence.Entity;
import javax.persistence.Id;

//this is a simple java bean . To make if jpa entity, we mark this with @Entity annotations
@Entity
public class Student {

    //primary key. @Entity and @Id are mandatory annotations on every jpa entity.
    @Id
    private long id;
    private String name;
    private int score;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
