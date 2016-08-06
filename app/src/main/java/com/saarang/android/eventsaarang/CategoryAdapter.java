package com.saarang.android.eventsaarang;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Abhi on 8/5/2016.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    //List which will hold the data
    private List<String> titles;

    //Constructor - assigns values to the list of data items
    public CategoryAdapter(List<String> titles) {
        this.titles = titles;

    }

    /*This is the class that implements the ViewHolder*/
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView seeAll,title;
RecyclerView RV_eventHorizontal;
        private final Context context;
        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            RV_eventHorizontal = (RecyclerView) itemView.findViewById(R.id.rv_eventHorizontal);
            if (RV_eventHorizontal != null) {
                RV_eventHorizontal.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
            }
            seeAll = (TextView) itemView.findViewById(R.id.tv_seeAll);
            title  = (TextView) itemView.findViewById(R.id.tv_catTitle);
            seeAll.setClickable(true);
            seeAll.setOnClickListener(this);
        }
        //Function called when user clicks on any ViewHolder in RecyclerView
        @Override
        public void onClick(View v) {
            //depends on the view clicked
            if(v.getId()==R.id.tv_seeAll) {
                Toast.makeText(context, "GridLayout Page!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, EventsInCategory.class);
                context.startActivity(intent);
            }
            //the card click will be taken care of inside the card recycler view
        }
    }

    //onCreateViewHolder is called whenever a new instance of our ViewHolder class is created.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    //onBindViewHolder is called when the view is bound with the data, i.e. when the data is shown in the UI.
    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        holder.title.setText(this.titles.get(position));
        List<String> titleList  = Arrays.asList("Music Events", "Classical Events", "Word Games");

        if (holder.RV_eventHorizontal != null) {
            holder.RV_eventHorizontal.setAdapter( new EventAdapter(titleList, R.layout.event_item) );
        }
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
