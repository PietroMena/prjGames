//Faz conexão com o Banco de Dados
package DAO.ArquivosDao;

import conexao.Conexao;
import DAO.Entidades.Amigo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmigoDAO {

    private Conexao conexao;
    private Connection conn;

    public AmigoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    // Método para Inserir (Cadastrar)
    public boolean inserir(Amigo amigo) {
        String sql = "INSERT INTO amigos (nome, telefone, email) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, amigo.getNome());
            stmt.setString(2, amigo.getTelefone());
            stmt.setString(3, amigo.getEmail());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir amigo: " + e.getMessage());
            return false;
        }
    }

    // Listar todos
    public List<Amigo> getAmigos() {
        String sql = "SELECT * FROM amigos";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Amigo> lista = new ArrayList<>();

            while (rs.next()) {
                Amigo amigo = new Amigo();
                amigo.setId(rs.getInt("id"));
                amigo.setNome(rs.getString("nome"));
                amigo.setTelefone(rs.getString("telefone"));
                amigo.setEmail(rs.getString("email"));
                lista.add(amigo);
            }
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    // Método para Editar
    public boolean editar(Amigo amigo) {
        String sql = "UPDATE amigos SET nome=?, telefone=?, email=? WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, amigo.getNome());
            stmt.setString(2, amigo.getTelefone());
            stmt.setString(3, amigo.getEmail());
            stmt.setInt(4, amigo.getId());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao editar amigo: " + e.getMessage());
            return false;
        }
    }

    // Método para Excluir
    public boolean excluir(int id) {
        String sql = "DELETE FROM amigos WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir amigo: " + e.getMessage());
            return false;
        }
    }

    //Buscar pelo Id do caba
    public Amigo buscarPorId(int id) {
        String sql = "SELECT * FROM amigos WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Amigo amigo = new Amigo();
                amigo.setId(rs.getInt("id"));
                amigo.setNome(rs.getString("nome"));
                amigo.setTelefone(rs.getString("telefone"));
                amigo.setEmail(rs.getString("email"));
                return amigo;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar amigo: " + e.getMessage());
        }
        return null;
    }
}
