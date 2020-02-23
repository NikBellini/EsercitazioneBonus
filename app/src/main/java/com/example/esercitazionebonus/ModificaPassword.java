package com.example.esercitazionebonus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ModificaPassword extends AppCompatActivity {

    //Variabili
    int idUtente;
    Utente utente;
    TextView username;
    TextView password;
    EditText nuovaPassword;
    EditText confermaPassword;
    Button aggiorna;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_modifica_password);

        //Associazione variabili e punti nel layout
        username = findViewById(R.id.modifica_password_username);
        password = findViewById(R.id.modifica_password_password);
        nuovaPassword = findViewById(R.id.modifica_password_nuova);
        confermaPassword = findViewById(R.id.modifica_password_conferma);
        aggiorna = findViewById(R.id.modifica_password_aggiorna);
        home = findViewById(R.id.modifica_password_home);

        //Recuperato l'id dell'utente che ha effettuato l'accesso
        idUtente = (int) getIntent().getExtras().get(Global.DATA);

        //Recuperato l'utente che ha effettuato l'accesso
        utente = DataBase.get(idUtente);

        //Setta i dati da visualizzare
        username.setText(utente.getUsername()); //Setta lo username
        password.setText(utente.getPassword()); //Setta la password


        //Premendo su aggiorna controlla se la password va bene e, se va bene,
        //la aggiorna
        aggiorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Controlla se la password non e' vuota
                if(nuovaPassword.getText().toString() != null && nuovaPassword.getText().toString().length() != 0) {

                    //Controlla se la nuova password non e' uguale a quella vecchia
                    if (!nuovaPassword.getText().toString().equals(utente.getPassword())) {

                        //Controlla se la password non e' uguale a quella da confermare
                        if (nuovaPassword
                                .getText()
                                .toString()
                                .equals(confermaPassword.getText().toString())) {

                            utente.setPassword(nuovaPassword.getText().toString()); //Aggiorna la password
                            DataBase.modifyUser(utente); //Aggiorna l'utente

                            //Torna alla home
                            Intent intent = new Intent(ModificaPassword.this, Home.class);
                            intent.putExtra(Global.DATA, idUtente);
                            startActivity(intent);

                        } else {
                            confermaPassword.setError("Le due password non coincidono");
                        }

                    } else {
                        nuovaPassword.setError("La nuova password non può essere uguale a quella vecchia");
                    }

                } else {
                    nuovaPassword.setError("Inserisci una password");
                }
            }
        });

        //Premendo sul pulsante home torna alla home
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Torna alla home
                Intent intent = new Intent(ModificaPassword.this, Home.class);
                intent.putExtra(Global.DATA, idUtente);
                startActivity(intent);
            }
        });
    }

}
