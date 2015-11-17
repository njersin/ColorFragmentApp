package com.example.mt1556ys.colorfragmentapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ColorListFragment extends Fragment {

    private ListView mColorListView;
    private ArrayAdapter<String> mColorArray;

    private OnListItemSelectedListener mListItemSelectedListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mColorArray = new ArrayAdapter<>(this.getActivity(), R.layout.color_list_view_item, R.id.list_item);
        mColorArray.add("Blue");
        mColorArray.add("Green");

        try {
            mListItemSelectedListener = (OnListItemSelectedListener) activity;
        } catch (ClassCastException cce) {
            throw new ClassCastException(activity.toString() + " must implement OnListItemSelectedListener.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.color_list_fragment, group, false);
        mColorListView = (ListView) v.findViewById(R.id.color_list_view);

        mColorListView.setAdapter(mColorArray);
        mColorListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mColorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListItemSelectedListener.onListItemSelected(mColorArray.getItem(position));
            }
        });

        return v;
    }


    public interface OnListItemSelectedListener {
        void onListItemSelected(String colorItem);
    }

}
