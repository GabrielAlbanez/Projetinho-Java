package org.example.CadastroUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroUsuario {
    public static void main(String[] args) {
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
                String nome = nomeField.getText();
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());

                String[] data = { nome, email, senha };

                for (String user : data) {
                    System.out.println(user);
                }

                

                // Aqui você pode adicionar a lógica para salvar os dados
                JOptionPane.showMessageDialog(frame, "Usuário cadastrado com sucesso!");
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
    }
}
