package com.laboratorio.controllers;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.laboratorio.Entities.Problem;
import com.laboratorio.Entities.Query;
import com.laboratorio.http.response.ProblemDTO;
import com.laboratorio.repositories.ProblemRepository;
import com.laboratorio.service.ProblemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problem")
@CrossOrigin(originPatterns = {"http://localhost:5173/"})
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    private ModelMapper modelMapper = new ModelMapper();


    @GetMapping
    public ProblemDTO allQueries(){
       ProblemDTO problemDTO = modelMapper.map(problemService.findProblem(1), ProblemDTO.class);
        return problemDTO;
    }

}
