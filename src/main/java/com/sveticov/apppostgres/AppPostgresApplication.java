package com.sveticov.apppostgres;

import com.sveticov.apppostgres.mytest.myTestClass;
import com.sveticov.apppostgres.mytest.myTestInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppPostgresApplication {



    public static void main(String[] args) {
        SpringApplication.run(AppPostgresApplication.class, args);


    }


}
