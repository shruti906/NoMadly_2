package com.example.nomadly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    Context context;
    ArrayList<TripModel> tripList;
    boolean showDate;

    public TripAdapter(Context context, ArrayList<TripModel> tripList, boolean showDate) {
        this.context = context;
        this.tripList = tripList;
        this.showDate = showDate;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tripName;
        TextView tripDate;
        ImageView tripImage;

        public ViewHolder(View view) {
            super(view);
            tripName = view.findViewById(R.id.tripName);
            tripImage = view.findViewById(R.id.tripImage);
            try {
                tripDate = view.findViewById(R.id.tripDate);
            } catch (Exception e) {
                tripDate = null;
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                showDate ? R.layout.item_past_trip : R.layout.item_popular_trip, parent, false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TripModel trip = tripList.get(position);
        holder.tripName.setText(trip.name);
        holder.tripImage.setImageResource(trip.imageResId);

        if (showDate && holder.tripDate != null) {
            holder.tripDate.setText(trip.dateRange);
            holder.tripDate.setVisibility(View.VISIBLE);
        } else if (holder.tripDate != null) {
            holder.tripDate.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }
}
