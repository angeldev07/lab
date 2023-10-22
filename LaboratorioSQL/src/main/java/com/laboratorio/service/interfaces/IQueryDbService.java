package com.laboratorio.service.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IQueryDbService {

    List<Map<String, Object>> executeUserQuery(String userQuery) throws SQLException;

    boolean isCorrectAnswer(List<Map<String, Object>> userResult);

}
