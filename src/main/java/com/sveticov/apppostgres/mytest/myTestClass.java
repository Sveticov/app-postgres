package com.sveticov.apppostgres.mytest;

import org.springframework.stereotype.Service;

@Service
public class myTestClass implements myTestInterf {
    public myTestClass() {
    }

    @Override
    public void next() {
        System.out.println("test "+this.getClass().getName());
    }
}
