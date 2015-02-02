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
import de.rocks.jsdevelopment.betmanagement.model.BetSubscriber;

/**
 * Created by i on 02.02.15.
 */
public class SubscriberListAdapter extends BaseAdapter {

        private Activity activity;
        private LayoutInflater inflater;
        private List<BetSubscriber> subscriberItems;

        //TODO Später wenn die Bilder aus dem Internet geladen werden
        // ImageLoader imageLoader = VolleySingleton.getInstance().getImageLoader();

        public SubscriberListAdapter(Activity activity, List<BetSubscriber> subscriberItems){
            this.activity = activity;
            this.subscriberItems = subscriberItems;
        }

        @Override
        public int getCount() {
            return subscriberItems.size();
        }

        @Override
        public Object getItem(int location) {
            return subscriberItems.get(location);
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
                convertView = inflater.inflate(R.layout.listitem_subscriber, null);


            //TODO diese funktion setzt für das eine Item die Werte rein und gibt das View zurück

//            TextView name = (TextView) convertView.findViewById(R.id.bet_name);
//            TextView subscriber = (TextView) convertView.findViewById(R.id.bet_subscriber);
//            TextView start = (TextView) convertView.findViewById(R.id.bet_start);
//            TextView end = (TextView) convertView.findViewById(R.id.bet_end);

            //TODO ergänzen
//            BetSubscriber subscriber = subscriberItems.get(position);
//
//            name.setText(subscriber.getName());
//            //subscriber.setText(subscriber.);
//            start.setText("21.01.2015");
//            end.setText("25.01.2015");

            return convertView;
        } 

}
