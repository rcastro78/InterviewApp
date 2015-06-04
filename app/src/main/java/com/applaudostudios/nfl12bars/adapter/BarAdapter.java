package com.applaudostudios.nfl12bars.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.applaudostudios.nfl12bars.R;
import com.applaudostudios.nfl12bars.models.BarVenue;

import java.util.ArrayList;

/**
 * Created by RafaelCastro on 28/5/15.
 */
public class BarAdapter extends BaseAdapter {

    protected Activity mActivity;
    protected ArrayList<BarVenue> mBarVenueItems;
    protected BarVenue mBarVenue;
    final static String FONT_PATH="fonts/";
    final static String FONT_TITLE="gt-walsheim-medium-web.ttf";
    final static String FONT_ITEM="gt-walsheim-light-web.ttf";
    String TAG="BarAdapter";
    public BarAdapter(Activity activity, ArrayList<BarVenue> barVenueItems) {
        super();
        this.mActivity = activity;
        this.mBarVenueItems = barVenueItems;
    }
    @Override
    public int getCount() {
         return mBarVenueItems.size();
    }

    @Override
    public Object getItem(int position) {

        return mBarVenueItems.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Typeface tfBarName =
                Typeface.createFromAsset(mActivity.getAssets(),FONT_PATH+FONT_TITLE);
        Typeface tfBarAddress =
                Typeface.createFromAsset(mActivity.getAssets(),FONT_PATH+FONT_ITEM);
        mBarVenue = mBarVenueItems.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_bar, null);
            holder = new ViewHolder();
            holder.mTxtBar = (TextView) convertView.findViewById(R.id.txtBarName);
            holder.mTxtAddress = (TextView) convertView.findViewById(R.id.txtBarAddress);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.mTxtBar.setText(mBarVenue.getName());
        holder.mTxtBar.setTypeface(tfBarName);
        holder.mTxtAddress.setText(mBarVenue.getAddress());
        holder.mTxtAddress.setTypeface(tfBarAddress);

        return convertView;
    }




    static class ViewHolder {
        TextView mTxtBar,mTxtAddress;
    }
}
