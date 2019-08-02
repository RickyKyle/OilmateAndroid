package com.rickykyle.oilmate.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickykyle.oilmate.R;
import com.rickykyle.oilmate.network.responses.Reading;
import com.rickykyle.oilmate.utilities.Globals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 * This class is an adapter for the RecyclerView used in the ReadingList Activity,
 * it determines the appearance and behaviour of the RecyclerView.
 */
public class ReadingListAdapter extends RecyclerView.Adapter<ReadingListAdapter.ViewHolder> {

    // Holds the readings.
    List<Reading> readingDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView readingItem;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            // Initialise views.
            readingItem = v.findViewById(R.id.readingText);
            imageView = v.findViewById(R.id.button);

        }
    }

    // Assign data to the dataset.
    public ReadingListAdapter(List<Reading> dataset){
        readingDataset = dataset;
    }

    @NonNull
    @Override
    public ReadingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate layout on creation.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_container, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReadingListAdapter.ViewHolder viewHolder, int i) {
        // Get the reading.
        Reading reading = readingDataset.get(i);
        TextView textView = viewHolder.readingItem;

        // Call the method to set the notification circle.
        setWarningCircleColour(reading, viewHolder);

        // Format reading.
        String readingAsString = Float.toString(reading.getReading());
        String output = "Date: " + formattedDate(reading.getDate()) + "\nTime: " +
                reading.getTime() + "\nReading: " + readingAsString + " litres";

        // Set reading to card.
        textView.setText(output);
    }

    @Override
    public int getItemCount() {
        return readingDataset.size();
    }

    // Format date.
    public String formattedDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }

    // Determines the conditions which change the warning circle colour.
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
