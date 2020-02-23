/**
 * Bellini Nikita 65725
 */

package com.example.esercitazionebonus;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;



public class Registrazione extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText confermaPassword;
    EditText cittaProvenienza;
    EditText dataNascita;
    Button registrati;
    DialogDatePicker dialogDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registrazione);

        //Associazione variabili e punti nel layout
        username = findViewById(R.id.registrazione_username);
        password = findViewById(R.id.registrazione_password);
        confermaPassword = findViewById(R.id.registrazione_password_conferma);
        cittaProvenienza = findViewById(R.id.registrazione_citta_provenienza);
        dataNascita = findViewById(R.id.registrazione_data_nascita);
        registrati = findViewById(R.id.registrazione_registrati);

        //Impostazione datePicker
        dialogDatePicker = new DialogDatePicker(Registrazione.this, dataNascita);

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean checkUsername = false; //Per controllare se lo username e' stato inserito
                boolean checkPassword = false; //Per controllare se la password e' stata inserita
                boolean checkCitta = false; //Per controllare se la citta' di provenienza e' stata inserita
                boolean checkData = false; //Per controllare se la data e' stata inserita

                //Controllo se lo username e' stato inserito
                if(username.getText().toString() != null && username.getText().toString().length() != 0) {
                    checkUsername = true;
                } else {
                    username.setError("Inserisci uno username");
                }

                //Controllo se la password e' stata inserita
                if(password.getText().toString() != null && password.getText().toString().length() != 0) {
                    checkPassword = true;
                } else {
                    password.setError("Inserisci una password");
                }

                //Controllo se la citta' di provenienza e' stata inserita
                if(cittaProvenienza.getText().toString() != null && cittaProvenienza.getText().toString().length() != 0) {
                    checkCitta = true;
                } else {
                    cittaProvenienza.setError("Inserisci la tua città di provenienza");
                }

                //Controllo se la data di nascita e' stata inserita
                if(dataNascita.getText().toString() != null && dataNascita.getText().toString().length() != 0) {
                    checkData = true;
                } else {
                    dataNascita.setError("Seleziona la tua data di nascita");
                }


                //Controllo se tutte le informazioni sono state inserite
                if(checkUsername && checkPassword && checkCitta && checkData) {

                    //Se le due password corrispondono
                    if (password.getText().toString().equals(confermaPassword.getText().toString())) {

                        //Aggiunge l'utente se lo username non e' gia' stato usato, altrimenti
                        //setta un errore
                        if (!DataBase.checkUsername(username.getText().toString())) {

                            //Nuovo utente creato con i dati inseriti
                            Utente utente = new Utente(username.getText().toString(), password.getText().toString(),
                                    cittaProvenienza.getText().toString(), dataNascita.getText().toString());

                            DataBase.add(utente); //Aggiunge l'utente al database

                            //Ritorno al Login
                            Intent intent = new Intent(Registrazione.this, Login.class);
                            startActivity(intent);

                        } else { //Se lo username e' gia' stato usato

                            username.setError("Username già usato. Scegline un altro"); //Setta l'errore

                        }

                    } else { //Se le due password non corrispondono

                        confermaPassword.setError("Le due password non corrispondono");

                    }

                }
            }
        });
    }
}
