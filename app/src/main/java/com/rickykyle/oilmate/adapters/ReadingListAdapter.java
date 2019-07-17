package com.rickykyle.oilmate.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.entities.Reading;
import com.rickykyle.oilmate.utilities.Globals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReadingListAdapter extends RecyclerView.Adapter<ReadingListAdapter.ViewHolder> {

    List<Reading> readingDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView readingItem;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            readingItem = v.findViewById(R.id.readingText);
            imageView = v.findViewById(R.id.button);

        }
    }

    public ReadingListAdapter(List<Reading> dataset){
        readingDataset = dataset;
    }

    @NonNull
    @Override
    public ReadingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_container, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReadingListAdapter.ViewHolder viewHolder, int i) {

        Reading reading = readingDataset.get(i);
        TextView textView = viewHolder.readingItem;
        setWarningCircleColour(reading, viewHolder);
        String readingAsString = Float.toString(reading.getReading());
        String output = "Date: " + formattedDate(reading.getDate()) + "\nTime: " +
                reading.getTime() + "\nReading: " + readingAsString + " litres";
        textView.setText(output);
    }

    @Override
    public int getItemCount() {
        return readingDataset.size();
    }

    public String formattedDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }

    public void setWarningCircleColour(Reading reading, ReadingListAdapter.ViewHolder viewHolder){
        ImageView imageView = viewHolder.imageView;

        if (reading.getReading() > Globals.tankSize / 2){
            imageView.setImageResource(R.drawable.greencirlce);
        } else if (reading.getReading() <= Globals.tankSize / 2 && reading.getReading() > Globals.tankSize / 4){
            imageView.setImageResource(R.drawable.ambercircle);
        } else {
            imageView.setImageResource(R.drawable.redcircle);
        }
    }
}
