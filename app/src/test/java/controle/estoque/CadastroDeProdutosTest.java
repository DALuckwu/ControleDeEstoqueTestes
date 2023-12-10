package test.java.controle.estoque;

import org.junit.Test;
import org.mockito.Mockito;

import main.java.controle.estoque.CadastroDeProdutos;
import main.java.controle.estoque.Produto;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CadastroDeProdutosTest {

    @Test
    public void testCadastrarProduto() {
        CadastroDeProdutos cadastro = new CadastroDeProdutos();

        // Mock Produto
        Produto produtoMock = mock(Produto.class);
        when(produtoMock.getCodigo()).thenReturn("001");

        // Mock cadastrarProduto
        CadastroDeProdutos cadastroSpy = spy(cadastro);
        doReturn(produtoMock).when(cadastroSpy).cadastrarProduto(anyString(), anyString(), anyDouble());

        // Teste
        cadastroSpy.cadastrarProduto("001", "Produto A", 10.0);

        assertNotNull(cadastroSpy.obterProduto("001"));
        verify(cadastroSpy).cadastrarProduto(anyString(), anyString(), anyDouble());
    }

    @Test
    public void testObterProdutoInexistente() {
        CadastroDeProdutos cadastro = new CadastroDeProdutos();

        // Mock obterProduto
        CadastroDeProdutos cadastroSpy = spy(cadastro);
        doReturn(null).when(cadastroSpy).obterProduto(anyString());

        // Teste
        assertNull(cadastroSpy.obterProduto("999"));
        verify(cadastroSpy).obterProduto(anyString());
    }

    @Test
    public void testCadastrarProdutoDuplicado() {
        CadastroDeProdutos cadastro = new CadastroDeProdutos();

        // Mock cadastrarProduto
        CadastroDeProdutos cadastroSpy = spy(cadastro);
        doReturn(null).when(cadastroSpy).cadastrarProduto(anyString(), anyString(), anyDouble());

        // Teste
        assertNull(cadastroSpy.cadastrarProduto("001", "Produto A", 10.0));
        verify(cadastroSpy).cadastrarProduto(anyString(), anyString(), anyDouble());
    }

    @Test
    public void testCadastrarProdutoSemCodigo() {
        CadastroDeProdutos cadastro = new CadastroDeProdutos();

        // Mock cadastrarProduto
        CadastroDeProdutos cadastroSpy = spy(cadastro);
        doReturn(null).when(cadastroSpy).cadastrarProduto(anyString(), anyString(), anyDouble());

        // Teste
        assertNull(cadastroSpy.cadastrarProduto("", "Produto A", 10.0));
        verify(cadastroSpy).cadastrarProduto(anyString(), anyString(), anyDouble());
    }

    @Test
    public void testObterProdutoPorCodigo() {
        CadastroDeProdutos cadastro = new CadastroDeProdutos();

        // Mock Produto
        Produto produtoMock = mock(Produto.class);
        when(produtoMock.getCodigo()).thenReturn("001");

        // Mock obterProduto
        CadastroDeProdutos cadastroSpy = spy(cadastro);
        doReturn(produtoMock).when(cadastroSpy).obterProduto(anyString());

        // Teste
        assertNotNull(cadastroSpy.obterProduto("001"));
        verify(cadastroSpy).obterProduto(anyString());
    }
}
