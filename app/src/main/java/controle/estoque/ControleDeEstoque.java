package main.java.controle.estoque;

public class ControleDeEstoque {
    private CadastroDeProdutos cadastroDeProdutos;
    private Map<String, Integer> estoque;

    public ControleDeEstoque(CadastroDeProdutos cadastroDeProdutos) {
        this.cadastroDeProdutos = cadastroDeProdutos;
        this.estoque = new HashMap<>();
    }

    public void adicionarAoEstoque(String codigo, int quantidade) {
        Produto produto = cadastroDeProdutos.obterProduto(codigo);

        if (produto != null) {
            estoque.put(codigo, estoque.getOrDefault(codigo, 0) + quantidade);
            System.out.println("Estoque atualizado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void exibirEstoque() {
        System.out.println("Estoque atual:");
        for (Map.Entry<String, Integer> entry : estoque.entrySet()) {
            Produto produto = cadastroDeProdutos.obterProduto(entry.getKey());
            System.out.println("Produto: " + produto.getNome() + ", Quantidade: " + entry.getValue());
        }
    }
}
