package com.laboratorio.controllers;

import com.laboratorio.Entities.Problem;
import com.laboratorio.Entities.Query;
import com.laboratorio.repositories.ProblemRepository;
import com.laboratorio.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ProblemRepository problemRepository;

    @GetMapping
    public String allQueries(){
        Problem p =  problemRepository.findById(1).get();
        System.out.println(p.getId()+" "+p.getAuthor() +" "+p.getQueries().get(0));
        return "?";
    }

}
