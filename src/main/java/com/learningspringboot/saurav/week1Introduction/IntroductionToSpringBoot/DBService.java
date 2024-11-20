package com.learningspringboot.saurav.week1Introduction.IntroductionToSpringBoot;

import org.springframework.stereotype.Service;

@Service
public class DBService {
    final DB db;

    public DBService(DB db){
        this.db=db;
    }

    String getData(){
        return db.getData();
    }
}
