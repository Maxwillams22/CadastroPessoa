package com.example.maxwillams.cadastropessoa;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maxwillams.cadastropessoa.R;

import java.util.ArrayList;

/**
 * Created by Leonan-Mac on 9/23/16.
 */
public class CustomAdapter extends ArrayAdapter<Person> {

    private Context context;

    public CustomAdapter(Context context, int resource, ArrayList<Person> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        Person p = getItem(position);

        if (converView == null) {
            converView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }

        TextView txtName = (TextView) converView.findViewById(R.id.txt_label);
        TextView txtId = (TextView) converView.findViewById(R.id.txt_label_id);
        TextView txtlast = (TextView) converView.findViewById(R.id.txt_last);


        txtName.setText(p.getFirstName());
        txtlast.setText(p.getLastname());

        txtId.setText(p.getId().toString());

        return converView;
    }

}