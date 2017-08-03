package com.joseadilson.news_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.joseadilson.news_app.R;
import com.joseadilson.news_app.WebActivity;
import com.joseadilson.news_app.domain.News;
import com.joseadilson.news_app.extras.FontTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 05/03/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> newsList;
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder  {
        FontTextView tvCategory;
        FontTextView tvTime;
        FontTextView tvSummary;
        ImageView imgStory;
        RelativeLayout relLayoutCard;

        ViewHolder(View itemView) {
            super(itemView);
            imgStory   = (ImageView)itemView.findViewById(R.id.imageView);
            tvCategory = (FontTextView)itemView.findViewById(R.id.tv_category_news);
            tvTime     = (FontTextView)itemView.findViewById(R.id.tv_time_news);
            tvSummary  = (FontTextView)itemView.findViewById(R.id.tv_summary_news);
            relLayoutCard  = (RelativeLayout) itemView.findViewById(R.id.relMain);
        }

        public void setData(final News news){
            tvCategory.setText(news.getCategoryNews());
            tvTime.setText(news.getTimeNews());
            tvSummary.setText(news.getSummaryNews());

            Glide.with(context)
                    .load(news. getImgUrlNews() )
                    .asBitmap()
                    .placeholder(R.drawable.news_img2)
                    .thumbnail(0.1f)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                            imgStory.setImageBitmap(bitmap);
                        }
                    });

            relLayoutCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("URL_Article", news.getUrlNews() );
                    context.startActivity(intent);
                }
            });
        }
    }

    public NewsAdapter(Context context, List<News> newsList){
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_full, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(newsList.get(position));

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

}
