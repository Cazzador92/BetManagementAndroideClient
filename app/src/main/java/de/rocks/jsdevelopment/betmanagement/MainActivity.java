package de.rocks.jsdevelopment.betmanagement;

//region imports
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import de.rocks.jsdevelopment.betmanagement.activity.BetDetailsActivity;
import de.rocks.jsdevelopment.betmanagement.activity.BetDetailsGruppenActivity;
import de.rocks.jsdevelopment.betmanagement.activity.SettingsActivity;
import de.rocks.jsdevelopment.betmanagement.adapter.NavDrawerListAdapter;
import de.rocks.jsdevelopment.betmanagement.fragment.BetDetailsFragment;
import de.rocks.jsdevelopment.betmanagement.fragment.BetDetailsGruppenFragment;
import de.rocks.jsdevelopment.betmanagement.fragment.BetItemFragment;
import de.rocks.jsdevelopment.betmanagement.fragment.BetItemFragmentGruppen;
import de.rocks.jsdevelopment.betmanagement.fragment.HomeFragment;
import de.rocks.jsdevelopment.betmanagement.fragment.SQLiteFragment;
import de.rocks.jsdevelopment.betmanagement.model.Bet;
import de.rocks.jsdevelopment.betmanagement.model.NavDrawerItem;
//endregion

public class MainActivity extends Activity
        implements
        BetDetailsFragment.OnFragmentInteractionListener,
        BetItemFragment.OnFragmentInteractionListener,
        BetItemFragmentGruppen.OnBetItemClickListener
{

    //region Variables

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private int lastPosition = -1;

    //Menu
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    static final int PICK_BET_REQUEST = 0;

    //endregion

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateSlidingMenu(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    //endregion

    //region onClickListener  for Fragments
    @Override
    public void onFragmentInteraction(Uri uri) {
        //anlage oder bearbeitung von wetten.
    }

    @Override
    public void onFragmentInteraction(Bet item) {
        //Toast.makeText(this, "main", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, BetDetailsActivity.class);
        intent.putExtra("Bet", item);
        startActivity(intent);
    }


    @Override
    public void onBetClickedGruppen(Bet item) {
        Intent intent = new Intent(MainActivity.this, BetDetailsGruppenActivity.class);
        intent.putExtra("Bet", item);
        startActivity(intent);
    }

    //endregion

    //region NavigationDrawer

    private void CreateSlidingMenu(Bundle savedInstanceState) {

        mTitle = mDrawerTitle = getTitle();

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        navMenuIcons = getResources()
                .obtainTypedArray
                        (R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // Alle Elemente die in derm Array in strings_nav_drawer.xml vorhanden sind werden hinzugefügt
        // es MUSS immer ein Titel und ein Icon vorhanden sein
        for (int i = 0; i < navMenuTitles.length; i++)
        {
            navDrawerItems.add(new NavDrawerItem(navMenuTitles[i], navMenuIcons.getResourceId(i, -1)));
        }

        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);

        mDrawerList.setAdapter(adapter);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }

    }


    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                   fragment = new HomeFragment();
                break;
            case 1:
                fragment = BetItemFragment.newInstance();
                break;
            case 2:
                // Testen der SettingsActivity zu benutzen
                //todo liste laden und details bearbeiten sodass die wie settings aussehen.
                fragment = BetItemFragmentGruppen.newInstance();
                //Toast.makeText(this, "maintestseite", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                fragment = new SQLiteFragment().newInstance();
                break;
            case 4:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
            default:
                fragment = new HomeFragment();
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .addToBackStack(null)       //wird alles nacheinander auf den Stack gelegt
                    .commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            Log.e("MainActivity", "Error in creating fragment for case " + position);
        }
    }
    //endregion

    //region OptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        menu.setGroupVisible(0, !drawerOpen);

        return super.onPrepareOptionsMenu(menu);
    }
    //endregion

    //region Settings
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    //endregion
}
