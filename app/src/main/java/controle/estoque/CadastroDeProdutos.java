package main.java.controle.estoque;

import java.util.HashMap;
import java.util.Map;

public class CadastroDeProdutos {
    private Map<String, Produto> produtos;

    public CadastroDeProdutos() {
        this.produtos = new HashMap<>();
    }

    public void cadastrarProduto(String codigo, String nome, double preco) {
        Produto produto = new Produto(codigo, nome, preco);
        produtos.put(codigo, produto);
        System.out.println("Produto cadastrado com sucesso.");
    }

    public Produto obterProduto(String codigo) {
        return produtos.get(codigo);
    }
}
