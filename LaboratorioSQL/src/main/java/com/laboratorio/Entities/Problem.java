package com.laboratorio.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "problema")
public class Problem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name =  "nombre")
    private String name;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "docente")
    private String author;

    @Column(name = "nombre_bd")
    private String dbName;

    @Column(name = "esquema")
    private String schema;

    @OneToMany( mappedBy = "problem")
    private List<Query> queries;

}
