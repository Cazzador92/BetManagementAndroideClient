package de.rocks.jsdevelopment.betmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO prüfen ob wir online sind und Anmelde-Daten vorhanden sind

        startActivity(new Intent(StartActivity.this, MainActivity.class));
    }

}
