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
import android.widget.Toast;

import de.rocks.jsdevelopment.betmanagement.R;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link de.rocks.jsdevelopment.betmanagement.fragment.BetDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link de.rocks.jsdevelopment.betmanagement.fragment.BetDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BetDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bet_details.
     */
    // TODO: Rename and change types and number of parameters
    public static BetDetailsFragment newInstance(String param1, String param2) {
        BetDetailsFragment fragment = new BetDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bet_details, container, false);
    }


    /*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
*/

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


                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, BetDetailsFragment.newInstance("Param1", "Param2"))
                        .commit();
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