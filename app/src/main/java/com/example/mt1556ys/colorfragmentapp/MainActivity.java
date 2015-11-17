package com.example.mt1556ys.colorfragmentapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ColorListFragment.OnListItemSelectedListener  {

    private ColorListFragment mColorListFragment;
    private boolean mDualPane;
    private int mCurrentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorListFragment = new ColorListFragment();

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(android.R.id.content, mColorListFragment);
        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentItem", mCurrentItem);
    }

    @Override
    public void onListItemSelected(String colorItem) {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (colorItem == "Blue") {
            BlueFragment itemFragment = new BlueFragment();
            transaction.replace(android.R.id.content, itemFragment);

        } else if (colorItem == "Green") {
            GreenFragment itemFragment = new GreenFragment();
            transaction.replace(android.R.id.content, itemFragment);
        }

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getFragmentManager();
        manager.popBackStack();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(android.R.id.content, mColorListFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
