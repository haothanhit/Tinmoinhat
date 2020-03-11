package tinmoinhat.huutri.com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

import java.util.ArrayList;


import tinmoinhat.huutri.com.Activity.DetailActivity;
import tinmoinhat.huutri.com.Model.Article;
import tinmoinhat.huutri.com.R;

import static android.content.Context.MODE_PRIVATE;


public class AdapterRecyclerMain extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Article> data;
    private Context context;
    private String category,tabtitle;

    private final int VIEW_ITEM_1 = 1, VIEW_ITEM_2 = 2,VIEW_ADS=3;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, source;
        private ImageView img;
        private AdView adView;
        private CardView cardView;

        private MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.txt_title_item_main);
            source = view.findViewById(R.id.txt_source_item_main);
            img = view.findViewById(R.id.img_item_main);
            adView=view.findViewById(R.id.ad_view_item);
            cardView=view.findViewById(R.id.card_view_item);
        }
    }

    public class AdHolder extends RecyclerView.ViewHolder{
        private TextView title, source;
        private ImageView img;
        private AdView adView;
        CardView cardView;

        public AdHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title_item_main);
            source = itemView.findViewById(R.id.txt_source_item_main);
            img = itemView.findViewById(R.id.img_item_main);
            adView=itemView.findViewById(R.id.ad_view_main);
            cardView=itemView.findViewById(R.id.card_view_item);
        }
    }

    public AdapterRecyclerMain(Context context, ArrayList<Article> data,String category,String Tab) {
        this.context = context;
        this.data = data;
        this.category=category;
        this.tabtitle=Tab;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM_1) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_item_recycler_view_main_1, parent, false);
            vh = new MyViewHolder(itemView);
        } else if(viewType==VIEW_ITEM_2){
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_item_recycler_view_main_2, parent, false);
            vh = new MyViewHolder(itemView);
        } else{
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_item_recycler_view_ads, parent, false);
            vh= new AdHolder(itemView);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof AdHolder){
            AdRequest adRequest=new AdRequest.Builder().build();
            ((AdHolder) holder).adView.loadAd(adRequest);
            final Article article = data.get(position);
            ((AdHolder) holder).title.setText(article.getTitle());
            ((AdHolder) holder).source.setText(article.getSource().replace("- ", ""));
            ((AdHolder) holder).img.setVisibility(View.VISIBLE);
            Glide.with(context).load(article.getThumb()).placeholder(R.drawable.img_place_holder).into(((AdHolder) holder).img);

            ((AdHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, DetailActivity.class);
                    intent.putExtra("id",article.getId());
                    intent.putExtra("title",article.getTitle());
                    intent.putExtra("source",article.getSource());
                    intent.putExtra("thumb",article.getThumb());
                    intent.putExtra("sourceLink",article.getSourceLink());
                    intent.putExtra("tabTitle",tabtitle);
                    intent.putExtra("isVideo",article.isVideo());
                    intent.putExtra("linkVideo",article.getLinkVideo());
                    intent.putExtra("cate",category);
                    intent.putExtra("page",1);

                    SharedPreferences appSharedPrefs = context.getSharedPreferences("relate", MODE_PRIVATE);;
                    SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(data);
                    prefsEditor.putString("relate", json);
                    prefsEditor.apply();

                    SharedPreferences preferencesCount=context.getSharedPreferences("retry",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferencesCount.edit();
                    editor.clear().apply();
                    context.startActivity(intent);
                }
            });
        }else{
            final Article article = data.get(position);
            ((MyViewHolder) holder).title.setText(article.getTitle());
            ((MyViewHolder) holder).source.setText(article.getSource().replace("- ", ""));
            ((MyViewHolder) holder).img.setVisibility(View.VISIBLE);
            if(position==2){
                ((MyViewHolder) holder).adView.setVisibility(View.VISIBLE);
                AdRequest adRequest=new AdRequest.Builder().build();
                ((MyViewHolder) holder).adView.loadAd(adRequest);
            } else ((MyViewHolder) holder).adView.setVisibility(View.GONE);
            Glide.with(context).load(article.getThumb()).placeholder(R.drawable.img_place_holder).into(((MyViewHolder) holder).img);
            ((MyViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, DetailActivity.class);
                    intent.putExtra("id",article.getId());
                    intent.putExtra("title",article.getTitle());
                    intent.putExtra("source",article.getSource());
                    intent.putExtra("thumb",article.getThumb());
                    intent.putExtra("sourceLink",article.getSourceLink());
                    intent.putExtra("tabTitle",tabtitle);
                    intent.putExtra("isVideo",article.isVideo());
                    intent.putExtra("linkVideo",article.getLinkVideo());
                    intent.putExtra("cate",category);
                    intent.putExtra("page",1);

                    SharedPreferences appSharedPrefs = context.getSharedPreferences("relate", MODE_PRIVATE);;
                    SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(data);
                    prefsEditor.putString("relate", json);
                    prefsEditor.apply();

                    SharedPreferences preferencesCount=context.getSharedPreferences("retry",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferencesCount.edit();
                    editor.clear().apply();
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (position%9){
            case 0: return VIEW_ITEM_1;
            case 1:return VIEW_ITEM_1;
            case 2:return VIEW_ITEM_2;
            case 3: return VIEW_ITEM_1;
            case 4:return VIEW_ITEM_1;
            case 5:return VIEW_ITEM_2;
            case 6:return VIEW_ITEM_1;
            case 7:return VIEW_ITEM_1;
            case 8:return VIEW_ADS;
            default: return VIEW_ITEM_1;
        }
    }
}
