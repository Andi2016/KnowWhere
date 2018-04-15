package com.android.andi.knowwhere.Chat;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.andi.knowwhere.Contact.ContactData;
import com.android.andi.knowwhere.Contact.ContactListAdapter;
import com.android.andi.knowwhere.R;

/**
 * Created by Andi Xu on 3/4/18.
 */

public class ChatListAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list_item, parent, false);
        return new ChatListAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ChatListAdapter.ListViewHolder)holder).bindView(position);

    }

    @Override
    public int getItemCount() {
        return ChatData.names.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nameText;
        private TextView msgText;
        private TextView timeText;
        private ImageView mImage;

        public ListViewHolder(View itemView){
            super(itemView);
            nameText=(TextView)itemView.findViewById(R.id.name_chat_list);
            msgText=(TextView)itemView.findViewById(R.id.message_chat_list);
            timeText=(TextView)itemView.findViewById(R.id.time_chat_list);
            mImage=(ImageView)itemView.findViewById(R.id.image_chat_list);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(view.getContext(), ChatWindow.class);
                    view.getContext().startActivity(intent);
                }
            });

        }

        public void bindView(int position){
            nameText.setText(ChatData.names[position]);
            msgText.setText(ChatData.msgs[position]);
            timeText.setText(ChatData.lastTime[position]);
            mImage.setImageResource(ContactData.picturePath[position]);
        }

        public void onClick(View view){

        }
    }
}
