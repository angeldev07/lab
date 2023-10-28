package com.laboratorio.service.jdbc;


import com.laboratorio.repositories.ProblemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class PoolConnections {

    private final String URL = "jdbc:mysql://localhost:3306/%s";
    private HashMap<String, JdbcTemplate> pool;
    private ProblemRepository problemRepository;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Autowired
    public PoolConnections(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
        this.pool = new HashMap<>();
    }


    private DriverManagerDataSource createConnection(String db) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(String.format(URL, db));

        return dataSource;
    }

    @PostConstruct
    private void startConnections(){
        this.problemRepository.findAll().forEach( problem -> {
            String db = problem.getDbName();
            this.pool.put(db, new JdbcTemplate(createConnection(db)));
        });
    }

    public JdbcTemplate get(String db){
        return this.pool.get(db);
    }

    public boolean pop(String db){
        return this.pool.remove(db) != null;
    }

}
