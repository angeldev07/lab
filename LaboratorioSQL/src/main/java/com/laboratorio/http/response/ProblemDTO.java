package com.laboratorio.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProblemDTO {
    private String name;
    private String description;
    private String author;
    private String schema;
    private List<QueryDTO> queries;

}
