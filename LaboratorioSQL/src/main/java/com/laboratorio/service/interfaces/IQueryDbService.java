package com.laboratorio.service.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IQueryDbService {

    List<Map<String, Object>> execute(String userQuery, Integer problemId) throws SQLException;

    boolean isCorrectAnswer(List<Map<String, Object>> userResult, Integer problemId);

}
