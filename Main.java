
import java.util.Scanner;

import org.example.Auth.Autenticacao;
import org.example.Usuario.Usuario;

public class Main {
    public static void main(String[] args) {
        // Criar um scanner para capturar a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Solicitar quantos usuários serão criados
        System.out.print("Quantos usuários você deseja cadastrar? ");
        int numeroDeUsuarios = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        // Criar um array de usuários com base no número fornecido
        Usuario[] usuarios = new Usuario[numeroDeUsuarios];

        // Loop para capturar os dados de cada usuário
        for (int i = 0; i < numeroDeUsuarios; i++) {
            System.out.println("Digite os dados do usuário " + (i + 1) + ":");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            // Criar um novo objeto Usuario e armazená-lo no array
            usuarios[i] = new Usuario(nome, senha, email);
        }

        System.out.print("1 - listar usuarios  / 2 - ir para autenticação ");
        String NumeroPego = scanner.nextLine();

        if (NumeroPego.equals("1")) {
            for (Usuario usuario : usuarios) {
                // Exibir os dados do usuário
                System.out.println("Nome: " + usuario.getNome());
                System.out.println("Senha: " + usuario.getPassword());
                System.out.println("Email: " + usuario.getEmail());
                System.out.println("--------------------"); // Separador entre usuários
            }
        } else {

        Autenticacao autenticacao = new Autenticacao();

        // Solicitar nome e senha ao usuário para autenticação
        System.out.print("Digite o nome de usuário para autenticação: ");
        String nomeFornecido = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senhaFornecida = scanner.nextLine();

        // Verificar se o nome e a senha estão corretos para algum usuário no array
        if (autenticacao.autenticar(usuarios, nomeFornecido, senhaFornecida)) {
            System.out.println("Autenticação bem-sucedida! Bem-vindo, " + nomeFornecido + "!");
        } else {
            System.out.println("Falha na autenticação. Nome ou senha incorretos.");
        }
        }

    

        // Fechar o scanner
        scanner.close();
    }
}
