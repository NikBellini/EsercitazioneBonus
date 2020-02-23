/**
 * Bellini Nikita 65725
 */

package com.example.esercitazionebonus;

import java.util.*;

/**
 * Database degli utenti
 */
public class DataBase {

    private static List<Utente> utenti = new ArrayList<>(); //DataBase degli utenti

    public static List<Utente> getDataBase(){
        return utenti;
    }

    public static Utente get(int i){
        return utenti.get(i);
    }

    /**
     * Per aggiungere un nuovo utente
     * @param u Utente da aggiungere
     * @return true se l'utente e' stato aggiunto, false altrimenti
     */
    public static void add(Utente u){
        utenti.add(u);
    }

    /**
     * Per ottenere l'id dell'utente
     * @param username
     * @return L'id dell'utente o -1 se non e' stato trovato
     */
    public static int getIdByUsername(String username) {

        for(Utente u : utenti) {
            if(u.getUsername().equals(username)) {
                return u.getIdUtente();
            }
        }

        return -1;
    }


    /**
     * Controlla se lo username e' gia' presente nel database
     * @param username
     * @return true se e' presente, false altrimenti
     */
    public static boolean checkUsername(String username) {

        for(Utente u : utenti) {
            if(u.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Metodo per aggiornare l'utente all'interno della lista
     * @param utente
     */
    public static void modifyUser(Utente utente) {
        List<Utente> utentiClone = new ArrayList<>();

        for(Utente u : utenti) {

            if(u.getIdUtente() == utente.getIdUtente()) {
                utentiClone.add(utente);
            } else {
                utentiClone.add(u);
            }

        }

        utenti = utentiClone;
    }
}
