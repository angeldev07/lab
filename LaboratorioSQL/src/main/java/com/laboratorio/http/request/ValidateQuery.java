package com.laboratorio.http.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateQuery {
    private List<Map<String, Object>> results;
    private Integer problemId;
}
