package com.example.gentleman.materialdesign.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gentleman.materialdesign.FruitActivity;
import com.example.gentleman.materialdesign.R;
import com.example.gentleman.materialdesign.data.Fruit;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gentleman on 2017/10/24.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mList = new ArrayList<>();

    private Context mContext;

    public FruitAdapter(List<Fruit> List) {
        this.mList = List;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mList.get(position);
                Intent intent = new Intent(mContext,FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                mContext.startActivity(intent);
            }
        });

        return  holder;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mList.get(position);
        holder.mImageView.setImageResource(fruit.getImageId());
        holder.mTextView.setText(fruit.getName());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView mCardView;
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
             mCardView = (CardView) itemView;
             mImageView = (ImageView) itemView.findViewById(R.id.fruit_image);
             mTextView = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }
}
