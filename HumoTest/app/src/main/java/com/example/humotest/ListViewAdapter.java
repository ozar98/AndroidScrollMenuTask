package com.example.humotest;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class ListViewAdapter  extends ArrayAdapter<JSONObject> {

    final int VIEW_TYPE_ITEM=1;
    final int VIEW_TYPE_LOADING=0;
    int listLayout;
    ArrayList<JSONObject> usersList;
    Context context;



    public ListViewAdapter(Context context, int listLayout , int field, ArrayList<JSONObject> usersList) {
        super(context, listLayout, field, usersList);
        this.context = context;
        this.listLayout=listLayout;
        this.usersList = usersList;
    }

    @Override
    public int getItemViewType(int position) {
        return usersList.get(position)==null? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM ;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridViewItem = inflater.inflate(listLayout, null, false);
        //email -photo url
        final ImageView email = gridViewItem.findViewById(R.id.imageView);
        TextView name = gridViewItem.findViewById(R.id.textViewName);
        try {


            email.setAlpha((float) 0.1);

            email.setOnClickListener(new View.OnClickListener(){
                int blurred = 0;
                public void onClick(View v){
                    if (blurred==0){
                        blurred=1;
                        email.animate().rotation(0f).setListener(new AnimatorListenerAdapter()
                        {
                            @Override
                            public void onAnimationEnd(Animator animation)
                            {
                                email.setRotationY(270f);
                                email.animate().rotationY(360f).setListener(null);
                            }
                        });
                    }

                    email.setAlpha((float) 1.0);

                }
            });
            Glide.with(context).load(usersList.get(position).getString("photo")).into(email);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        name.setText("");



        return gridViewItem;
    }


}
