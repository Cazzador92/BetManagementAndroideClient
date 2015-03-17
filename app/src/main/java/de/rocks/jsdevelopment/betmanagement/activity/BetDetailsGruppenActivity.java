package de.rocks.jsdevelopment.betmanagement.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;

import de.rocks.jsdevelopment.betmanagement.R;
import de.rocks.jsdevelopment.betmanagement.fragment.BetDetailsFragment;
import de.rocks.jsdevelopment.betmanagement.fragment.BetDetailsGruppenFragment;
import de.rocks.jsdevelopment.betmanagement.model.Bet;

public class BetDetailsGruppenActivity extends Activity
        implements BetDetailsGruppenFragment.OnFragmentInteractionListener
{

    private BetDetailsGruppenFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Übergebene Daten holen
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Bet bet = (Bet) bundle.get("Bet");
        // oder = (Bet) bundle.getSerializable("Bet");

        setContentView(R.layout.activity_bet_details);
        if (savedInstanceState == null) {
            fragment = BetDetailsGruppenFragment.newInstance(bet, false);
            //  fragment.CreateSpecialTextFields();
            getFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setResult(0);
    }

    private void setResult(){

    }

    public void deleteSubscriberItem(View view) {
        if (fragment.isEditEnabled()){
            Toast.makeText(this, "Löschen angeklickt", Toast.LENGTH_SHORT).show();

        }
    }
}
