package supermercado.app;

import supermercado.service.ProdutoService;

import javax.swing.*;

public class FindById {
    public static void main(String[] args) {
        var service = new ProdutoService();

        var pro = service.findById(1L);

        var msg = "Produto.nome: " + pro.getNome() + "\n" + "Produto.preço: " + pro.getPreco() + "\n" + "Produto.quantidade: " + pro.getQuantidade() + "\n" + "Produto.tipo: " + pro.getTipo();
        JOptionPane.showMessageDialog(null, msg, "Resposta", JOptionPane.INFORMATION_MESSAGE);
    }
}
