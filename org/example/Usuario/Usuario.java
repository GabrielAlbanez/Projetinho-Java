package org.example.Usuario;

public class Usuario {
    private String nome;
    private String password;
    private String email;

    // Construtor
    public Usuario(String nome, String password, String email) {
        this.nome = nome;
        this.password = password;
        this.email = email;
    }

    // Getters para acessar os atributos
    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
