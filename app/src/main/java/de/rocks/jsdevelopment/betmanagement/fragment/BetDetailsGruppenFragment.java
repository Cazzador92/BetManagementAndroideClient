package de.rocks.jsdevelopment.betmanagement.fragment;

//region Imports
import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import de.rocks.jsdevelopment.betmanagement.R;
import de.rocks.jsdevelopment.betmanagement.adapter.SubscriberListAdapter;
import de.rocks.jsdevelopment.betmanagement.helper.Util;
import de.rocks.jsdevelopment.betmanagement.model.Bet;
//endregion

/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link de.rocks.jsdevelopment.betmanagement.fragment.BetDetailsGruppenFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link de.rocks.jsdevelopment.betmanagement.fragment.BetDetailsGruppenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BetDetailsGruppenFragment extends Fragment implements AdapterView.OnItemClickListener {


    //region Variables
    private static final String ARG_PARAM1 = "mBet";
    private static final String ARG_PARAM2 = "mEnabled";

    protected Bet mBet;
    protected boolean mEnabled;

    protected OnFragmentInteractionListener mListener;
    protected Menu mMenu;

    protected ListView listView;
    protected SubscriberListAdapter adapter;

    //region Getter Setter
    public boolean isEditEnabled(){ return mEnabled; }
    //endregion

    //endregion

    //region Constructor
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment bet_details.
     * @param bet
     */
    public static BetDetailsGruppenFragment newInstance(Bet bet, boolean enabled) {
        BetDetailsGruppenFragment fragment = new BetDetailsGruppenFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, bet);
        args.putSerializable(ARG_PARAM2, enabled);
        fragment.setArguments(args);
        return fragment;
    }

    public BetDetailsGruppenFragment() {
        // Required empty public constructor
      //  CreateSpecialTextFields();
    }
    //endregion

    //region Lifecycle
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

        Activity activity = getActivity();
        setHasOptionsMenu(true);

        CreateSpecialTextFields(view);

        //TODO prüfen warum die Labels bei den FloatLabels nicht angezeigt werden
        // (alternativ auf etwas anderes ausweichen)

        if (mBet.id != null){
            fillFields(view);
            //setFieldsEnabled(view, false);
        }

        setFieldsEnabled(view, mEnabled);

        // Set the adapter
        listView = (ListView) view.findViewById(R.id.subscriber_container);
        adapter = new SubscriberListAdapter(activity, mBet.getSubscriber());
        listView.setAdapter(adapter);

        Util.setListViewHeightBasedOnItems(listView);

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
    //endregion

    //region Optionsmenu
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
                        .replace(R.id.frame_container, BetDetailsGruppenFragment.newInstance(bet, false))
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

                BetDetailsGruppenFragment fragment = BetDetailsGruppenFragment.newInstance(mBet, true);

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
    //endregion

    //region Interfaces
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
    //endregion

    //region Listview
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
    //endregion

    //region Helper
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
    //endregion

    /**
     * set all Fields on BetDetailsFragment like b
     * @param view
     * @param IsEnabled
     */
    private void setFieldsEnabled(View view, boolean IsEnabled) {
        setFieldEnabled(view, IsEnabled, R.id.betdetails_name);
        setFieldEnabled(view, IsEnabled, R.id.betdetails_start);
        setFieldEnabled(view, IsEnabled, R.id.betdetails_end);

        //setFieldEnabled(view, IsEnabled, R.id.listview_subscriber_container);

        setFieldEnabled(view, IsEnabled, R.id.betdetails_deadline);
        setFieldEnabled(view, IsEnabled, R.id.betdetails_description);
    }

    private void setFieldsEnabled(Activity activity, boolean IsEnabled) {
        setFieldEnabled(activity, IsEnabled, R.id.betdetails_name);
        setFieldEnabled(activity, IsEnabled, R.id.betdetails_start);
        setFieldEnabled(activity, IsEnabled, R.id.betdetails_end);

        //TODO onClickListener setzen/löschen beim editieren
        //setFieldEnabled(activity, IsEnabled, R.id.listview_subscriber_container);

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

    /**
     * Listener for Subscriber Items
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), mBet.getSubscriber(position).getName() + " angeklickt", Toast.LENGTH_SHORT).show();
        //TODO 2 Funktionalität + in die Activity auslagern (interface usw...)
    }


//    @Override
//    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//        Toast.makeText(getActivity(),"Text bei onDataSet",Toast.LENGTH_SHORT);
//    }



}
