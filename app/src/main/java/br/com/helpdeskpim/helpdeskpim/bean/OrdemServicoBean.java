package br.com.helpdeskpim.helpdeskpim.bean;

public class OrdemServicoBean {

    private int id;
    private String nome;
    private String email;
    private String local;
    private String titulo;
    private String telefone;
    private String categoria;
    private String descricao;
    private String status;

    public OrdemServicoBean(int id, String nome, String email, String telefone,
            String local, String titulo, String descricao){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.local = local;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
