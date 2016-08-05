package com.saarang.android.eventsaarang;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
//TODO-app is crashing. Find out why!
/**
 * Created by Abhi on 8/5/2016.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    //List which will hold the data
    private List<String> titles; //TODO - draw the class

    //Constructor - assigns values to the list of data items
    public EventAdapter(List<String> titles) {
        this.titles = titles;//TODO
    }

    /*This is the class that implements the ViewHolder*/
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title; //TODO

        private final Context context;
        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            title  = (TextView) itemView.findViewById(R.id.tv_eventTitle);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            //image = (ImageView) itemView.findViewById(R.id.image1);
        }
        //Function called when user clicks on any ViewHolder in RecyclerView
        @Override
        public void onClick(View v) {

                Toast.makeText(context, "Event Page!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, SecondActivity.class);
                context.startActivity(intent);

            //the card click will be taken care of inside the card recycler view
        }
    }

    //onCreateViewHolder is called whenever a new instance of our ViewHolder class is created.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new ViewHolder(view);
    }

    //onBindViewHolder is called when the view is bound with the data, i.e. when the data is shown in the UI.
    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        holder.title.setText(this.titles.get(position));
        //      holder.subtitle.setText(this.dataList.get(position).getSubtitle());
//        holder.position = position;
        //int id = context.getResources().getIdentifier("saarang1", "drawable", "R");
        //holder.image.setImageResource(id);
    }

    //Gets the number of items in list
    //@Override
    public int getItemCount() {
        return titles.size();
    }
}
