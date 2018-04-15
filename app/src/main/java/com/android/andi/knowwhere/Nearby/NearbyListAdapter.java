package com.android.andi.knowwhere.Nearby;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.andi.knowwhere.Chat.ChatWindow;
import com.android.andi.knowwhere.Contact.ContactData;
import com.android.andi.knowwhere.Contact.ContactListAdapter;
import com.android.andi.knowwhere.R;

/**
 * Created by Andi Xu on 3/4/18.
 */

public class NearbyListAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.nearby_list_item, parent, false);
        return new NearbyListAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((NearbyListAdapter.ListViewHolder)holder).bindView(position);

    }

    @Override
    public int getItemCount() {
        return NearbyData.names.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView _nameText;
        private TextView _distText;
        private TextView _postText;
        private ImageView mImage;

        public ListViewHolder(View itemView){
            super(itemView);
            _nameText=(TextView)itemView.findViewById(R.id.name_nearby_list);
            _distText=(TextView)itemView.findViewById(R.id.dist_nearby_list);
            _postText=(TextView)itemView.findViewById(R.id.post_nearby_list);
            mImage=(ImageView)itemView.findViewById(R.id.image_nearby_list);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(view.getContext(), ChatWindow.class);
                    view.getContext().startActivity(intent);
                }
            });

        }

        public void bindView(int position){
            _nameText.setText(NearbyData.names[position]);
            _distText.setText(NearbyData.distances[position]);
            _postText.setText(NearbyData.posts[position]);
            mImage.setImageResource(ContactData.picturePath[position]);
        }

        public void onClick(View view){

        }
    }
}
