package com.laboratorio.service;

import com.laboratorio.repositories.QueryRepository;
import com.laboratorio.service.interfaces.IQueryDbService;
import com.laboratorio.service.jdbc.CustomJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class QueryDbService implements IQueryDbService {


    @Autowired
    private CustomJdbcTemplate jdbc;

    @Autowired
    private QueryRepository queryRepository;


    @Override
    public List<Map<String, Object>> executeUserQuery(String userQuery) throws SQLException {
        jdbc.connectToDataBase(queryRepository.findById(1).get().getProblem().getDbName());
        return jdbc.queryForList(userQuery);
    }

    @Override
    public boolean isCorrectAnswer(List<Map<String, Object>> userResult) {
        return false;
    }
}
