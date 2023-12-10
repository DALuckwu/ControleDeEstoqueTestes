package main.java.controle.estoque;

public class Vendas {
    private CadastroDeProdutos cadastroDeProdutos;
    private ControleDeEstoque controleDeEstoque;

    public Vendas(CadastroDeProdutos cadastroDeProdutos, ControleDeEstoque controleDeEstoque) {
        this.cadastroDeProdutos = cadastroDeProdutos;
        this.controleDeEstoque = controleDeEstoque;
    }

    public void realizarVenda(String codigo, int quantidade) {
        Produto produto = cadastroDeProdutos.obterProduto(codigo);

        if (produto != null) {
            int estoqueAtual = controleDeEstoque.obterQuantidadeEmEstoque(codigo);

            if (estoqueAtual >= quantidade) {
                controleDeEstoque.adicionarAoEstoque(codigo, -quantidade);
                System.out.println("Venda realizada com sucesso.");
            } else {
                System.out.println("Quantidade insuficiente em estoque.");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}
