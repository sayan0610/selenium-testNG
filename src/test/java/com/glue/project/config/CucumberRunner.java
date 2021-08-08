package com.glue.project.config;


import com.glue.project.AppConfig;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = AppConfig.class)
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = { },
        glue = {"com.glue.project"},
        plugin = {"pretty"},
        monochrome = true
)

public class CucumberRunner {
}
