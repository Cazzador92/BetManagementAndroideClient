package de.rocks.jsdevelopment.betmanagement.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import de.rocks.jsdevelopment.betmanagement.R;
import de.rocks.jsdevelopment.betmanagement.model.Bet;

/**
 * Created by i on 22.01.15.
 */
public class BetListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Bet> betItems;

    //TODO Später wenn die Bilder aus dem Internet geladen werden
    // ImageLoader imageLoader = VolleySingleton.getInstance().getImageLoader();

    public BetListAdapter(Activity activity, List<Bet> betItems){
        this.activity = activity;
        this.betItems = betItems;
    }

    @Override
    public int getCount() {
        return betItems.size();
    }

    @Override
    public Object getItem(int location) {
        return betItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.listitem_bet, null);

        TextView name = (TextView) convertView.findViewById(R.id.bet_name);
        TextView subscriber = (TextView) convertView.findViewById(R.id.bet_subscriber);
        TextView start = (TextView) convertView.findViewById(R.id.bet_start);
        TextView end = (TextView) convertView.findViewById(R.id.bet_end);

        Bet bet = betItems.get(position);

        name.setText(bet.getName());
        subscriber.setText(bet.getSubscriberList());
        start.setText("21.01.2015");
        end.setText("25.01.2015");

        return convertView;
    }
}
