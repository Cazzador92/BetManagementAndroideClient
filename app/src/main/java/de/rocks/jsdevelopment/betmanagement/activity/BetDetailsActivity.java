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

        //Übergebene Daten hollen
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Bet bet = (Bet) bundle.get("Bet");
        // oder = (Bet) bundle.getSerializable("Bet");

        setContentView(R.layout.activity_bet_details);
        if (savedInstanceState == null) {
            BetDetailsFragment fragment = BetDetailsFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bet_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
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
