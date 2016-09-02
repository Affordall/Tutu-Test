package com.testapps.tututest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.testapps.tututest.R;
import com.testapps.tututest.models.City;

import java.util.ArrayList;

/**
 * Created by affy on 30.08.16.
 */
public class CityListAdapter extends RecyclerView.Adapter<CityViewHolder>
        implements RecyclerView.OnItemTouchListener {

    private ArrayList<City> mCities;
    private OnItemClickListener mListener;

    public CityListAdapter(Context context, OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    public void refresh(final ArrayList<City> listData) {
        this.mCities = listData;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    GestureDetector mGestureDetector;

    @Override
    public CityViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_city_parent, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        final City city = mCities.get(position);
        holder.mTitle.setText(city.getCountryCityTitle());
    }


    @Override
    public int getItemCount() {
        if(mCities == null) {
            return 0;
        }
        return mCities.size();
    }

    public City getCityData(int i) {
        return mCities.get(i);
    }

    public void setCitiesFrom(final ArrayList<City> cities) {
        mCities = cities;
        notifyDataSetChanged();
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

}
