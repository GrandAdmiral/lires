package com.lires.jimmy;

/**
 * Created by jimmy on 26/03/15.
 */

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

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
        int position1 = position + 1;
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        String s1 = "<![<FONT COLOR=\"#515045\">" + position1 + ".  " + values[position].getName() + "</FONT>";
        String s2 = "<![<FONT COLOR=\"#000000\">" + String.valueOf(formatter.format(values[position].getScoreAmount())) + "  Λ</FONT><FONT COLOR=\"#d8361c\">Ι</FONT><FONT COLOR=\"#62b41b\">Ρ</FONT><FONT COLOR=\"#1a86d9\">Ε</FONT><FONT COLOR=\"#515045\">Σ</FONT>";
        name.setText(Html.fromHtml(s1));
        score.setText(Html.fromHtml(s2));
        // Change icon based on name
        String s = values[position].toString();

        return rowView;
    }
}