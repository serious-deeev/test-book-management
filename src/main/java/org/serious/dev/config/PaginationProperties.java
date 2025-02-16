package org.serious.dev.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.book-pagination")
public class PaginationProperties {

    private int pageSize;
}
