package com.laboratorio.service.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class CustomJdbcTemplate extends JdbcTemplate{

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    private String lastDbNameConnection;

    @Autowired
    public CustomJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

    public void setLastDbNameConnection(String lastDbNameConnection) {
        this.lastDbNameConnection = lastDbNameConnection;
    }

    public void connectToDataBase(String dbName) {

        if( lastDbNameConnection != null &&  lastDbNameConnection.equalsIgnoreCase(dbName))
            return;

        if(lastDbNameConnection == null )
            setLastDbNameConnection(dbName);



        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl("jdbc:mysql://localhost:3306/"+dbName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        this.setDataSource(dataSource);

    }
}
