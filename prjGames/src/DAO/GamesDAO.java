/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexao.Conexao;
import conexao.Games;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class GamesDAO {

    private Conexao conexao;
    private Connection conn;

    public GamesDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Games game) {
        String sql = "INSERT INTO games (nome, status, plataforma, genero, multiplayer, ano_lancamento, caminho_imagem)" + "VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, game.getNome());
            stmt.setString(2, game.getStatus());
            stmt.setString(3, game.getPlataforma());
            stmt.setString(4, game.getGenero());
            stmt.setBoolean(5, game.isMultiplayer());
            stmt.setInt(6, game.getAnoLancamento());
            stmt.setString(7, game.getCaminhoImagem());
            stmt.execute();

        } catch (SQLException ex) {
            System.out.println("Erro ao inserir jogo: " + ex.getMessage());
        }

    }

    public boolean editar(Games g) {
        String sql = "UPDATE games SET nome=?, status=?, plataforma=?, genero=?, "
                + "multiplayer=?, ano_lancamento=?, caminho_imagem=? WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, g.getNome());
            stmt.setString(2, g.getStatus());
            stmt.setString(3, g.getPlataforma());
            stmt.setString(4, g.getGenero());
            stmt.setBoolean(5, g.isMultiplayer());
            stmt.setInt(6, g.getAnoLancamento());
            stmt.setString(7, g.getCaminhoImagem());
            stmt.setInt(8, g.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar jogo: " + ex.getMessage());
            return false;
        }
    }

    public Games getGames(int idGames) {
        String sql = "SELECT * FROM games WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, idGames);
            ResultSet rs = stmt.executeQuery();

            // Se encontrar o jogo na base de dados, preenche o objeto e devolve-o
            if (rs.next()) {
                Games g = new Games();
                g.setId(rs.getInt("id"));
                g.setNome(rs.getString("nome"));
                g.setStatus(rs.getString("status"));
                g.setPlataforma(rs.getString("plataforma"));
                g.setGenero(rs.getString("genero"));
                g.setMultiplayer(rs.getBoolean("multiplayer"));
                g.setAnoLancamento(rs.getInt("ano_lancamento"));
                g.setCaminhoImagem(rs.getString("caminho_imagem"));

                return g;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar jogo por ID: " + ex.getMessage());
        }

        return null;
    }

    public List<Games> getGames() {
        List<Games> lista = new ArrayList<>();
        String sql = "SELECT * FROM games";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Games g = new Games();

                g.setId(rs.getInt("id"));
                g.setNome(rs.getString("nome"));
                g.setStatus(rs.getString("status"));
                g.setPlataforma(rs.getString("plataforma"));
                g.setGenero(rs.getString("genero"));
                g.setMultiplayer(rs.getBoolean("multiplayer"));
                g.setAnoLancamento(rs.getInt("ano_lancamento"));
                g.setCaminhoImagem(rs.getString("caminho_imagem"));
                lista.add(g);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar jogos: " + ex.getMessage());
        }
        return lista;
    }

    public void excluir(int id) {
        try {
            String sql = "delete from games WHERE id=?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir jogo:" + ex.getMessage());
        }
    }
}
