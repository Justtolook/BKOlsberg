package com.example.hlamm.bkolsberg;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DisplayBildungsgangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bildungsgang);
    }

    public class ListViewAbschluss extends ListActivity {
        ArrayList<String> listItems = new ArrayList<>();
        ArrayAdapter<String> adapter;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_bildungsgang);
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
            setListAdapter(adapter);
            addItems();
        }

        /**
         * Add Items to ListView
         */
        public void addItems() {
            //Add Items here:
            listItems.add("Abitur");
            listItems.add("Berufsabschluss");

            //update ListView
            adapter.notifyDataSetChanged();
        }
    }
}


