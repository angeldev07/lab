package com.laboratorio.repositories;

import com.laboratorio.Entities.Problem;
import com.laboratorio.Entities.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {

    Problem findProblemById(Integer id);

//    List<Query> findAllQueriesByProblemId(Integer id);
}
