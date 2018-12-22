package com.example.hlamm.bkolsberg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.myViewHolder> {
    Context mContext;
    List<CardItem> mData;


    public CardItemAdapter(Context mContext, List<CardItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public CardItemAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.card_item, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardItemAdapter.myViewHolder holder, int position) {

        holder.tv_beschreibung.setText("Dauer: " + Float.toString(mData.get(position).getDauer()) + " Jahr/e");
        holder.tv_bezeichnung.setText(mData.get(position).getBezeichnung());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView tv_bezeichnung;
        TextView tv_beschreibung;

        public myViewHolder(View itemView) {
            super(itemView);
            tv_bezeichnung = itemView.findViewById(R.id.bezeichnung);
            tv_beschreibung = itemView.findViewById(R.id.beschreibung);
        }
    }
}
