package de.rocks.jsdevelopment.betmanagement.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import de.rocks.jsdevelopment.betmanagement.R;
import de.rocks.jsdevelopment.betmanagement.adapter.SubscriberListAdapter;
import de.rocks.jsdevelopment.betmanagement.helper.Util;

/**
 * Created by Sebastian on 22.03.2015.
 */
public class SQLiteFragment extends Fragment
{
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SQLiteFragment.
     */
    public static SQLiteFragment newInstance(){
    /*BetDetailsFragment fragment = new BetDetailsFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    */
        SQLiteFragment fragment = new SQLiteFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sqlitetest, container, false);

        Activity activity = getActivity();
        setHasOptionsMenu(true);

  /*      // Set the adapter
        listView = (ListView) view.findViewById(R.id.subscriber_container);
        adapter = new SubscriberListAdapter(activity, mBet.getSubscriber());
        listView.setAdapter(adapter);

        Util.setListViewHeightBasedOnItems(listView);

        listView.setOnItemClickListener(this);
*/
        return view;
    }
}
