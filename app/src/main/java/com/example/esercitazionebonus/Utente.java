/**
 * Bellini Nikita 65725
 */


package com.example.esercitazionebonus;

public class Utente {

    private int idUtente;
    private String username;
    private String password;
    private String citta;
    private String dataNascita;
    private static int id = 0;

    /**
     * Costruttore
     * @param username
     * @param password
     * @param citta
     * @param dataNascita
     */
    public Utente(String username, String password, String citta, String dataNascita){
        this.idUtente = this.id;
        this.username = username;
        this.password = password;
        this.citta = citta;
        this.dataNascita = dataNascita;
        this.id = this.id + 1;
    }

    public int getIdUtente() {
        return idUtente;
    }

    /**
     * Per cambiare username
     * @param username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Per cambiare la password
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCitta() {
        return citta;
    }

    public String getDataNascita() {
        return dataNascita;
    }

}
