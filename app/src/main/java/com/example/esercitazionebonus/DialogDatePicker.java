/**
 * Bellini Nikita 65725
 */

package com.example.esercitazionebonus;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DialogDatePicker extends DatePickerDialog {

    private Context context;
    private EditText editData;
    private Calendar data;



    /**
     * Premendo sul campo della data appare il calendario per selezionare la data
     */
    View.OnClickListener clickEditData = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //Chiude la tastiera
            ((InputMethodManager)context
                    .getSystemService(Activity.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(v.getWindowToken(), 0);

            // Mostra il dialog
            DialogDatePicker.this.show();
        }
    };

    /**
     * Con il focus sopra, mostra il calendario
     */
    View.OnFocusChangeListener focusEditData = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus) {

                //Chiude la tastiera
                ((InputMethodManager)context
                        .getSystemService(Activity.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(v.getWindowToken(), 0);

                //Fa vedere il calendario da cui scegliere la data
                DialogDatePicker.this.show();
            }
        }
    };

    /**
     * Premendo su conferma, aggiorna la data e compila il campo della data
     */
    OnClickListener clickDialogOk = new OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            //Aggiorna la data
            data.set(Calendar.YEAR, getDatePicker().getYear());
            data.set(Calendar.MONTH, getDatePicker().getMonth());
            data.set(Calendar.DAY_OF_MONTH, getDatePicker().getDayOfMonth());

            //Compila l'EditText con la data selezionata
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            editData.setText(format.format(data.getTime()));
        }
    };

    /**
     * Premendo su annulla torna indietro senza modificare nulla
     */
    OnClickListener clickDialogAnnulla = new OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            //Non fa nulla
        }
    };


    public DialogDatePicker(Context context, EditText editData) {

        //Costruzione
        super(
                context,
                null,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        this.context = context;
        this.editData = editData;

        //Salva la data passata al DatePicker
        this.data = Calendar.getInstance();
        this.data.set(Calendar.YEAR, this.getDatePicker().getYear());
        this.data.set(Calendar.MONTH, this.getDatePicker().getMonth());
        this.data.set(Calendar.DAY_OF_MONTH, this.getDatePicker().getDayOfMonth());

        //Imposta come data massima la data di oggi
        this.getDatePicker().setMaxDate(data.getTimeInMillis());

        //L'utente non puo' scrivere
        this.editData.setInputType(InputType.TYPE_NULL);

        //Imposta i metodi sulla EditText, al click e al focus
        this.editData.setOnClickListener(clickEditData);
        this.editData.setOnFocusChangeListener(focusEditData);

        //Definizione del tasto conferma e del tasto annuulla
        this.setButton(DialogInterface.BUTTON_POSITIVE, "Conferma", clickDialogOk);
        this.setButton(DialogInterface.BUTTON_NEGATIVE, "Annulla", clickDialogAnnulla);
    }
}