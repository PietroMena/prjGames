/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

/**
 *
 * @author aluno
 */
public class Games {

    private int id;
    private String Nome;
    private String Status;
    private String Plataforma;
    private String Genero;
    private boolean Multiplayer;
    private int AnoLancamento;
    private String CaminhoImagem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getPlataforma() {
        return Plataforma;
    }

    public void setPlataforma(String Plataforma) {
        this.Plataforma = Plataforma;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public boolean isMultiplayer() {
        return Multiplayer;
    }

    public void setMultiplayer(boolean Multiplayer) {
        this.Multiplayer = Multiplayer;
    }

    public int getAnoLancamento() {
        return AnoLancamento;
    }

    public void setAnoLancamento(int AnoLancamento) {
        this.AnoLancamento = AnoLancamento;
    }

    public String getCaminhoImagem() {
        return CaminhoImagem;
    }

    public void setCaminhoImagem(String CaminhoImagem) {
        this.CaminhoImagem = CaminhoImagem;
    }

   
}
