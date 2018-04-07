package com.android.andi.knowwhere.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.andi.knowwhere.R;
import com.android.andi.knowwhere.application.KnowWhere;
import com.android.andi.knowwhere.models.Chat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andi Xu on 4/6/18.
 */

public class  ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    private List<Chat> mChatList;
    private Context mContext;
    private KnowWhere mApp;
    private ChatListAdapter.ChatListAdapterListener mListener;

    public ChatListAdapter(Context context, ChatListAdapter.ChatListAdapterListener listener){
        mContext = context;
        mChatList = new ArrayList<>();
        mApp = (KnowWhere) mContext.getApplicationContext();
        mListener = listener;
    }

    public void addItems(ArrayList<Chat> list){
        mChatList.clear();
        mChatList.addAll(list);
    }

    public ArrayList<Chat> getItems(){
        return (ArrayList<Chat>) mChatList;
    }

    public void clearItems(){
        mChatList.clear();
    }

    @Override
    public ChatListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //inflate the custom layout
        View chatView = inflater.inflate(R.layout.chat_list_item, parent, false);

        //return a new holder instance
        return new ViewHolder(chatView);
    }

    @Override
    public void onBindViewHolder(ChatListAdapter.ViewHolder holder, final int position) {
        Chat chat = mChatList.get(position);
        //change later
        holder.mHeadImage.setImageResource(R.drawable.head_alice);

        holder.mNameView.setText(chat.getGroupname());
        holder.mMessageView.setText(chat.getSender()+": "+ chat.getContent());
        holder.mTimeView.setText(chat.getTimeStamp());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.OnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChatList.size();
    }

    //View holder class
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mHeadImage;
        private TextView mNameView;
        private TextView mMessageView;
        private TextView mTimeView;

        public ViewHolder(final View view){
            super(view);
            mHeadImage = view.findViewById(R.id.image_chat_list);
            mNameView = view.findViewById(R.id.name_chat_list);
            mMessageView = view.findViewById(R.id.message_chat_list);
            mTimeView = view.findViewById(R.id.time_chat_list);
        }
    }

    public interface ChatListAdapterListener {
        void OnClick(int position);
    }
}
