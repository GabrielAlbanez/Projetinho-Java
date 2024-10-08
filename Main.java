import java.util.Scanner;
import org.example.Auth.Autenticacao;
import org.example.Database.Database;
import org.example.Usuario.Usuario;

import models.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Connection connection = Database.getConnection();
        if (connection == null) {
            System.out.println("Não foi possível conectar ao banco de dados.");
            return; // Encerra o programa se não conseguir conectar
        }

        Scanner scanner = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>();
        UsuarioDAO usuarioDAO = new UsuarioDAO(); // Cria a instância do DAO

        // Pergunta quantos usuários o usuário deseja cadastrar
        System.out.print("Quantos usuários você deseja cadastrar? ");
        int numeroDeUsuarios = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        for (int i = 0; i < numeroDeUsuarios; i++) {
            JFrame frame = new JFrame("Cadastro de Usuário");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);
            frame.setLayout(new GridLayout(4, 2));

            // Criar campos de entrada
            JLabel nomeLabel = new JLabel("Nome:");
            JTextField nomeField = new JTextField();
            JLabel emailLabel = new JLabel("E-mail:");
            JTextField emailField = new JTextField();
            JLabel senhaLabel = new JLabel("Senha:");
            JPasswordField senhaField = new JPasswordField();
            JButton cadastrarButton = new JButton("Cadastrar");

            // Adicionar ação ao botão
            cadastrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nome = nomeField.getText().trim();
                    String email = emailField.getText().trim();
                    String senha = new String(senhaField.getPassword()).trim();

                    // Validação dos campos
                    if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        return; // Sai se houver erro
                    }

                    // Verificação de e-mail duplicado
                    for (Usuario usuario : usuarios) {
                        if (usuario.getEmail().equalsIgnoreCase(email)) {
                            JOptionPane.showMessageDialog(frame, "E-mail já cadastrado!", "Erro",
                                    JOptionPane.ERROR_MESSAGE);
                            return; // Sai se encontrar um e-mail existente
                        }
                    }

                    // Cadastro do novo usuário
                    usuarios.add(new Usuario(nome, senha, email));
                    JOptionPane.showMessageDialog(frame, "Usuário cadastrado com sucesso!");
                    frame.dispose(); // Fecha a janela após o cadastro
                }
            });

            // Adicionar componentes ao frame
            frame.add(nomeLabel);
            frame.add(nomeField);
            frame.add(emailLabel);
            frame.add(emailField);
            frame.add(senhaLabel);
            frame.add(senhaField);
            frame.add(cadastrarButton);
            frame.setVisible(true);

            // Espera o usuário fechar a janela antes de continuar
            while (frame.isVisible()) {
                try {
                    Thread.sleep(100); // Espera um pouco para evitar uso excessivo da CPU
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        // Após o loop de cadastro, salvar os usuários no banco de dados
        usuarioDAO.salvarUsuarios(usuarios);

        scanner.close();

        // Fechar a conexão após o uso
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
