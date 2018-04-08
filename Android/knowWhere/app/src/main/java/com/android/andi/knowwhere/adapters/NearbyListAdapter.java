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
import com.android.andi.knowwhere.models.Post;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andi Xu on 4/5/18.
 */

public class  NearbyListAdapter extends RecyclerView.Adapter<NearbyListAdapter.ViewHolder>{

    private List<Post> mPostList;
    private Context mContext;
    private KnowWhere mApp;
    private NearbyListAdapter.NearbyListAdapterListener mListener;

    public NearbyListAdapter(Context context, NearbyListAdapter.NearbyListAdapterListener listener){
        mContext = context;
        mPostList = new ArrayList<>();
        mApp = (KnowWhere) mContext.getApplicationContext();
        mListener = listener;
    }

    public void addItems(ArrayList<Post> list){
        mPostList.clear();
        mPostList.addAll(list);
    }

    public ArrayList<Post> getItems(){
        return (ArrayList<Post>) mPostList;
    }

    public void clearItems(){
        mPostList.clear();
    }

    @Override
    public NearbyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //inflate the custom layout
        View nearbyView = inflater.inflate(R.layout.nearby_list_item, parent, false);

        //return a new holder instance
        return new ViewHolder(nearbyView);
    }

    @Override
    public void onBindViewHolder(NearbyListAdapter.ViewHolder holder, final int position) {
        Post post = mPostList.get(position);
        //change later
        holder.mHeadImage.setImageResource(R.drawable.head_alice);

        holder.mNameView.setText(post.getUsername());
        DecimalFormat df = new DecimalFormat("0.00");
        holder.mDistanceView.setText(df.format(post.getDistance()*1000)+" m");
        holder.mPostView.setText(post.getWhatsup());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.OnClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    //View holder class
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mHeadImage;
        private TextView mNameView;
        private TextView mDistanceView;
        private TextView mPostView;

        public ViewHolder(final View view){
            super(view);
            mHeadImage = view.findViewById(R.id.image_nearby_list);
            mNameView = view.findViewById(R.id.name_nearby_list);
            mDistanceView = view.findViewById(R.id.dist_nearby_list);
            mPostView = view.findViewById(R.id.post_nearby_list);
        }
    }

    public interface NearbyListAdapterListener {
        void OnClick(int position);
    }
}
