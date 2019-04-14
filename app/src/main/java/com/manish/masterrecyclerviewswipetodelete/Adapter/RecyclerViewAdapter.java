package com.manish.masterrecyclerviewswipetodelete.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.manish.masterrecyclerviewswipetodelete.Model.DataModel;
import com.manish.masterrecyclerviewswipetodelete.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<DataModel> data;
    Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mversion_name,mversion_code,mversion_release_date,version_type;

        ImageView mversion_image;
        LinearLayout relativeLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            mversion_name = itemView.findViewById(R.id.version_name);
            mversion_code = itemView.findViewById(R.id.version_code);
            mversion_release_date = itemView.findViewById(R.id.version_release_date);
            mversion_image = itemView.findViewById(R.id.version_image);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            version_type = itemView.findViewById(R.id.version_type);
        }
    }

    public RecyclerViewAdapter(Context context,ArrayList<DataModel> data) {
        this.data = data;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_cardview, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DataModel dataModel = data.get(position);
        holder.mversion_name.setText(dataModel.getName());
        holder.mversion_code.setText("API: "+dataModel.getVersion_number());
        holder.mversion_release_date.setText(dataModel.getFeature());
        holder.version_type.setText(dataModel.getType());

        Picasso
                .get()
                .load(dataModel.getImage_uri())
                .into(holder.mversion_image);


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext," Swipe Left to Remove Item "+dataModel.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(DataModel item, int position)
    {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<DataModel> getData() {
        return data;
    }
}

