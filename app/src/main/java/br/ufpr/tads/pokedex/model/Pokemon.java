package br.ufpr.tads.pokedex.model;

public class Pokemon {

    private String nome;
    private int tipo;
    private String habilidades;
    private Usuario usuario;

    public Pokemon() {
    }

    public Pokemon(String nome, int tipo, String habilidades, Usuario usuario) {
        this.nome = nome;
        this.tipo = tipo;
        this.habilidades = habilidades;
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
