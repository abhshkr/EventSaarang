package com.saarang.android.eventsaarang;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
/**
 * Created by Abhi on 8/5/2016.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    //List which will hold the data
    private List<String> titles; //TODO - draw the list of objects from the CategoryAdapter class
    private int InflaterID;

    //Constructor - assigns values to the list of data items
    public EventAdapter(List<String> titles, int InflaterID) {
        this.titles = titles; //TODO
        this.InflaterID = InflaterID;
    }

    /*This is the class that implements the ViewHolder*/
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title,description; //TODO -add extra fields
        ImageView favStar;

        private final Context context;
        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
           if(InflaterID==R.layout.event_item)
            {
                title = (TextView) itemView.findViewById(R.id.tv_eventTitle);
                favStar = (ImageView) itemView.findViewById(R.id.iv_eventStar);

            }
            else if(InflaterID ==R.layout.eventlist_item){
               title = (TextView) itemView.findViewById(R.id.tv_eventListTitle);
               favStar = (ImageView) itemView.findViewById(R.id.iv_eventListStar);
           }

            //TODO - add this
//            if(isFavorite)
//                Glide.with(context).load(R.drawable.ic_star_black_24dp).into(fab);
//            else
//                Glide.with(context).load(R.drawable.ic_star_border_black_24dp).into(fab);
            favStar.setClickable(true);
            favStar.setOnClickListener(this);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            //image = (ImageView) itemView.findViewById(R.id.image1);
        }
        //Function called when user clicks on any ViewHolder in RecyclerView
        @Override
        public void onClick(View v) {
                if(v.getId()==favStar.getId()){
                    Toast.makeText(context,"Preference changed!",Toast.LENGTH_SHORT).show();
                    if (true)
                        favStar.setImageResource(R.drawable.ic_star_white_24dp);
                    else
                        favStar.setImageResource(R.drawable.ic_star_white_24dp);
                }
            else {
                    Toast.makeText(context, "Event Page!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, DetailsActivity.class);
                    context.startActivity(intent);      //TODO - also have to send the position
                }
        }
    }

    //onCreateViewHolder is called whenever a new instance of our ViewHolder class is created.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(InflaterID, parent, false);
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
