package com.applaudostudios.nfl12bars.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.applaudostudios.nfl12bars.R;
import com.applaudostudios.nfl12bars.models.BarVenue;
import com.squareup.picasso.Picasso;

/**
 * Created by RafaelCastro on 28/5/15.
 */
public class DetailFragment extends Fragment {
    private BarVenue item;
    TextView mTxtBarAddress,mTxtBarName,mTxtSchedule;
    ImageView mImgPhoto;
    final static String FONT_PATH="fonts/";
    final static String FONT_TITLE="gt-walsheim-medium-web.ttf";
    final static String FONT_ITEM="gt-walsheim-light-web.ttf";
    final static String ITEM="item";
    public static DetailFragment newInstance(BarVenue item) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(ITEM, item);
        detailFragment.setArguments(args);
        return detailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item = getArguments().getParcelable(ITEM);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate view
        View view = inflater.inflate(R.layout.fragment_detail,
                container, false);


        Typeface tfBarName =
                Typeface.createFromAsset(getActivity().getAssets(),
                       FONT_PATH+FONT_TITLE);

        Typeface tfBarAddress =
                Typeface.createFromAsset(getActivity().getAssets(),
                        FONT_PATH+FONT_ITEM);

        mTxtBarAddress = (TextView)view.findViewById(R.id.txtBarAddress);
        mTxtBarAddress.setTypeface(tfBarAddress);
        mTxtBarName= (TextView)view.findViewById(R.id.txtBarName);
        mTxtBarName.setTypeface(tfBarName);
        mImgPhoto = (ImageView)view.findViewById(R.id.imgPhoto);
        mTxtBarAddress.setText(item.getAddress());
        mTxtBarName.setText(item.getName());
        try {
            Picasso.with(getActivity())
                    .load(item.getImageUrl())
                    .placeholder(R.drawable.logoapplaudo)
                    .error(R.drawable.logoapplaudo)
                    .into(mImgPhoto);
        }catch (Exception e)
        {

        }
        mTxtSchedule = (TextView)view.findViewById(R.id.txtSchedule);
        mTxtSchedule.setText(item.getSchedules());
        mTxtSchedule.setTypeface(tfBarAddress);


        return view;
    }

}
