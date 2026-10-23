package DAO.ArquivosDao;

import conexao.Conexao;
import DAO.Entidades.Emprestimo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    private Conexao conexao;
    private Connection conn;

    public EmprestimoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

   public int inserir(Emprestimo emp) {
    String sql = "INSERT INTO emprestimos (id_amigo, data_emprestimo) VALUES (?, ?)";
    
    try {
        // Statement.RETURN_GENERATED_KEYS permite recuperar o ID do empréstimo criado
        PreparedStatement stmt = this.conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, emp.getIdAmigo());
        stmt.setDate(2, emp.getDataEmprestimo());
        stmt.execute();

        java.sql.ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); // Retorna o ID gerado
        }
    } catch (SQLException e) {
        System.out.println("Erro ao salvar empréstimo: " + e.getMessage());
    }
    return -1;
}

    public boolean registrarDevolucao(int idEmprestimo, java.sql.Date dataDevolucao) {
        String sql = "UPDATE emprestimos SET data_devolucao = ?, devolvido = true WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setDate(1, dataDevolucao);
            stmt.setInt(2, idEmprestimo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao registrar devolução: " + e.getMessage());
            return false;
        }
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos WHERE devolvido = false";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Emprestimo emp = new Emprestimo();
                emp.setId(rs.getInt("id"));
                emp.setIdAmigo(rs.getInt("id_amigo"));
                emp.setIdGame(rs.getInt("id_game"));
                emp.setDataEmprestimo(rs.getDate("data_emprestimo"));
                emp.setDevolvido(rs.getBoolean("devolvido"));
                lista.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos: " + e.getMessage());
        }
        return lista;
    }

    public List<Emprestimo> getEmprestimos() {
        String sql = "SELECT e.id, a.nome AS amigo, g.nome AS jogo, e.data_emprestimo "
                + "FROM emprestimos e "
                + "INNER JOIN amigos a ON e.id_amigo = a.id "
                + "INNER JOIN games g ON e.id_game = g.id";

        List<Emprestimo> lista = new ArrayList<>();

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Emprestimo emp = new Emprestimo();
                emp.setId(rs.getInt("id"));

                emp.setNomeAmigo(rs.getString("amigo"));
                emp.setNomeGame(rs.getString("jogo"));
                emp.setDataEmprestimo(rs.getDate("data_emprestimo"));

                lista.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos: " + e.getMessage());
        }
        return lista;
    }

    public boolean devolver(int idEmprestimo) {
        String sqlBuscaGame = "SELECT id_game FROM emprestimos WHERE id = ?";
        String sqlDelete = "DELETE FROM emprestimos WHERE id = ?";
        String sqlUpdateGame = "UPDATE games SET status = 'Disponível' WHERE id = ?";

        try {
        
            PreparedStatement stmtBusca = this.conn.prepareStatement(sqlBuscaGame);
            stmtBusca.setInt(1, idEmprestimo);
            ResultSet rs = stmtBusca.executeQuery();

            int idGame = 0;
            if (rs.next()) {
                idGame = rs.getInt("id_game");
            }

           
            PreparedStatement stmtDelete = this.conn.prepareStatement(sqlDelete);
            stmtDelete.setInt(1, idEmprestimo);
            stmtDelete.execute();

       
            if (idGame > 0) {
                PreparedStatement stmtUpdate = this.conn.prepareStatement(sqlUpdateGame);
                stmtUpdate.setInt(1, idGame);
                stmtUpdate.execute();
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao devolver empréstimo: " + e.getMessage());
            return false;
        }
    }
}
