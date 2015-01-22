package de.rocks.jsdevelopment.betmanagement.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import de.rocks.jsdevelopment.betmanagement.R;
import de.rocks.jsdevelopment.betmanagement.adapter.BetListAdapter;
import de.rocks.jsdevelopment.betmanagement.model.Bet;
import de.rocks.jsdevelopment.betmanagement.model.BetSubscriber;

public class BetItemFragment extends Fragment
{

    private static final String TAG = BetItemFragment.class.getSimpleName();

    // wird vielleicht später gebraucht
    //private ProgressDialog pDialog;
    private List<Bet> betList = new ArrayList<Bet>();
    private ListView listView;
    private BetListAdapter adapter;


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

        // Set the adapter
        listView = (ListView) view.findViewById(R.id.betlist_container);
//        List<Bet> list = getListe();
        adapter = new BetListAdapter(activity, DummyBetContent.ITEMS);
        listView.setAdapter(adapter);

        return view;
    }

    private List<Bet> getListe() {
        List<Bet> list = new ArrayList<>();

//        for (int i = 1; i < 5; i++) {
//            list.add(createBet(i));
//        }
        list.add(createBet(1));
        list.add(createBet(2));
        return list;
    }


    private static Bet createBet(int i) {
        Bet bet = new Bet();
        bet.setName("Testwette" + String.valueOf(i));
        bet.setDescription("Dies ist eine Testwette " + String.valueOf(i));
        bet.setOwner("1");
        bet.setId(String.valueOf(i));
//        bet.setDeadline(Date.valueOf("21.01.2015"));
//        bet.setStart(Date.valueOf("23.01.2015"));
//        bet.setEnd(Date.valueOf("29.01.2015"));

        List<BetSubscriber> user = new ArrayList<BetSubscriber>();

//        for (int pers = 1; i < 5; pers++) {
//            user.add(createUser(pers));
//        }

        user.add(createUser(1));
        user.add(createUser(2));

        bet.setSubscriber(user);

        return bet;
    }

    private static BetSubscriber createUser(int pers) {
        BetSubscriber user = new BetSubscriber();

        user.setId(String.valueOf(pers));
        user.setName("Benutzer" + String.valueOf(pers));
        user.setMail("mail" + String.valueOf(pers) + "@qweqwe.de");
        user.setTel(String.valueOf(pers) + String.valueOf(pers) + String.valueOf(pers) + String.valueOf(pers) + String.valueOf(pers));

        return user;
    }

}
