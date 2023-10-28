package com.laboratorio.service;

import com.laboratorio.Entities.Query;
import com.laboratorio.repositories.QueryRepository;
import com.laboratorio.service.interfaces.IQueryDbService;
import com.laboratorio.service.jdbc.PoolConnections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class QueryDbService implements IQueryDbService {

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private PoolConnections poolConnections;


    @Override
    public List<Map<String, Object>> execute(String userQuery, Integer problemId) throws SQLException {
        var query = queryRepository.findById(problemId).get();
        return poolConnections.get(query.getProblem().getDbName())
                .queryForList(userQuery != null ? userQuery : query.getQuerySolution());
    }


    @Override
    public boolean isCorrectAnswer(List<Map<String, Object>> userResult, Integer problemId) {

        try {
            List<Map<String, Object>> results = execute(null, problemId);

            if (results.size() != userResult.size()) //las filas no coinciden
                return false;

            if (results.get(0).keySet().size() != userResult.get(0).keySet().size()) // las columnas no coinciden
                return false;


            return isCorrectRows(userResult, results);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isCorrectRows(List<Map<String, Object>> userResult, List<Map<String, Object>> problemResult) {

        byte index = 0;
        for (Map<String, Object> rows : userResult) {
            var valuesUser = rows.keySet();
            var valuesProblem = problemResult.get(index);

            for (String value : valuesUser) {
                if (!rows.get(value).toString().equalsIgnoreCase(valuesProblem.get(value).toString()))
                    return false;
            }
            index++;
        }

        return true;
    }
}
