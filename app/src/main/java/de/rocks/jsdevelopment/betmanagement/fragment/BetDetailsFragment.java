package de.rocks.jsdevelopment.betmanagement.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wrapp.floatlabelededittext.FloatLabeledEditText;

import de.rocks.jsdevelopment.betmanagement.R;
import de.rocks.jsdevelopment.betmanagement.activity.ContactActivity;
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
    private static final String ARG_PARAM1 = "mBet";
    private static final String ARG_PARAM2 = "mEnabled";

    private Bet mBet;
    private boolean mEnabled;

    private OnFragmentInteractionListener mListener;
    private Menu mMenu;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment bet_details.
     * @param bet
     */
    public static BetDetailsFragment newInstance(Bet bet, boolean enabled) {
        BetDetailsFragment fragment = new BetDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, bet);
        args.putSerializable(ARG_PARAM2, enabled);
        fragment.setArguments(args);
        return fragment;
    }

    public BetDetailsFragment() {
        // Required empty public constructor
      //  CreateSpecialTextFields();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBet = (Bet) getArguments().getSerializable(ARG_PARAM1);
            mEnabled = (boolean) getArguments().get(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bet_details, container, false);
        setHasOptionsMenu(true);

        CreateSpecialTextFields(view);

        //TODO prüfen warum die Labels bei den FloatLabels nicht angezeigt werden
        // (alternativ auf etwas anderes ausweichen)

        if (mBet.id != null){
            fillFields(view);
            //setFieldsEnabled(view, false);
        }

        setFieldsEnabled(view, mEnabled);


        return view;
    }


    /**
     * Create the Datetimepicker for the start and end date of the bet.
     */
    public void CreateSpecialTextFields(View view)
    {
        final EditText start = (EditText) view.findViewById(R.id.betdetails_start);

        start.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Toast.makeText(getActivity(),"Get Focus",Toast.LENGTH_SHORT);
                    DialogFragment dialogFragment = new de.rocks.jsdevelopment.betmanagement.helper.DatePicker();
                    dialogFragment.show(getFragmentManager(), "OpenDatePicker");
                    start.setText("");
                }
            }
        });
    }

    /**
     * set all Fields on BetDetailsFragment like b
     * @param view
     * @param IsEnabled
     */
    private void setFieldsEnabled(View view, boolean IsEnabled) {
        setFieldEnabled(view, IsEnabled, R.id.betdetails_name);
        setFieldEnabled(view, IsEnabled, R.id.betdetails_start);
        setFieldEnabled(view, IsEnabled, R.id.betdetails_end);
        setFieldEnabled(view, IsEnabled, R.id.betdetails_subscriber);
        setFieldEnabled(view, IsEnabled, R.id.betdetails_deadline);
        setFieldEnabled(view, IsEnabled, R.id.betdetails_description);
    }

    private void setFieldsEnabled(Activity activity, boolean IsEnabled) {
        setFieldEnabled(activity, IsEnabled, R.id.betdetails_name);
        setFieldEnabled(activity, IsEnabled, R.id.betdetails_start);
        setFieldEnabled(activity, IsEnabled, R.id.betdetails_end);
        setFieldEnabled(activity, IsEnabled, R.id.betdetails_subscriber);
        setFieldEnabled(activity, IsEnabled, R.id.betdetails_deadline);
        setFieldEnabled(activity, IsEnabled, R.id.betdetails_description);
    }

    /**
     * set 1 field enable like b
     * @param view
     * @param IsEnabled
     * @param ViewId
     */
    private void setFieldEnabled(View view, boolean IsEnabled, int ViewId) {
        TextView element = (TextView) view.findViewById(ViewId);
        element.setEnabled(IsEnabled);
    }

    private void setFieldEnabled(Activity activity, boolean IsEnabled, int ViewId) {
        TextView element = (TextView) activity.findViewById(ViewId);
        element.setEnabled(IsEnabled);
    }

    private void fillFields(View view) {

        //TODO parameter übergeben
        if (mBet.name != null) {
            TextView name = (TextView) view.findViewById(R.id.betdetails_name);

            name.setText(mBet.getName());
        }
        //FloatLabeledEditText container = (FloatLabeledEditText) getView().findViewById(R.id.container_name);

        if (mBet.description != null) {
            EditText description = (EditText) view.findViewById(R.id.betdetails_description);
            description.setText(mBet.getDescription());
        }

        if(mBet.start != null){
            EditText start = (EditText) view.findViewById(R.id.betdetails_start);
            start.setText(mBet.getStart().toString());
        }

        if(mBet.end != null){
            EditText end = (EditText) view.findViewById(R.id.betdetails_end);
            end.setText(mBet.getEnd().toString());
        }

        if (!mBet.subscriber.isEmpty()){
            EditText subscriber = (EditText) view.findViewById(R.id.betdetails_subscriber);
            subscriber.setText(mBet.getSubscriberList());
            //subscriber.setText(ContactActivity.getkram());
        }

        if (mBet.deadline != null){
            EditText deadline = (EditText) view.findViewById(R.id.betdetails_deadline);
            deadline.setText(mBet.getDeadline().toString());
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

//    @Override
//    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//        Toast.makeText(getActivity(),"Text bei onDataSet",Toast.LENGTH_SHORT);
//    }

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
        mMenu = menu;
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        FragmentManager fragmentManager = getFragmentManager();
        MenuInflater inflater = getActivity().getMenuInflater();

        //TODO nur die benötigten optionen in allen Fragmenten lassen
        switch (item.getItemId())
        {
            case R.id.action_bar_bet_add:
                Toast.makeText(getActivity(), "Neue Wette anlegen", Toast.LENGTH_SHORT).show();

                Bet bet = new Bet();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, BetDetailsFragment.newInstance(bet, false))
                        .commit();
                break;
            case R.id.action_bar_bet_save:

                //TODO Wette speichern Liste, DB, ......

                Toast.makeText(getActivity(), "Wette Speichern", Toast.LENGTH_SHORT).show();

                mEnabled = false;
                setFieldsEnabled(getActivity(), mEnabled);

                mMenu.clear();
                inflater.inflate(R.menu.menu_bet_details, mMenu);

                break;
            case R.id.action_bar_bet_delete:
                Toast.makeText(getActivity(), "Wette löschen", Toast.LENGTH_SHORT).show();


                //TODO löschen und zurück zur Liste springen


                break;
            case R.id.action_bar_bet_edit:
                Toast.makeText(getActivity(), "Wette bearbeiten", Toast.LENGTH_SHORT).show();


                mEnabled = true;
                setFieldsEnabled(getActivity(), mEnabled);

                BetDetailsFragment fragment = BetDetailsFragment.newInstance(mBet, true);

 //               // funktioniert auch. Dabei wird ein neues Fragment erstellt und das alte damit ersetzt.
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.container, fragment)
//                        .commit();

                mMenu.clear();
                inflater.inflate(R.menu.menu_bet_edit, mMenu);


                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
