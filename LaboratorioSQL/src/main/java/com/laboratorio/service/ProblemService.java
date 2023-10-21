package com.laboratorio.service;

import com.laboratorio.Entities.Problem;
import com.laboratorio.Entities.Query;
import com.laboratorio.repositories.ProblemRepository;
import com.laboratorio.service.interfaces.IProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService implements IProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public Problem findProblem(Integer problemId) {
        return problemRepository.findProblemById(problemId);
    }
}
