package com.manish.masterrecyclerviewswipetodelete;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.manish.masterrecyclerviewswipetodelete.Adapter.RecyclerViewAdapter;
import com.manish.masterrecyclerviewswipetodelete.Interface.SwipeToDeleteCallback;
import com.manish.masterrecyclerviewswipetodelete.Model.DataModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter mAdapter;
    ArrayList<String> stringArrayList = new ArrayList<>();
    CoordinatorLayout coordinatorLayout;
    ArrayList<DataModel> dataModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        dataModels= new ArrayList<>();
        RecyclerViewDataModel();
        enableSwipeToDeleteAndUndo();
    }


    private void RecyclerViewDataModel() {

        dataModels.add(new DataModel("Alpha", "Android 1.0", "1","September 23, 2008",R.drawable.one));
        dataModels.add(new DataModel("Beta", "Android 1.1", "2","February 9, 2009",R.drawable.two));
        dataModels.add(new DataModel("Cupcake", "Android 1.5", "3","April 27, 2009",R.drawable.cupcake));
        dataModels.add(new DataModel("Donut","Android 1.6","4","September 15, 2009",R.drawable.donut));
        dataModels.add(new DataModel("Eclair", "Android 2.0", "5","October 26, 2009",R.drawable.eclair));
        dataModels.add(new DataModel("Froyo", "Android 2.2", "8","May 20, 2010",R.drawable.froyo));
        dataModels.add(new DataModel("Gingerbread", "Android 2.3", "9","December 6, 2010",R.drawable.gingerbread));
        dataModels.add(new DataModel("Honeycomb","Android 3.0","11","February 22, 2011",R.drawable.honeycomb));
        dataModels.add(new DataModel("Ice Cream Sandwich", "Android 4.0", "14","October 18, 2011",R.drawable.ics));
        dataModels.add(new DataModel("Jelly Bean", "Android 4.2", "16","July 9, 2012",R.drawable.jellybean));
        dataModels.add(new DataModel("Kitkat", "Android 4.4", "19","October 31, 2013",R.drawable.kitkat));
        dataModels.add(new DataModel("Lollipop","Android 5.0","21","November 12, 2014",R.drawable.lollipop));
        dataModels.add(new DataModel("Marshmallow", "Android 6.0", "23","October 5, 2015",R.drawable.marsh));
        dataModels.add(new DataModel("Nougat", "Android 7.0", "24","August 22, 2016",R.drawable.nougat));
        dataModels.add(new DataModel("Oreo", "Android 8.1", "27","March 21, 2017",R.drawable.oreo));
        dataModels.add(new DataModel("Pie", "Android 9", "27","March 7, 2018",R.drawable.pie));

        mAdapter = new RecyclerViewAdapter(this,dataModels);
        recyclerView.setAdapter(mAdapter);
    }


    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int position = viewHolder.getAdapterPosition();
                final DataModel item = mAdapter.getData().get(position);

                mAdapter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAdapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }




}
