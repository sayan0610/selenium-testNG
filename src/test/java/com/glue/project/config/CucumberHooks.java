package com.glue.project.config;

import com.glue.project.AppConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = AppConfig.class)
public class CucumberHooks {

    @Before
    public void before(){
        System.out.println("Running before test");
    }

    @After
    public void after(){
        System.out.println("Running after test");
    }

}
