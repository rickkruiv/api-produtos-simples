package com.api_rest.api_rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api_rest.api_rest.model.Produto;
import com.api_rest.api_rest.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    public Produto buscarId( Long id ) {
        Optional<Produto> produto = repository.findById( id );
        return produto.orElse(null);
    }

    public Produto salvar( Produto produto ) {
        return repository.save( produto );
    }

    public Produto atualizar( Long id, Produto produtoAtualizado ) {
        Optional<Produto> produtoExistente = repository.findById( id );

        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setNome( produtoAtualizado.getNome() );
            produto.setPreco( produtoAtualizado.getPreco() );
            produto.setUrlImg( produtoAtualizado.getUrlImg() );
            return repository.save( produto );
        }
        return null;
    }

    public boolean deletar( Long id ) {

        if ( repository.existsById( id ) ) {
            repository.deleteById( id );
            return true;
        }
        return false;
    }
}
