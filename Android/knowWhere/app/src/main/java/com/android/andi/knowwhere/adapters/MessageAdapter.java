package com.android.andi.knowwhere.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.andi.knowwhere.R;
import com.android.andi.knowwhere.activities.MainActivity;
import com.android.andi.knowwhere.application.KnowWhere;
import com.android.andi.knowwhere.models.Chat;
import com.android.andi.knowwhere.utils.Basics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andi Xu on 4/7/18.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    private List<Chat> mChatList;
    private Context mContext;
    private KnowWhere mApp;
    private MessageAdapter.MessageAdapterListener mListener;

    public MessageAdapter(Context context, MessageAdapter.MessageAdapterListener listener){
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
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //inflate the custom layout
        View chatView = inflater.inflate(R.layout.chat_item_view, parent, false);

        //return a new holder instance
        return new ViewHolder(chatView);
    }

    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder holder, final int position) {
        Chat chat = mChatList.get(position);
        //change later
        if(!chat.getSender().equals(mApp.getPreference().getUser(mApp).getUsername())){
            holder.mLeftLayoutView.setVisibility(View.VISIBLE);
            holder.mRightLayoutView.setVisibility(View.GONE);
            if(MainActivity.map.containsKey(chat.getSender())){
                holder.mLeftHeadImage.setImageResource(MainActivity.map.get(chat.getSender()));
            }else{
                holder.mLeftHeadImage.setImageResource(R.drawable.head_smith);
            }

            holder.mSenderNameView.setText(chat.getSender());
            holder.mSenderMessageView.setText(chat.getContent());

            String time = Basics.stamp2Date(chat.getTimeStamp(), "MM-dd HH:mm");
            holder.mSenderTimeView.setText(time);
        }else{
            holder.mRightLayoutView.setVisibility(View.VISIBLE);
            holder.mLeftLayoutView.setVisibility(View.GONE);
            holder.mUserMessageView.setText(chat.getContent());

            String time = Basics.stamp2Date(chat.getTimeStamp(), "MM-dd HH:mm");
            holder.mUserTimeView.setText(time);
        }

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
        private ImageView mLeftHeadImage;
        private TextView mSenderNameView;
        private TextView mSenderMessageView;
        private TextView mSenderTimeView;
        private TextView mUserMessageView;
        private TextView mUserTimeView;
        private ConstraintLayout mLeftLayoutView;
        private ConstraintLayout mRightLayoutView;

        public ViewHolder(final View view){
            super(view);
            mLeftLayoutView = view.findViewById(R.id.left_layout);
            mRightLayoutView = view.findViewById(R.id.right_layout);
            mLeftHeadImage = view.findViewById(R.id.image_left);
            mSenderNameView = view.findViewById(R.id.name_left);
            mSenderMessageView = view.findViewById(R.id.message_left);
            mSenderTimeView = view.findViewById(R.id.time_left);
            mUserMessageView = view.findViewById(R.id.message_right);
            mUserTimeView = view.findViewById(R.id.time_right);
        }
    }

    public interface MessageAdapterListener {
        void OnClick(int position);
    }
}
