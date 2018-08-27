package com.sacannouncements.announcementsystem.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.sacannouncements.announcementsystem.R;
import com.sacannouncements.announcementsystem.datamodel.AnnouncementCategoryDataModal;

import java.util.ArrayList;

/**
 * Created by Keji's Lab on 19/01/2018.
 */

public class AnnouncementListRecyclerViewAdapter extends RecyclerView.Adapter<AnnouncementListRecyclerViewAdapter.MyViewHolder> {
   private ArrayList<AnnouncementCategoryDataModal> announcementCategoryDataModalArrayList = new ArrayList<>();
   private Context context;
   int active = 0;


    public class MyViewHolder extends RecyclerView.ViewHolder{
    TextView categoryName;
    ConstraintLayout container;




        public MyViewHolder(View view){
            super(view);
            categoryName = (TextView) view.findViewById(R.id.categoryName);

        }
    }

    public AnnouncementListRecyclerViewAdapter(Context c, ArrayList<AnnouncementCategoryDataModal> announcementCategoryDataModals){
    this.context = c;
    this.announcementCategoryDataModalArrayList = announcementCategoryDataModals;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_category_item,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.categoryName.setText(announcementCategoryDataModalArrayList.get(position).getCategoryName());
        holder.categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                active = position;
                notifyDataSetChanged();
            }
        });
        if (active == position){
            textAnimation(holder.categoryName,holder.categoryName.getTextSize());
            holder.categoryName.setTextColor(context.getResources().getColor(R.color.coloractive));
        }else {
            textAnimationDown(holder.categoryName,holder.categoryName.getTextSize());
            holder.categoryName.setTextColor(context.getResources().getColor(R.color.colorMuted));
        }
    }

    @Override
    public int getItemCount() {
        return announcementCategoryDataModalArrayList.size();
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position, AnnouncementCategoryDataModal announcementCategoryDataModal);

    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickListener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private void textAnimation(TextView textView,float start){
        final TextView tv = textView;

        final float startSize = start/context.getResources().getDisplayMetrics().scaledDensity;; // Size in pixels
        final float endSize = 25;
        long animationDuration = 600; // Animation duration in ms

        ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
        animator.setDuration(animationDuration);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                tv.setTextSize(animatedValue);
            }
        });
        animator.start();
    }
    private void textAnimationDown(TextView textView,float end){
        final TextView tv = textView;

        final float startSize = end/context.getResources().getDisplayMetrics().scaledDensity; // Size in pixels
        final float endSize = 15;
        long animationDuration = 600; // Animation duration in ms

        ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
        animator.setDuration(animationDuration);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                tv.setTextSize(animatedValue);
            }
        });

        animator.start();
    }

}


