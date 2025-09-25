package spring.context.lesson.database.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class DataBaseConfig {

    @Getter
    private final HikariDataSource dataSource;

    public DataBaseConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/java_pro_course");
        config.setUsername("root");
        config.setPassword("123456");
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(10);

        this.dataSource = new HikariDataSource(config);
    }
}
