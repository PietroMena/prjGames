package DAO.ArquivosDao;

import conexao.Conexao;
import DAO.Entidades.ItemEmprestimo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemEmprestimoDAO {
    private Connection conn;

    public ItemEmprestimoDAO() {
        this.conn = new Conexao().getConexao();
    }

    public boolean salvarItens(int idEmprestimo, List<ItemEmprestimo> itens) {
        String sqlItem = "INSERT INTO item_emprestimo (id_emprestimo, id_game) VALUES (?, ?)";
        String sqlStatus = "UPDATE games SET status = 'Emprestado' WHERE id = ?";

        try {
            for (ItemEmprestimo item : itens) {
              
                PreparedStatement stmt1 = this.conn.prepareStatement(sqlItem);
                stmt1.setInt(1, idEmprestimo);
                stmt1.setInt(2, item.getIdGame());
                stmt1.execute();

               
                PreparedStatement stmt2 = this.conn.prepareStatement(sqlStatus);
                stmt2.setInt(1, item.getIdGame());
                stmt2.execute();
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar itens do empréstimo: " + e.getMessage());
            return false;
        }
    }
}