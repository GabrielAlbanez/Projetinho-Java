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
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = Database.getConnection();
        if (connection == null) {
            System.out.println("Não foi possível conectar ao banco de dados.");
            return; 
        }

        Scanner scanner = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        System.out.println("1 - listar usuarios |  2 - cadastrar usuarios | 3 - remover usuarios");
        String numeroEscolhido = scanner.nextLine();

        switch (numeroEscolhido) {
            case "1":

                System.out.println("Usuarios Cadastrados No Banco");

                List<Usuario> UserDataa = usuarioDAO.getAllUserDataBase();

                int index = 0;

                for (Usuario usuario : UserDataa) {
                    System.out.println("usuario" + index);
                    System.out.println("id : " + usuario.getId());
                    System.out.println("nome : " + usuario.getNome());
                    System.out.println("email : " + usuario.getEmail());
                    index++;
                }

                break;

            case "2":

                System.out.print("Quantos usuários você deseja cadastrar? ");
                int numeroDeUsuarios = scanner.nextInt();
                scanner.nextLine(); 

                for (int i = 0; i < numeroDeUsuarios; i++) {
                    JFrame frame = new JFrame("Cadastro de Usuário");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(300, 200);
                    frame.setLayout(new GridLayout(4, 2));

                    JLabel nomeLabel = new JLabel("Nome:");
                    JTextField nomeField = new JTextField();
                    JLabel emailLabel = new JLabel("E-mail:");
                    JTextField emailField = new JTextField();
                    JLabel senhaLabel = new JLabel("Senha:");
                    JPasswordField senhaField = new JPasswordField();
                    JButton cadastrarButton = new JButton("Cadastrar");

                    cadastrarButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String nome = nomeField.getText().trim();
                            String email = emailField.getText().trim();
                            String senha = new String(senhaField.getPassword()).trim();

                            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                                JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos!", "Erro",
                                        JOptionPane.ERROR_MESSAGE);
                                return; 
                            }


                            List<Usuario> UserData = usuarioDAO.getAllUserDataBase();

                            for (Usuario usuario : UserData) {
                                if (usuario.getEmail().equalsIgnoreCase(email)) {
                                    JOptionPane.showMessageDialog(frame, "E-mail já cadastrado!", "Erro",
                                            JOptionPane.ERROR_MESSAGE);
                                    return; 
                                }
                            }

                            usuarios.add(new Usuario(nome, senha, email));
                            JOptionPane.showMessageDialog(frame, "Usuário cadastrado com sucesso!");
                            frame.dispose(); 
                        }
                    });

                   
                    frame.add(nomeLabel);
                    frame.add(nomeField);
                    frame.add(emailLabel);
                    frame.add(emailField);
                    frame.add(senhaLabel);
                    frame.add(senhaField);
                    frame.add(cadastrarButton);
                    frame.setVisible(true);

                   
                    while (frame.isVisible()) {
                        try {
                            Thread.sleep(100); 
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

               
                usuarioDAO.salvarUsuarios(usuarios);

                break;

            case "3":
                JFrame frame = new JFrame("Lista de Usuários");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 300);
                frame.setLayout(new BorderLayout());

                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                frame.add(scrollPane, BorderLayout.CENTER);

                JButton loadButton = new JButton("Carregar Usuários");
                frame.add(loadButton, BorderLayout.SOUTH);

                loadButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        List<Usuario> DataUser = usuarioDAO.getAllUserDataBase();
                        textArea.setText(""); 
                        int indexx = 0;
                        for (Usuario usuario : DataUser) {
                            textArea.append("Usuário " + indexx + "\n");
                            textArea.append("ID: " + usuario.getId() + "\n");
                            textArea.append("Nome: " + usuario.getNome() + "\n");
                            textArea.append("Email: " + usuario.getEmail() + "\n\n");
                            indexx++;
                        }

                       
                        JPanel deletePanel = new JPanel();
                        deletePanel.setLayout(new FlowLayout());
                        JLabel idLabel = new JLabel("Qual ID de usuário gostaria de deletar?");
                        JTextField idField = new JTextField(10);
                        JButton deleteButton = new JButton("Deletar");

                        deleteButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String idSelection = idField.getText().trim();
                                try {
                                    int id = Integer.parseInt(idSelection);
                                    boolean response = usuarioDAO.deleteUserFromId(id);

                                    if (response) {
                                        JOptionPane.showMessageDialog(frame, "Usuário deletado com sucesso!");
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Falha ao deletar usuário!");
                                    }
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(frame,
                                            "ID inválido. Por favor, insira um número válido.", "Erro",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });

                        deletePanel.add(idLabel);
                        deletePanel.add(idField);
                        deletePanel.add(deleteButton);
                        frame.add(deletePanel, BorderLayout.NORTH);
                    }
                });

                frame.setVisible(true); 
                break;
            default:
                break;
        }



        scanner.close();

        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
