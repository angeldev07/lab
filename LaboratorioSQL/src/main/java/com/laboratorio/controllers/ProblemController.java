package com.laboratorio.controllers;

import com.laboratorio.http.request.ValidateQuery;
import com.laboratorio.http.response.ProblemDTO;
import com.laboratorio.service.ProblemService;
import com.laboratorio.service.QueryDbService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/problem")
@CrossOrigin("http://localhost:5173/")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private QueryDbService queryDbService;

    private ModelMapper modelMapper = new ModelMapper();


    @GetMapping
    public ProblemDTO allQueries(){
       ProblemDTO problemDTO = modelMapper.map(problemService.findProblem(1), ProblemDTO.class);
        return problemDTO;
    }

    @GetMapping("/execute")
    public List<Map<String, Object>> executeQuery(@RequestParam String query, @RequestParam Integer problemId) throws SQLException {
        return queryDbService.execute(query, problemId);
    }

    @PostMapping("/validate")
    public boolean validateQuery(@RequestBody ValidateQuery validateQuery){
        return queryDbService.isCorrectAnswer(validateQuery.getResults(), validateQuery.getProblemId());
    }

}
