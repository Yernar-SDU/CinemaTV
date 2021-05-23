package com.example.bottomnavigationactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.bottomnavigationactivity.R;
import com.example.bottomnavigationactivity.model.Movie;
import com.example.bottomnavigationactivity.network.NetworkService;

import java.util.List;

public class ImageAdapter extends PagerAdapter {
    public Context mContext;
    LayoutInflater mLayoutInflater;
    List<Movie> trendings;


    public ImageAdapter(Context context, List<Movie> trendings){
        this.trendings = trendings;
        mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return trendings.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mLayoutInflater.inflate(R.layout.image_view_item,container,false);
        ImageView imageView = view.findViewById(R.id.imageViewMain);
        TextView cinemaName = view.findViewById(R.id.item_text);
        TextView idView = view.findViewById(R.id.id);
        if (mContext != null){
            Glide.with(mContext)
                    .load(NetworkService.BASE_URL_IMAGES + trendings.get(position).getBackdrop_path())
                    .override(600,300)
                    .placeholder(R.drawable.placeholder_png)
                    .centerCrop()
                    .into(imageView);
        }
        cinemaName.setText(trendings.get(position).getTitle());
        String id = String.valueOf(trendings.get(position).getId());
        idView.setText(id);
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
