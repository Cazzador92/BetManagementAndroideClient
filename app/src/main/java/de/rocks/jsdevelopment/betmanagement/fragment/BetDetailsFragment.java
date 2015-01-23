package de.rocks.jsdevelopment.betmanagement.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wrapp.floatlabelededittext.FloatLabeledEditText;

import de.rocks.jsdevelopment.betmanagement.R;
import de.rocks.jsdevelopment.betmanagement.model.Bet;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link de.rocks.jsdevelopment.betmanagement.fragment.BetDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link de.rocks.jsdevelopment.betmanagement.fragment.BetDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BetDetailsFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private Bet mParam1;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment bet_details.
     * @param bet
     */
    public static BetDetailsFragment newInstance(Bet bet) {
        BetDetailsFragment fragment = new BetDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, bet);
        fragment.setArguments(args);
        return fragment;
    }

    public BetDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Bet) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bet_details, container, false);
        setHasOptionsMenu(true);

        if (mParam1.id != null){
            fill    Ŷ<<<<<<<<<<<<<<<<<<<<<<<<AFields(view);
            setFieldsEnabled(view, false);
        }




        return view;
    }

    /**
     * set all Fields on BetDetailsFragment like b
     * @param view
     * @param b
     */
    private void setFieldsEnabled(View view, boolean b) {
        setFieldEnabled(view, b, R.id.betdetails_name);
        setFieldEnabled(view, b, R.id.betdetails_start);
        setFieldEnabled(view, b, R.id.betdetails_end);
        setFieldEnabled(view, b, R.id.betdetails_subscriber);
        setFieldEnabled(view, b, R.id.betdetails_deadline);
        setFieldEnabled(view, b, R.id.betdetails_description);
    }

    /**
     * set 1 field enable like b
     * @param view
     * @param b
     * @param betdetails_name
     */
    private void setFieldEnabled(View view, boolean b, int id) {
        TextView element = (TextView) view.findViewById(id);
        element.setEnabled(b);
    }

    private void fillFields(View view) {

        //TODO parameter übergeben
        if (mParam1.name != null) {
            TextView name = (TextView) view.findViewById(R.id.betdetails_name);

            name.setText(mParam1.getName());
        }
        //FloatLabeledEditText container = (FloatLabeledEditText) getView().findViewById(R.id.container_name);

        if (mParam1.description != null) {
            EditText description = (EditText) view.findViewById(R.id.betdetails_description);
            description.setText(mParam1.getDescription());
        }
    }


//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }


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
        public void onFragmentInteraction(Uri uri);
    }


    @Override
    public void  onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_bet_details, menu);
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

                Bet bet = new Bet();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, BetDetailsFragment.newInstance(bet))
                        .commit();
                break;
            case R.id.action_bar_bet_save:
                Toast.makeText(getActivity(), "You selected the save option", Toast.LENGTH_SHORT).show();
                Bet bet2 = new Bet();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, BetDetailsFragment.newInstance(bet2))
                        .commit();
                break;
            case R.id.action_bar_bet_delete:
                Toast.makeText(getActivity(), "You selected the delete option", Toast.LENGTH_SHORT).show();

                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, BetItemFragment.newInstance())
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
