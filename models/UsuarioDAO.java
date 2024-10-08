package models;

import org.example.Database.Database;
import org.example.Usuario.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void salvarUsuarios(List<Usuario> usuarios) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (Usuario usuario : usuarios) {
                pstmt.setString(1, usuario.getNome());
                pstmt.setString(2, usuario.getEmail());
                pstmt.setString(3, usuario.getPassword()); // Corrigido para `getSenha()`
                pstmt.addBatch(); // Adiciona à batch
            }
            pstmt.executeBatch(); // Executa todos os inserts de uma vez
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar usuários no banco");
        }
    }

    public List<Usuario> getAllUserDataBase() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios;";

        try (Connection conn = Database.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Cria um objeto Usuario a partir dos dados da linha do ResultSet
                Usuario usuario = new Usuario(
                        rs.getString("role"),
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("email"));

                usuarios.add(usuario); // Adiciona à lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao recuperar usuários do banco");
        }

        return usuarios;
    }

    public boolean deleteUserFromId(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = Database.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário deletado com sucesso.");
                return true;
            } else {
                System.out.println("Nenhum usuário encontrado com o id: " + id);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        }
        return false;
    }

}
