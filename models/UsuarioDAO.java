package  models;

import org.example.Database.Database;
import org.example.Usuario.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO {
    public void salvarUsuarios(List<Usuario> usuarios) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            for (Usuario usuario : usuarios) {
                pstmt.setString(1, usuario.getNome());
                pstmt.setString(2, usuario.getEmail());
                pstmt.setString(3, usuario.getPassword()); // Ajustado para `getSenha()`
                pstmt.addBatch(); // Adiciona à batch
            }
            pstmt.executeBatch(); // Executa todos os inserts de uma vez
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error ao salvar usuarios no banco");
            
        }
    }
}
