package org.example.Auth;


import org.example.Usuario.Usuario;

public class Autenticacao {

    // Método para validar o nome de usuário e a senha
    public boolean autenticar(Usuario[] usuarios, String nomeFornecido, String senhaFornecida) {
        for (Usuario usuario : usuarios) {
            // Verifica se o nome e a senha correspondem a algum usuário no array
            if (usuario.getNome().equals(nomeFornecido) && usuario.getPassword().equals(senhaFornecida)) {
                return true; // Autenticação bem-sucedida
            }
        }
        return false; // Falha na autenticação
    }
}
