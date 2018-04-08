package com.android.andi.knowwhere.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.andi.knowwhere.R;
import com.android.andi.knowwhere.activities.MainActivity;
import com.android.andi.knowwhere.application.KnowWhere;
import com.android.andi.knowwhere.models.Contact;
import com.android.andi.knowwhere.models.Post;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andi Xu on 4/7/18.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder>{

    //private List<Contact> mContactList;
    private List<String> mContactList;
    private Context mContext;
    private KnowWhere mApp;
    private ContactListAdapter.ContactListAdapterListener mListener;

    public ContactListAdapter(Context context, ContactListAdapter.ContactListAdapterListener listener){
        mContext = context;
        mContactList = new ArrayList<>();
        mApp = (KnowWhere) mContext.getApplicationContext();
        mListener = listener;
    }

    public void addItems(ArrayList<String> list){
        mContactList.clear();
        mContactList.addAll(list);
    }

    public ArrayList<String> getItems(){
        return (ArrayList<String>) mContactList;
    }

    public void clearItems(){
        mContactList.clear();
    }

    @Override
    public ContactListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //inflate the custom layout
        View contactView = inflater.inflate(R.layout.contact_list_item, parent, false);

        //return a new holder instance
        return new ContactListAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ContactListAdapter.ViewHolder holder, final int position) {
        //Contact contact = mContactList.get(position);
        String contact = mContactList.get(position);
        //change later
        holder.mHeadImage.setImageResource(MainActivity.map.get(contact));

        holder.mNameView.setText(contact);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.OnClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    //View holder class
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mHeadImage;
        private TextView mNameView;

        public ViewHolder(final View view){
            super(view);
            mHeadImage = view.findViewById(R.id.head_contact);
            mNameView = view.findViewById(R.id.name_contact);
        }
    }

    public interface ContactListAdapterListener {
        void OnClick(int position);
    }
}
