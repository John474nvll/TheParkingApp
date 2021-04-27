package com.example.theparkingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static java.security.AccessController.getContext;

public class LotsAdapter extends RecyclerView.Adapter<LotsAdapter.ViewHolder> {
    private String TAG = "Lot";
    private Context context;
    private List <ParkingLot> parkingLots;
    public LotsAdapter(Context context,List<ParkingLot> parkingLots)
    {
        this.context=context;
        this.parkingLots=parkingLots;
    }

    //Pass in the List of Parking Lots

    //For each row inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_selection,parent,false);
        return new ViewHolder(view);
    }
    //Bind Values Based on the position of elements
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //Get the data at the view Holder
        ParkingLot parkingLot= parkingLots.get(position);
        //Bind the data with View Holder
        holder.bind(parkingLot);
    }

    @Override
    public int getItemCount() {
        return parkingLots.size();
    }
    public void clear() {
        parkingLots.clear();
        notifyDataSetChanged();
    }


    //Define a ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLotName;
        TextView tvFreeSpace;
        Button btnDirection;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLotName=itemView.findViewById(R.id.tvLotName);
            tvFreeSpace=itemView.findViewById(R.id.tvFreeSpace);
            btnDirection=itemView.findViewById(R.id.btnDirection);
        }

        public void bind(ParkingLot parkingLot) {
            tvLotName.setText(parkingLot.getKeyLotName());
            tvFreeSpace.setText(String.valueOf(parkingLot.getKeyFreeSpace())+" Available Spaces");
//            Log.i(TAG, parkingLot.getKeyLotNumber()+ "  latitude: " + parkingLot.getKeyLotLat() + "  longitude: " + parkingLot.getKeyLotLong());

            btnDirection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String lat = String.valueOf(parkingLot.getKeyLotLat());
                    String lon = String.valueOf(parkingLot.getKeyLotLong());
                    String Uri_string = ("http://maps.google.com/maps?&api=1&map_action=map&layer=traffic&dir_action=navigate&addr="+lon+","+lat);
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse(Uri_string));
                    context.startActivity(intent);
//                    Intent i = new Intent(context , GetDirection.class);
//
//                    i.putExtra("Lot", parkingLot.getKeyLotName() );
//                    i.putExtra("lot_long", parkingLot.getKeyLotLong());
//                    i.putExtra("lot_lat", parkingLot.getKeyLotLat());
//                    context.startActivity(i);

                }
            });
        }
    }
}
