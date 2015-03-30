package com.example.jimmy.test;

/**
 * Created by jimmy on 26/03/15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MobileArrayAdapter extends ArrayAdapter<Player> {
    private final Context context;
    private final Player[] values;

    public MobileArrayAdapter(Context context, Player[] values) {
        super(context, R.layout.list_mobile, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_mobile, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.scorename);
        TextView score = (TextView) rowView.findViewById(R.id.scorescore);
        name.setText(values[position].getName());
        score.setText(String.valueOf(values[position].getScoreAmount()));
        // Change icon based on name
        String s = values[position].toString();

        return rowView;
    }
}