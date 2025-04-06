package com.api_rest.api_rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_rest.api_rest.model.Produto;
import com.api_rest.api_rest.service.ProdutoService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping( "/produtos" )
public class ProdutoController {

    private ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Produto> listarProdutos() {
        return service.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProduto( @PathVariable Long id ) {
        Produto produto = service.buscarId( id );
        return produto != null ? ResponseEntity.ok( produto ) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Produto criarProduto( @RequestBody Produto produto ) {
        return service.salvar( produto );
    }

    @PostMapping("/lote")
    public ResponseEntity<List<Produto>> criarProduto( @RequestBody List<Produto> produtos ) {

        List<Produto> salvos = new ArrayList<>();

        for ( Produto produto : produtos ) {
            Produto produtoSalvo = service.salvar( produto );
            salvos.add( produtoSalvo );
        }

        return ResponseEntity.status( HttpStatus.CREATED ).body( salvos );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar( @PathVariable Long id, @RequestBody Produto produto ) {
        Produto atualizado = service.atualizar( id, produto );
        return atualizado != null ? ResponseEntity.ok( atualizado ) : ResponseEntity.notFound().build();  
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empty> deletar( @PathVariable Long id ) {
        return service.deletar( id ) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
