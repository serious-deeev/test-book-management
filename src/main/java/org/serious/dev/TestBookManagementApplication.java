package org.serious.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * ConfigurationPropertiesScan автоматически сканирует и регистрирует классы,
 * помеченные @ConfigurationProperties,
 * без необходимости вручную аннотировать их @Component или указывать в @EnableConfigurationProperties.
 */
@ConfigurationPropertiesScan
@SpringBootApplication
public class TestBookManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestBookManagementApplication.class, args);
    }
}
