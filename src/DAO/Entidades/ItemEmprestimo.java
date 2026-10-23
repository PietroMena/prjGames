/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Entidades;

/**
 *
 * @author aluno
 */

public class ItemEmprestimo {
    private int id;
    private int idEmprestimo;
    private int idGame;
    private String nomeGame; // Atributo auxiliar para exibição

    public ItemEmprestimo() {}

    public ItemEmprestimo(int idGame, String nomeGame) {
        this.idGame = idGame;
        this.nomeGame = nomeGame;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String getNomeGame() {
        return nomeGame;
    }

    public void setNomeGame(String nomeGame) {
        this.nomeGame = nomeGame;
    }
    
    
}