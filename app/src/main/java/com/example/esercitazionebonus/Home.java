/**
 * Bellini Nikita 65725
 */

package com.example.esercitazionebonus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    //Variabili
    int idUtente;
    Utente utente;
    TextView messaggioBenvenuto;
    TextView username;
    TextView password;
    TextView cittaProvenienza;
    TextView dataNascita;
    TextView modificaPassword;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        //Associazione variabili e punti nel layout
        messaggioBenvenuto = findViewById(R.id.home_messaggio_benvenuto);
        username = findViewById(R.id.home_username);
        password = findViewById(R.id.home_password);
        cittaProvenienza = findViewById(R.id.home_citta);
        dataNascita = findViewById(R.id.home_data);
        modificaPassword = findViewById(R.id.home_modifica_password);
        logout = findViewById(R.id.home_logout);

        //Recupera l'id dell'utente che ha effettuato l'accesso
        idUtente = (int) getIntent().getExtras().get(Global.DATA);

        //Recupera i dati dell'utente che ha effettuato l'accesso
        utente = DataBase.get(idUtente);

        //Setta i dati dell'utente visibili nella home
        messaggioBenvenuto.setText("Benvenuto " + utente.getUsername()); //Setta lo username per il messaggio di benvenuto
        username.setText(utente.getUsername()); //Setta lo username
        password.setText(utente.getPassword()); //Setta la password
        cittaProvenienza.setText(utente.getCitta()); //Setta la citta' di provenienza
        dataNascita.setText(utente.getDataNascita()); //Setta la data di nascita


        //Premendo su logout torna alla pagina del Login
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
            }
        });


        //Premendo su modifica password porta a una pagina che permette di modificare la password
        modificaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ModificaPassword.class);
                intent.putExtra(Global.DATA, idUtente);
                startActivity(intent);
            }
        });
    }

}
