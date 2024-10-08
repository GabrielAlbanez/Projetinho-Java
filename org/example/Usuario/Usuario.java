package org.example.Usuario;

import java.util.ArrayList;

public class Usuario {
    @SuppressWarnings("unused")
    private int id;
    private String nome;
    private String password;
    private String email;

    // Construtor
    public Usuario(int id,String nome, String password, String email) {
        this.id = id;
        this.nome = nome;
        this.password = password;
        this.email = email;
    }


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

    public int getId() {
        return id;
    }

    public static String[] cadastroDeUsuarios(ArrayList<Usuario> usuarios) {
        // criar uma lista data,
        String[] data = new String[usuarios.size()];

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            data[i] = "Nome: " + usuario.getNome() + ", Email: " + usuario.getEmail() + ", Senha: "
                    + usuario.getPassword();
        }

        return data;
    }

}
