package org.serious.dev.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "app.book-pagination")
public class PaginationProperties {

    private final int pageSize;

    public PaginationProperties(int pageSize) {
        this.pageSize = pageSize;
    }
}
