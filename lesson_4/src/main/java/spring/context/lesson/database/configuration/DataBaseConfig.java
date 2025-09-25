package spring.context.lesson.database.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class DataBaseConfig {

    @Getter
    private final HikariDataSource dataSource;

    public DataBaseConfig(@Value("${db.databaseUrl}") String databaseUrl,
                          @Value("${db.username}") String username,
                          @Value("${db.password}") String password,
                          @Value("${db.diver}") String diver,
                          @Value("${db.maxPoolSize}") int maxPoolSize) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(databaseUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(diver);
        config.setMaximumPoolSize(maxPoolSize);

        this.dataSource = new HikariDataSource(config);
    }
}
