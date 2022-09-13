package supermercado.app;

import supermercado.service.ProdutoService;

import javax.swing.*;

public class DeleteById {
    public static void main(String[] args) {
        var service = new ProdutoService();

        var status = service.deleteById(1L);

        var msg = "Status da exclusão: " + (status ? "Sucesso" : "Falha");
        JOptionPane.showMessageDialog(null, msg, "Resposta", JOptionPane.INFORMATION_MESSAGE);
    }
}
