package com.laboratorio.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "consulta")
public class Query implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "solucion")
    private String querySolution;

    @Column(name = "practicas")
    private Integer practices;

    @ManyToOne
    @JoinColumn(name = "problema_id")
    private Problem problem;
}
