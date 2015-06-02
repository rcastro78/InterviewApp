package com.applaudostudios.nfl12bars.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.applaudostudios.nfl12bars.fragments.DetailFragment;
import com.applaudostudios.nfl12bars.R;
import com.applaudostudios.nfl12bars.models.BarVenue;

/**
 * Created by RafaelCastro on 28/5/15.
 */
public class DetailActivity extends ActionBarActivity {
    DetailFragment mDetailFragment;

    View mCustomView;
    ActionBar mActionBar;
    ImageView mImgShare,mImgHome;
    BarVenue barVenue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        barVenue = getIntent().getParcelableExtra("item");

        if (savedInstanceState==null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable("item", getIntent().getParcelableExtra("item"));

            mDetailFragment = new DetailFragment();
            mDetailFragment.setArguments(arguments);
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContainer,mDetailFragment);
            fragmentTransaction.commit();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String share = barVenue.getName()+"\n"+barVenue.getAddress();
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "NFL Bar Venues");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, share);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
        else{
            // if a the new item is clicked show "Toast" message.
        }

        return super.onOptionsItemSelected(item);
    }


}
