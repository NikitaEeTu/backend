package com.bettingjaws.backend;

import com.bettingjaws.backend.configuration.H2JpaConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BackendApplication.class, H2JpaConfig.class})
public class IntegrationTest {
    @Test
    void testH2Db(){

    }
}
