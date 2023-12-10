package test.java.controle.estoque;

import org.junit.Test;
import org.mockito.Mockito;

import main.java.controle.estoque.CadastroDeProdutos;
import main.java.controle.estoque.ControleDeEstoque;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ControleDeEstoqueTest {

    @Test
    public void testAdicionarAoEstoque() {
        CadastroDeProdutos cadastroMock = mock(CadastroDeProdutos.class);
        when(cadastroMock.obterProduto("001")).thenReturn(new Produto("001", "Produto A", 10.0));

        ControleDeEstoque controle = new ControleDeEstoque(cadastroMock);
        controle.adicionarAoEstoque("001", 5);

        assertEquals(5, controle.obterQuantidadeEmEstoque("001"));
        verify(cadastroMock).obterProduto("001");
    }

    @Test
    public void testAdicionarProdutoInexistenteAoEstoque() {
        CadastroDeProdutos cadastroMock = mock(CadastroDeProdutos.class);
        when(cadastroMock.obterProduto("999")).thenReturn(null);

        ControleDeEstoque controle = new ControleDeEstoque(cadastroMock);
        controle.adicionarAoEstoque("999", 3);

        assertEquals(0, controle.obterQuantidadeEmEstoque("999"));
        verify(cadastroMock).obterProduto("999");
    }

    @Test
    public void testAdicionarQuantidadeNegativaAoEstoque() {
        CadastroDeProdutos cadastroMock = mock(CadastroDeProdutos.class);

        ControleDeEstoque controle = new ControleDeEstoque(cadastroMock);
        controle.adicionarAoEstoque("001", -5);

        assertEquals(0, controle.obterQuantidadeEmEstoque("001"));
        verifyNoInteractions(cadastroMock);
    }

    @Test
    public void testExibirEstoqueVazio() {
        CadastroDeProdutos cadastroMock = mock(CadastroDeProdutos.class);

        ControleDeEstoque controle = new ControleDeEstoque(cadastroMock);

        assertEquals(0, controle.exibirEstoque());
        verifyZeroInteractions(cadastroMock);
    }

    @Test
    public void testExibirEstoqueComProdutos() {
        CadastroDeProdutos cadastroMock = mock(CadastroDeProdutos.class);
        when(cadastroMock.obterProduto("001")).thenReturn(new Produto("001", "Produto A", 10.0));
        when(cadastroMock.obterProduto("002")).thenReturn(new Produto("002", "Produto B", 15.0));

        ControleDeEstoque controle = new ControleDeEstoque(cadastroMock);
        controle.adicionarAoEstoque("001", 5);
        controle.adicionarAoEstoque("002", 8);

        assertNotEquals(0, controle.exibirEstoque());
        verify(cadastroMock, times(2)).obterProduto(anyString());
    }
}
