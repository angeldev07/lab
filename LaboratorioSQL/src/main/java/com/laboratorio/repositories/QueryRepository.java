package com.laboratorio.repositories;

import com.laboratorio.Entities.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository  extends CrudRepository<Query, Integer> {
}
