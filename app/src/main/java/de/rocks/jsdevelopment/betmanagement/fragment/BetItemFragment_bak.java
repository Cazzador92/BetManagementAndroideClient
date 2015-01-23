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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.rocks.jsdevelopment.betmanagement.MainActivity;
import de.rocks.jsdevelopment.betmanagement.R;
import de.rocks.jsdevelopment.betmanagement.activity.BetDetailsActivity;
import de.rocks.jsdevelopment.betmanagement.adapter.BetListAdapter;
import de.rocks.jsdevelopment.betmanagement.fragment.dummy.DummyBetContent;
import de.rocks.jsdevelopment.betmanagement.model.Bet;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class BetItemFragment_bak extends Fragment implements AbsListView.OnItemClickListener{

    private static final String TAG = BetItemFragment_bak.class.getSimpleName();

    // wird vielleicht später gebraucht
    //private ProgressDialog pDialog;
    private List<Bet> betList = new ArrayList<Bet>();
    private ListView listView;
    private BetListAdapter adapter;

    private OnFragmentInteractionListener mListener;

    public static BetItemFragment_bak newInstance() {
        BetItemFragment_bak fragment = new BetItemFragment_bak();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BetItemFragment_bak() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new BetListAdapter(getActivity(), DummyBetContent.ITEMS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_betitem, container, false);

        Activity activity = getActivity();
        setHasOptionsMenu(true);

        // Set the adapter
        listView = (ListView) view.findViewById(R.id.betlist_container);
        listView.setAdapter(adapter);

        // Set OnItemClickListener so we can be notified on item clicks
        listView.setOnItemClickListener(this);

        return view;
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(DummyBetContent.ITEMS.get(position).id);
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
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

        //TODO nur die benötigten optionen in allen Fragmenten lassen
        switch (item.getItemId())
        {
            case R.id.action_bar_bet_add:
                Toast.makeText(getActivity(), "You selected the camera option", Toast.LENGTH_SHORT).show();


                startActivity(new Intent(getActivity(), BetDetailsActivity.class));

                break;
            case R.id.action_bar_bet_save:
                Toast.makeText(getActivity(), "You selected the save option", Toast.LENGTH_SHORT).show();

                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, BetDetailsFragment.newInstance("Param1", "Param2"))
                        .commit();
                break;
            case R.id.action_bar_bet_delete:
                Toast.makeText(getActivity(), "You selected the delete option", Toast.LENGTH_SHORT).show();

                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, BetItemFragment_bak.newInstance())
                        .commit();
                break;
            case R.id.action_bar_bet_edit:
                Toast.makeText(getActivity(), "You selected the edit option", Toast.LENGTH_SHORT).show();

                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, BetEditFragment.newInstance("Param1", "Param2"))
                        .commit();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
