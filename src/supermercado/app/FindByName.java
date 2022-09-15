package supermercado.app;

import supermercado.service.ProdutoService;

import javax.swing.*;

public class FindByName {
    public static void main(String[] args) {
        var service = new ProdutoService();

        var respostaList = service.findByName("Arroz").stream().map(produto -> "Produto.nome: " + produto.getNome() + "\n" + "Produto.preco: " + produto.getPreco() + "\n" + "Produto.quantidade: " + produto.getQuantidade() + "\n" + "Produto.tipo " + produto.getTipo() + "\n\n").toList();
        JOptionPane.showMessageDialog(null, respostaList, "Resposta", JOptionPane.INFORMATION_MESSAGE);
    }
}
