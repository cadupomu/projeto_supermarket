package supermercado.app;

import supermercado.service.ProdutoService;

import javax.swing.*;

public class Update {
    public static void main(String[] args) {
        var service = new ProdutoService();
        var pro = service.findById(1L);

        var nome = JOptionPane.showInputDialog(null, "Informe o nome do produto: ", "Input Nome", JOptionPane.QUESTION_MESSAGE);
        pro.setNome(nome);

        var preco = JOptionPane.showInputDialog(null, "Informe o preço do produto: ", "Input Preço", JOptionPane.QUESTION_MESSAGE);
        pro.setPreco(Double.valueOf(preco));

        var quantidade = JOptionPane.showInputDialog(null, "Informe a quantidade: ", "Input Quantidade", JOptionPane.QUESTION_MESSAGE);
        pro.setQuantidade(Integer.parseInt(quantidade));

        var tipo = JOptionPane.showInputDialog(null, "Informe o tipo do produto: ", "Input Tipo", JOptionPane.QUESTION_MESSAGE);
        pro.setTipo(tipo);

        var situacao = "Ocorreu uma falha na gravação. Verifique o log";
        var iconeStatus = JOptionPane.ERROR_MESSAGE;

        var response = service.update(pro);
        if(response) {
            situacao = "Gravado com sucesso";
            iconeStatus = JOptionPane.INFORMATION_MESSAGE;
        }

        var msg = "Situação da gravação no banco: " + situacao + "\n\n" + "Produto.nome: " + pro.getNome() + "\n" + "Produto.preço: " + pro.getPreco() + "\n" + "Produto.quantidade: " + pro.getQuantidade() + "\n" + "Produto.tipo: " + pro.getTipo();
        JOptionPane.showMessageDialog(null, msg, "Resposta", iconeStatus);
    }
}
