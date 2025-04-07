package com.api_rest.api_rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity // dizer que é uma entidade / modelo
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )

    private Long id;
    private String nome;
    private double preco;
    private String urlImg;
    
    public Produto( Long id, String nome, double preco, String urlImg ) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.urlImg = urlImg;
    }
    
    public Produto() {}
}
