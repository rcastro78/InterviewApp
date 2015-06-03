package com.applaudostudios.nfl12bars.activities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.applaudostudios.nfl12bars.fragments.BarsFragment;
import com.applaudostudios.nfl12bars.fragments.DetailFragment;
import com.applaudostudios.nfl12bars.R;
import com.applaudostudios.nfl12bars.models.BarVenue;


public class MainActivity extends ActionBarActivity implements
        BarsFragment.OnListItemSelectedListener {
    final static String SUBJECT="NFL Bar Venues";
    final static String SHARE_TEXT="Share via";
    final static String SHARE_TYPE="text/plain";
    final static String ITEM="item";
    BarVenue bar;
    private boolean has2Panes = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        determinePaneLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (has2Panes==true){
            getMenuInflater().inflate(R.menu.menu_detail, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if(item.getItemId() == R.id.action_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType(SHARE_TYPE);
            String share = bar.getName()+"\n"+bar.getAddress();
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, SUBJECT);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, share);
            startActivity(Intent.createChooser(sharingIntent, SHARE_TEXT));
        }
        else{
            // if a the new item is clicked show "Toast" message.
        }

        return super.onOptionsItemSelected(item);
    }

    private void determinePaneLayout() {
        FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.flDetailContainer);
        if (fragmentItemDetail != null) {
            has2Panes = true;
        }
    }



    @Override
    public void onItemSelected(BarVenue item) {

        if (has2Panes) {
            DetailFragment fragmentItem = DetailFragment.newInstance(item);
            bar = item;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragmentItem);
            ft.commit();
        }else {
            Intent i = new Intent(this, DetailActivity.class);
            i.putExtra(ITEM, item);
            startActivity(i);
        }
    }



}