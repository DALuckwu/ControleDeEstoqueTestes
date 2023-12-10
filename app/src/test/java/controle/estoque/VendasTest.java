package test.java.controle.estoque;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VendasTest {

    @Test
    public void testRealizarVenda() {
        CadastroDeProdutos cadastroMock = mock(CadastroDeProdutos.class);
        ControleDeEstoque controleMock = mock(ControleDeEstoque.class);

        when(cadastroMock.obterProduto("001")).thenReturn(new Produto("001", "Produto A", 10.0));
        when(controleMock.obterQuantidadeEmEstoque("001")).thenReturn(10);

        Vendas vendas = new Vendas(cadastroMock, controleMock);
        vendas.realizarVenda("001", 5);

        assertEquals(5, controleMock.obterQuantidadeEmEstoque("001"));
        verify(cadastroMock).obterProduto("001");
        verify(controleMock).obterQuantidadeEmEstoque("001");
        verify(controleMock).adicionarAoEstoque("001", -5);
    }

    @Test
    public void testVendaQuantidadeInsuficiente() {
        CadastroDeProdutos cadastroMock = mock(CadastroDeProdutos.class);
        ControleDeEstoque controleMock = mock(ControleDeEstoque.class);

        when(cadastroMock.obterProduto("001")).thenReturn(new Produto("001", "Produto A", 10.0));
        when(controleMock.obterQuantidadeEmEstoque("001")).thenReturn(2);

        Vendas vendas = new Vendas(cadastroMock, controleMock);
        vendas.realizarVenda("001", 5);

        assertEquals(2, controleMock.obterQuantidadeEmEstoque("001"));
        verify(cadastroMock).obterProduto("001");
        verify(controleMock).obterQuantidadeEmEstoque("001");
        verifyNoMoreInteractions(controleMock);
    }

    @Test
    public void testRealizarVendaSemEstoque() {
        CadastroDeProdutos cadastroMock = mock(CadastroDeProdutos.class);
        ControleDeEstoque controleMock = mock(ControleDeEstoque.class);

        when(cadastroMock.obterProduto("001")).thenReturn(new Produto("001", "Produto A", 10.0));
        when(controleMock.obterQuantidadeEmEstoque("001")).thenReturn(0);

        Vendas vendas = new Vendas(cadastroMock, controleMock);
        vendas.realizarVenda("001", 5);

        assertEquals(0, controleMock.obterQuantidadeEmEstoque("001"));
        verify(cadastroMock).obterProduto("001");
        verify(controleMock).obterQuantidadeEmEstoque("001");
        verifyNoMoreInteractions(controleMock);
    }

    @Test
    public void testRealizarVendaProdutoInexistente() {
        CadastroDeProdutos cadastroMock = mock(CadastroDeProdutos.class);
        ControleDeEstoque controleMock = mock(ControleDeEstoque.class);

        when(cadastroMock.obterProduto("999")).thenReturn(null);

        Vendas vendas = new Vendas(cadastroMock, controleMock);
        vendas.realizarVenda("999", 3);

        verify(cadastroMock).obterProduto("999");
        verifyNoMoreInteractions(controleMock);
    }

    @Test
    public void testRealizarVendaQuantidadeZero() {
        CadastroDeProdutos cadastroMock = mock(CadastroDeProdutos.class);
        ControleDeEstoque controleMock = mock(ControleDeEstoque.class);

        when(cadastroMock.obterProduto("001")).thenReturn(new Produto("001", "Produto A", 10.0));
        when(controleMock.obterQuantidadeEmEstoque("001")).thenReturn(5);

        Vendas vendas = new Vendas(cadastroMock, controleMock);
        vendas.realizarVenda("001", 0);

        assertEquals(5, controleMock.obterQuantidadeEmEstoque("001"));
        verify(cadastroMock).obterProduto("001");
        verify(controleMock).obterQuantidadeEmEstoque("001");
        verifyNoMoreInteractions(controleMock);
    }
}
