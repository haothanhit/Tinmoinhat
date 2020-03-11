package tinmoinhat.huutri.com.Adapter;

/**
 * Created by Thanh Trung on 20/05/2019.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import tinmoinhat.huutri.com.Model.Content;
import tinmoinhat.huutri.com.R;

public class AdapterRecyclerDetail extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Content> data;
    private Context context;
    private int VIEW_ITEM = 1, VIEW_ADS = 2;

    public AdapterRecyclerDetail(Context context, ArrayList<Content> data) {
        this.context = context;
        this.data = data;
    }

    private class ItemHolder extends RecyclerView.ViewHolder {
        private TextView txt;
        private ImageView img;

        public ItemHolder(View view) {
            super(view);
            txt = view.findViewById(R.id.txt_detail);
            img = view.findViewById(R.id.img_detail);
        }
    }

    private class AdHolder extends RecyclerView.ViewHolder {
        private TextView txt;
        private ImageView img;
        private AdView adView;

        public AdHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt_detail);
            img = itemView.findViewById(R.id.img_detail);
            adView = itemView.findViewById(R.id.ad_view_item);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.custom_item_detail, parent, false);

            vh = new ItemHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.custom_item_ads, parent, false);

            vh = new AdHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AdHolder) {
            AdRequest adRequest = new AdRequest.Builder().build();
            ((AdHolder) holder).adView.loadAd(adRequest);

            final Content content = data.get(position);
            if (content.getText().equals("")) {
                if (!content.getImg().equals("")) {
                    Glide.with(context).load(content.getImg()).into(((AdHolder) holder).img);
                    ((AdHolder) holder).img.setVisibility(View.VISIBLE);
                }
            }
            if (content.getImg().equals("")) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("font_size", Context.MODE_PRIVATE);
                int size = sharedPreferences.getInt("size", 0);
                if (size != 0) {
                    ((AdHolder) holder).txt.setTextSize(size);

                    if (!content.getText().equals("")) {
                        ((AdHolder) holder).txt.setText(content.getText());
                        ((AdHolder) holder).txt.setVisibility(View.VISIBLE);
                    }
                } else {
                    ((AdHolder) holder).txt.setTextSize(20);

                    if (!content.getText().equals("")) {
                        ((AdHolder) holder).txt.setText(content.getText());
                        ((AdHolder) holder).txt.setVisibility(View.VISIBLE);
                    }
                }
            }
        } else {
            final Content content = data.get(position);
            if (content.getText().equals("")) {
                if (!content.getImg().equals("")) {
                    Glide.with(context).load(content.getImg()).into(((ItemHolder) holder).img);
                    ((ItemHolder) holder).img.setVisibility(View.VISIBLE);
                    ((ItemHolder) holder).txt.setVisibility(View.GONE);
                }
            }
            if (content.getImg().equals("")) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("font_size", Context.MODE_PRIVATE);
                int size = sharedPreferences.getInt("size", 0);
                if (size != 0) {
                    ((ItemHolder) holder).txt.setTextSize(size);

                    if (position == 0) {  // set title
                        ((ItemHolder) holder).txt.setTextSize(size + 6);
                        ((ItemHolder) holder).txt.setTypeface(null, Typeface.BOLD);
                    }

                    if (position == 1) { //set source
                        ((ItemHolder) holder).txt.setTextSize(size + 2);
                    }

                    if (position == data.size() - 1) {  // set author
                        ((ItemHolder) holder).txt.setTypeface(null, Typeface.BOLD);
                    }

                    if (!content.getText().equals("")) {
                        ((ItemHolder) holder).txt.setText(content.getText());
                        ((ItemHolder) holder).txt.setVisibility(View.VISIBLE);
                        ((ItemHolder) holder).img.setVisibility(View.GONE);
                    }
                } else {
                    ((ItemHolder) holder).txt.setTextSize(20);

                    if (position == 0) {  // set title
                        ((ItemHolder) holder).txt.setTextSize(26);
                        ((ItemHolder) holder).txt.setTypeface(null, Typeface.BOLD);
                    }

                    if (position == 1) { //set source
                        ((ItemHolder) holder).txt.setTextSize(22);
                    }

                    if (position == data.size() - 1) {  // set author
                        ((ItemHolder) holder).txt.setTypeface(null, Typeface.BOLD);
                    }

                    if (!content.getText().equals("")) {
                        ((ItemHolder) holder).txt.setText(content.getText());
                        ((ItemHolder) holder).txt.setVisibility(View.VISIBLE);
                        ((ItemHolder) holder).img.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
       if(position==6) return VIEW_ADS;
       if(data.size()>30){
           if(position==data.size()/2) return VIEW_ADS;
       }
       return VIEW_ITEM;
    }
}
