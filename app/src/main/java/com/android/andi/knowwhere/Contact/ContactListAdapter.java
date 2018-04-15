package com.android.andi.knowwhere.Contact;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.andi.knowwhere.Chat.ChatWindow;
import com.android.andi.knowwhere.MainFragments.ContactFragment;
import com.android.andi.knowwhere.R;

import static android.support.v4.content.ContextCompat.createDeviceProtectedStorageContext;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Andi Xu on 3/1/18.
 */

public class ContactListAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder)holder).bindView(position);

    }

    @Override
    public int getItemCount() {
        return ContactData.title.length;
    }



    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mText;
        private ImageView mImage;

        public ListViewHolder(View itemView){
            super(itemView);
            mText=(TextView)itemView.findViewById(R.id.itemText);
            mImage=(ImageView)itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(view.getContext(), ChatWindow.class);
                    view.getContext().startActivity(intent);
                }
            });

        }

        public void bindView(int position){
            mText.setText(ContactData.title[position]);
            mImage.setImageResource(ContactData.picturePath[position]);

        }

        public void onClick(View view) {

        }
    }
}















