package supermercado.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import supermercado.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDAO extends DAO {
    private static Logger logger = LoggerFactory.getLogger(ProdutoDAO.class);

    public boolean save(Produto produto) {
        var sql = "insert into produto(nome, preco, quantidade, tipo) values (?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPreco());
            pstmt.setInt(3, produto.getQuantidade());
            pstmt.setString(4, produto.getTipo());

            logger.debug("Query executada: {}", sql);
            return (pstmt.executeUpdate() != 0) ? Boolean.TRUE : Boolean.FALSE;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on save produto. Error: {}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    public List<Produto> findAll() {

        var produtos = new ArrayList<Produto>();
        var sql = "select * from produto";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            logger.info("Query executada: {}", sql);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    produtos.add(setProduto(rs));
                }
            }
        } catch (SQLException e) {
                e.printStackTrace();
                logger.error("Error on list produto. Error: {}", e.getMessage());
                return new ArrayList<>();
            }
            return produtos;
        }

    public boolean update(Produto produto) {
        var sql = "update produto set nome = ?, preco = ?, quantidade = ?, tipo = ? where id  = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPreco());
            pstmt.setInt(3, produto.getQuantidade());
            pstmt.setString(4, produto.getTipo());
            pstmt.setInt(5, produto.getId());

            logger.info("Query executada: {}", sql);
            return (pstmt.executeUpdate() != 0) ? Boolean.TRUE : Boolean.FALSE;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on save produto. Error: {}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    public boolean deleteById(long id) {
        var sql = "delete from produto where id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);

            logger.info("Query executada: {}", sql);
            return (pstmt.executeUpdate() != 0) ? Boolean.TRUE : Boolean.FALSE;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error on delete produto. Error: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

    public boolean deleteAll(List<Produto> produtos) {
        var sql = "delete from produto where id in (?)";
        var sqlIN = produtos
                .stream()
                .map(produto -> String.valueOf(produto.getId()))
                .collect(Collectors.joining(",", "(", ")"));

        sql = sql.replace("(?)", sqlIN);

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            logger.info("Query executada: {}", sql);
            return (pstmt.executeUpdate() != 0) ? Boolean.TRUE : Boolean.FALSE;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on delete table produto. Error: {}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    public Produto findById(long id) {
        var sql = "select * from produto where id = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);

            logger.info("Query executada: {}", sql);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? setProduto(rs) : new Produto();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on find id produto. Error: {}", e.getMessage());
            return new Produto();
        }
    }

    public List<Produto> findByName(String nome) {
        var produtos = new ArrayList<Produto>();
        var sql = "select * from produto where nome like ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, '%' + nome + '%');

            logger.info("Query executada: {}", sql);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    produtos.add(setProduto(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on list produtos. Error: {}", e.getMessage());
            return new ArrayList<>();
        }
        return produtos;
    }

    public List<Produto> findByTipo(String tipo) {
        var produtos = new ArrayList<Produto>();
        var sql = "select * from produto where tipo like ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, '%' + tipo + '%');

            logger.info("Query executada: {}", sql);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    produtos.add(setProduto(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error on list produtos. Error: {}", e.getMessage());
            return new ArrayList<>();
        }
        return produtos;
    }

    private Produto setProduto(ResultSet rs) throws SQLException {
        return new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), rs.getInt("matricula"), rs.getString("tipo"));
    }
}
