package br.com.ada.orders.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("classpath:data.sql")
    private Resource dataScript;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        executeScript(dataScript);
    }

    private void executeScript(Resource script) {
        try {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator(script);
            populator.execute(jdbcTemplate.getDataSource());
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute script: " + script, e);
        }
    }
}