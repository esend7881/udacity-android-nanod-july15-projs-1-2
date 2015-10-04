package com.ericsender.android_nanodegree.popmovie.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ericsender.android_nanodegree.popmovie.R;
import com.ericsender.android_nanodegree.popmovie.parcelable.TrailerListObj;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by g56147 on 9/15/2015.
 */
public class TrailerListViewAdapter extends ArrayAdapter<TrailerListObj> {

    private static final String LOG_TAG = TrailerListViewAdapter.class.getSimpleName();
    private final Context mContext;
    private final int mTrailerCellRes;
    private final ListView mTrailerListView;
    private final String strYouTubeUrl;
    private List<TrailerListObj> mRowObjs;
    private final String strTrailerTitleItr;
    private final String strYouTubeImg;


    public TrailerListViewAdapter(Context context, int trailerCellRes, List<TrailerListObj> rowObjs, ListView trailerListView) {
        super(context, trailerCellRes, rowObjs);
        mContext = context;
        mTrailerCellRes = trailerCellRes;
        mRowObjs = rowObjs;
        mTrailerListView = trailerListView;
        strTrailerTitleItr = mContext.getString(R.string.trailer_title_iter);
        strYouTubeUrl = mContext.getString(R.string.youtube_url);
        strYouTubeImg = mContext.getString(R.string.youtube_img);
    }

    public void setData(List<TrailerListObj> data) {
        int v = data.isEmpty() ? View.GONE : View.VISIBLE;
        mTrailerListView.setVisibility(v);
        this.mRowObjs = data;
        notifyDataSetChanged();
    }

    @Override
    public int getPosition(TrailerListObj item) {
        return mRowObjs.indexOf(item);
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        //Utils.log();
        if (checkRowAndObj(row, position)) {
            final TrailerListObj trailer = getItem(position);
            row = ((Activity) mContext).getLayoutInflater().inflate(mTrailerCellRes, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.playIcon = (ImageView) row.findViewById(R.id.trailer_play);
            holder.youTubeThump = (ImageView) row.findViewById(R.id.youtube_thumb);
            holder.trailerTitle = (TextView) row.findViewById(R.id.trailer_name);
            holder.trailerTitle.setText(trailer.title);
            holder.position = position;
            holder.trailerHashCode = trailer.hashCode();

            Picasso.with(mContext.getApplicationContext())
                    .load(String.format(strYouTubeImg, trailer.youtube_key))
                    .placeholder(android.R.drawable.ic_media_next)
                    .fit()
                    .into(holder.youTubeThump);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(String.format(strYouTubeUrl, trailer.youtube_key))));
                }
            });
            row.setTag(holder);
        }
        return row;
    }

    private boolean checkRowAndObj(View row, int position) {
        if (row != null) {
            ViewHolder vh = (ViewHolder) row.getTag();
            return vh.position != position || vh.trailerHashCode != getItem(position).hashCode();
        } else
            return true;
    }

    @Override
    public int getCount() {
        return mRowObjs.size();
    }

    @Override
    public TrailerListObj getItem(int position) {
        return mRowObjs.get(position);
    }

    private static class ViewHolder {
        int position = -1;
        TextView trailerTitle;
        ImageView playIcon;
        ImageView youTubeThump;
        int trailerHashCode;
    }
}
