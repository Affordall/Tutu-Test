package com.testapps.tututest.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.testapps.tututest.R;
import com.testapps.tututest.adapter.CityListAdapter;
import com.testapps.tututest.common.BaseActivity;
import com.testapps.tututest.di.IHasComponent;
import com.testapps.tututest.di.components.DaggerIChooserActivityComponent;
import com.testapps.tututest.di.components.IChooserActivityComponent;
import com.testapps.tututest.di.components.TutuTestAppComponent;
import com.testapps.tututest.di.modules.ChooserActivityModule;
import com.testapps.tututest.models.City;
import com.testapps.tututest.presenter.impl.ChooserActivityPresenterImpl;
import com.testapps.tututest.utils.Utils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooserActivity extends BaseActivity implements
        IChooserActivityView, IHasComponent<IChooserActivityComponent>,
        SearchView.OnQueryTextListener {

    private final int REQUEST_CODE_FROM = 100;
    private final int REQUEST_CODE_TO = 200;
    int request_code;

    private IChooserActivityComponent chooserActivityComponent;
    private CityListAdapter.OnItemClickListener onItemClickListener;

    @Inject ChooserActivityPresenterImpl presenter;

    @BindView(R.id.rc_list) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.loading_text) TextView loadingText;

    private CityListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        initList();
        getIntentData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.clearFocus();
        searchItem.expandActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    private void getIntentData() {
        request_code = getIntent().getIntExtra("requestCode", REQUEST_CODE_FROM);
        if (Utils.isNetworkAvailable()) {
            presenter.parseThemAll(request_code);
        } else {
            showErrorMessage(getString(R.string.internet_is_off));
        }
    }

    private void initList() {
        onItemClickListener =
                (view1, position) -> presenter.onListItemClick(mAdapter.getCityData(position));
        mAdapter = new CityListAdapter(this, onItemClickListener);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnItemTouchListener(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.clearView();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void setupComponent(TutuTestAppComponent appComponent) {
        chooserActivityComponent = DaggerIChooserActivityComponent.builder()
                .tutuTestAppComponent(appComponent)
                .chooserActivityModule(new ChooserActivityModule(this))
                .build();
        chooserActivityComponent.inject(this);
    }

    @Override
    public void showCitiesList(ArrayList<City> listCities) {
        mAdapter.setCitiesFrom(listCities);
    }

    @Override
    public void someDetailsItemSetToIntentData(City itemCity) {
        String cityTitle = itemCity.getCountryCityTitle();
        Intent intent = new Intent();
        if (request_code == REQUEST_CODE_FROM) {
            intent.putExtra("from", cityTitle);
        } else if (request_code == REQUEST_CODE_TO) {
            intent.putExtra("to", cityTitle);
        } else {
            throw new IllegalArgumentException("Request code not found in Intent Data");
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressDialog() {
        isVisibleProgress(true);
    }

    @Override
    public void hideProgressDialog() {
        isVisibleProgress(false);
    }

    private void isVisibleProgress(boolean pb) {
        if (pb) {
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
        } else {
            mProgressBar.setVisibility(ProgressBar.GONE);
        }
    }

    @Override
    public IChooserActivityComponent getComponent() {
        return chooserActivityComponent;
    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        mRecyclerView.scheduleLayoutAnimation();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    /**
     * @param query
     * Method call search in SQLite DB
     * @return boolean
     * */
    @Override
    public boolean onQueryTextChange(String query) {
        hideProgressDialog();

        if (query.length() >= 3) {
            query = query.toLowerCase();
            mAdapter.refresh(presenter.resultSearchList(query));

            if (mAdapter.getItemCount() == 0) {
                Toast.makeText(ChooserActivity.this, getString(R.string.no_records), Toast.LENGTH_LONG).show();
            }
        }

        if (query.length() == 0) {
            presenter.parseThemAll(request_code);
        }

        return false;
    }

}
