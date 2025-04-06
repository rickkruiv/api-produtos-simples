package com.api_rest.api_rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.api_rest.api_rest.model.Produto;

@Service
public class ProdutoService {

    private final Map<Long, Produto> produtos = new HashMap<>();
    private long proximoId = 1;

    public List<Produto> listarProdutos() {
        return new ArrayList<>( produtos.values() );
    }

    public Produto buscarId( Long id ) {
        return produtos.get( id );
    }

    public Produto salvar( Produto produto ) {
        produto.setId( proximoId++ );
        produtos.put( produto.getId(), produto );
        return produto;
    }

    public Produto atualizar( Long id, Produto produtoAtualizado ) {
        Produto produtoExistente = produtos.get( id );

        if ( produtoExistente != null ) {
            produtoAtualizado.setId( id );
            produtos.put( id, produtoAtualizado );
            return produtoAtualizado;
        }
        return null;
    }

    public boolean deletar( Long id ) {
        return produtos.remove( id ) != null;
    }
}
