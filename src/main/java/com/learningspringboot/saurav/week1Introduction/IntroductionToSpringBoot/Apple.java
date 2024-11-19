package com.learningspringboot.saurav.week1Introduction.IntroductionToSpringBoot;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
public class Apple {


    void eatApple(){
        System.out.println("I am eating apple");
    }

    @PostConstruct
    void doBeforeEatingApple(){
        System.out.println("Creating the Apple Bean");
    }

    @PreDestroy
    void doAfterEatingApple(){
        System.out.println("Destroying the Apple Bean");
    }
}
