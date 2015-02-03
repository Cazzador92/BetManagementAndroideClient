package de.rocks.jsdevelopment.betmanagement.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.rocks.jsdevelopment.betmanagement.R;
import de.rocks.jsdevelopment.betmanagement.activity.BetDetailsActivity;
import de.rocks.jsdevelopment.betmanagement.activity.SettingsActivity;
import de.rocks.jsdevelopment.betmanagement.adapter.BetListAdapter;
import de.rocks.jsdevelopment.betmanagement.fragment.dummy.DummyBetContent;
import de.rocks.jsdevelopment.betmanagement.model.Bet;

public class BetItemFragment extends Fragment implements AdapterView.OnItemClickListener{

    //TODO 3 langer click später


    private static final String TAG = BetItemFragment.class.getSimpleName();

    // wird vielleicht später gebraucht
    //private ProgressDialog pDialog;

    private List<Bet> betList = new ArrayList<Bet>();
    private ListView listView;
    private BetListAdapter adapter;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(DummyBetContent.ITEMS.get(position));
        }
    }


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Bet item);
    }

    public static BetItemFragment newInstance() {
        BetItemFragment fragment = new BetItemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BetItemFragment() {    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_betitem, container, false);

        Activity activity = getActivity();
        setHasOptionsMenu(true);

        // Set the adapter
        listView = (ListView) view.findViewById(R.id.betlist_container);
//        List<Bet> list = getListe();
        adapter = new BetListAdapter(activity, DummyBetContent.ITEMS);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void  onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_betlist, menu);
        //return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        FragmentManager fragmentManager = getFragmentManager();

        //TODO Diese auswahl in die Activity auslagern
        switch (item.getItemId())
        {
            case R.id.action_bar_bet_add:
                Toast.makeText(getActivity(), "Erstellen einer neuen Wette", Toast.LENGTH_SHORT).show();

                //Neue leere Wette erstellen und Activity starten
                Intent intent = new Intent(getActivity(), BetDetailsActivity.class);
                intent.putExtra("Bet", new Bet());
                startActivity(intent);

                break;
            case R.id.action_bar_bet_save:
                Toast.makeText(getActivity(), "Neue Wette Speichern", Toast.LENGTH_SHORT).show();
                Bet bet = new Bet();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, BetDetailsFragment.newInstance(bet, false))
                        .commit();
                break;
            case R.id.action_bar_bet_delete:
                Toast.makeText(getActivity(), "Löschen einer Wette", Toast.LENGTH_SHORT).show();

                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, BetItemFragment.newInstance())
                        .commit();
                break;
//            case R.id.action_bar_bet_edit:
//                Toast.makeText(getActivity(), "You selected the edit option", Toast.LENGTH_SHORT).show();
//
//                fragmentManager.beginTransaction()
//                        .replace(R.id.frame_container, BetEditFragment.newInstance("Param1", "Param2"))
//                        .commit();
//                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = listView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
