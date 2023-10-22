package com.laboratorio.controllers;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.laboratorio.Entities.Problem;
import com.laboratorio.Entities.Query;
import com.laboratorio.http.response.ProblemDTO;
import com.laboratorio.repositories.ProblemRepository;
import com.laboratorio.service.ProblemService;
import com.laboratorio.service.QueryDbService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/problem")
@CrossOrigin(originPatterns = {"http://localhost:5173/"})
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
    public List<Map<String, Object>> executeQuery(@RequestParam String query ) throws SQLException {
        return queryDbService.executeUserQuery(query);
    }

}
