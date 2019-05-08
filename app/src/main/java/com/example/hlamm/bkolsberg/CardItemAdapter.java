package com.example.hlamm.bkolsberg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.myViewHolder> {
    Context mContext;
    List<CardItem> mData;

    /**
     * TODO: OnClickItemLister for Card
     * Activated if user clicks on the card.
     * Opens up @DisplayBildungsgangActivtiy for specific Bildungsgang
     */
    /*private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };*/


    /**
     * Activated if user clicks the Star-Button of the Card
     * Marks the selected Bildungsgang as a favorite or unmarks it.
     * Gives user a response by changing the picture of the borded-star to the yellow-filled-star
     * @param view
     */
    private View.OnClickListener onClickListenerFav = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            mData.get(position).swapFavorite();
            notifyDataSetChanged();
        }
    };

    /**
     * Opens DisplayBildugnsgangActivity for the selected Bildungsgang
     */
    private View.OnClickListener onClickListenerDetail = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            Intent intent = new Intent(mContext, DisplayBildungsgangActivity.class);
            Bundle b = new Bundle();
            b.putInt("id", mData.get(position).getId());
            intent.putExtras(b);
            mContext.startActivity(intent);

        }
    };

    private View.OnClickListener onClickListenerDetailBeschreibung = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            Intent intent = new Intent(mContext, DisplayBildungsgangActivity.class);
            Bundle b = new Bundle();
            b.putInt("id", mData.get(position).getId());
            intent.putExtras(b);
            mContext.startActivity(intent);

        }
    };


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

        holder.tv_beschreibung.setText(mData.get(position).getBeschreibung());
        holder.tv_bezeichnung.setText(mData.get(position).getBezeichnung());

        if(mData.get(position).isFavorite()) holder.btn_markAsFavorite.setBackgroundResource(R.drawable.ic_outline_star_24px);
        else holder.btn_markAsFavorite.setBackgroundResource(R.drawable.ic_outline_star_border_24px);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView tv_bezeichnung;
        TextView tv_beschreibung;
        Button btn_markAsFavorite;

        public myViewHolder(View itemView) {
            super(itemView);
            tv_bezeichnung = itemView.findViewById(R.id.bezeichnung);
            tv_bezeichnung.setTag(this);
            tv_bezeichnung.setOnClickListener(onClickListenerDetail);
            tv_beschreibung = itemView.findViewById(R.id.beschreibung);
            tv_beschreibung.setTag(this);
            tv_beschreibung.setOnClickListener(onClickListenerDetailBeschreibung);
            btn_markAsFavorite = itemView.findViewById(R.id.btn_MarkAsFavorite);
            btn_markAsFavorite.setTag(this);
            btn_markAsFavorite.setOnClickListener(onClickListenerFav);

        }
    }
}
