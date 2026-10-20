package DAO;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

public class DataBaseConfig {
    public static DataSource getDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/users2");
        ds.setUsername("postgres");
        ds.setPassword("alba77ww");
        return ds;
    }
}
