/**
 * Bellini Nikita 65725
 */

package com.example.esercitazionebonus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class Login extends AppCompatActivity {

    //Variabili
    EditText username;
    EditText password;
    Button accedi;
    TextView registrati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        accedi = findViewById(R.id.login_accedi);
        registrati = findViewById(R.id.login_registrati);

        //Pulsante accedi
        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean usernameTrovato = false; //Per indicare se lo username e' stato trovato
                boolean passwordTrovata = false; //Per indicare se la password e' stata trovata

                //Controllo se lo username e' corretto
                for(Utente u : DataBase.getDataBase()){
                    if(u.getUsername().equals(username.getText().toString())){
                        usernameTrovato = true;
                    }
                }

                //Controllo se la password associata a quello username e' corretta
                for(Utente u : DataBase.getDataBase()){
                    if(u.getPassword().equals(password.getText().toString()) &&
                            u.getUsername().equals(username.getText().toString())){
                        passwordTrovata = true;
                    }
                }

                if(!usernameTrovato){
                    username.setError("Username errato");
                }

                //Se la password e' stata trovata, anche lo username e' stato trovato, quindi accede,
                //altrimenti la password e' sbagliata
                if(passwordTrovata){

                    //Si sposta nella pagina Home
                    Intent intent = new Intent(Login.this, Home.class);
                    //Manda l'id dell'utente
                    intent.putExtra(Global.DATA, DataBase.getIdByUsername(username.getText().toString()));
                    startActivity(intent);

                } else {
                    //Setta l'errore perche' non e' stata trovata la password
                    password.setError("Password errata");
                }
            }
        });

        //EditText registrati
        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registrazione.class);
                startActivity(intent);
            }
        });
    }
}
