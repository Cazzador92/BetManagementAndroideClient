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

import de.rocks.jsdevelopment.betmanagement.R;
import de.rocks.jsdevelopment.betmanagement.fragment.BetDetailsFragment;
import de.rocks.jsdevelopment.betmanagement.model.Bet;

public class BetDetailsActivity extends Activity
    implements BetDetailsFragment.OnFragmentInteractionListener
{

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
            BetDetailsFragment fragment = BetDetailsFragment.newInstance(bet, false);
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
}
