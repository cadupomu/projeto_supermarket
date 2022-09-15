package supermercado.model;

public class Produto {

    private int id;
    private String nome;
    private Double preco;
    private int quantidade;
    private String tipo;

    public Produto() {}

    public Produto(int id, String nome, Double preco, int quantidade, String tipo) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public Produto retornaProduto() {
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
