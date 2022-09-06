package supermercado.service;

import supermercado.model.Produto;
import supermercado.persist.ProdutoDAO;

import java.util.List;

public class ProdutoService {
    private ProdutoDAO dao;

    public ProdutoService() {
        dao = new ProdutoDAO();
    }

    public boolean save(Produto produto) {
        return dao.save(produto);
    }

    public List<Produto> findAll() {
        return dao.findAll();
    }

    public boolean update(Produto produto) {
        return dao.update(produto);
    }

    public boolean deleteById(long id) {
        return dao.deleteById(id);
    }

    public boolean deleteAll() {
        return dao.deleteAll();
    }

    public Produto findById(long id) {
        return dao.findById(id);
    }
}
