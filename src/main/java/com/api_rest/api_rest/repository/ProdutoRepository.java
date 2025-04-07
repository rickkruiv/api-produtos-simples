package com.api_rest.api_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_rest.api_rest.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
