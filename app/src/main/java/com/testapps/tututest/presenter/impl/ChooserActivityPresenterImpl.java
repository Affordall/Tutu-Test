package com.testapps.tututest.presenter.impl;

import com.testapps.tututest.app.TutuTestApp;
import com.testapps.tututest.database.DatabaseHandler;
import com.testapps.tututest.models.City;
import com.testapps.tututest.network.JsonParser;
import com.testapps.tututest.presenter.IChooserActivityPresenter;
import com.testapps.tututest.repository.impl.CityRepository;
import com.testapps.tututest.specifications.impl.AllCitiesFromListSpecification;
import com.testapps.tututest.specifications.impl.AllCitiesFromSortedListSpecification;
import com.testapps.tututest.specifications.impl.AllCitiesToSortedListSpecification;
import com.testapps.tututest.views.IChooserActivityView;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by affy on 30.08.16.
 */
public class ChooserActivityPresenterImpl implements IChooserActivityPresenter {

    private static final int REQUEST_CODE_FROM = 100;
    private static final int REQUEST_CODE_TO = 200;

    private IChooserActivityView view;
    private Subscription mListSubscription;

    @Inject DatabaseHandler handler;
    @Inject CityRepository cityRepository;

    @Inject
    public ChooserActivityPresenterImpl(IChooserActivityView view) {
        TutuTestApp.get(TutuTestApp.getContext()).getAppComponent().inject(this);
        this.view = view;
    }

    @Override
    public void setView(final IChooserActivityView view) {
        this.view = view;
    }

    public void parseThemAll(int requestCode) {
        if (isDbEmpty()) {
           createObservableCitiesItems(requestCode);
        } else {
            view.showCitiesList(showList(requestCode));
        }
    }

    private boolean isDbEmpty() {
        return handler.getCitiesCount() == 0;
    }

    @Override
    public void onResume() {
        view.showProgressDialog();
    }

    @Override
    public void onDestroy() {
        if (mListSubscription != null && !mListSubscription.isUnsubscribed()) {
            mListSubscription.unsubscribe();
        }
    }

    @Override
    public void clearView() {
        this.view = new IChooserActivityView.EmptyDetailView();
    }

    @Override
    public void onListItemClick(City itemCity) {
        view.someDetailsItemSetToIntentData(itemCity);
    }

    @Override
    public void getCityById(long id) {
    }

    private void createObservableCitiesItems(int requestCode) {
        Single<ArrayList<City>> single = Single.fromCallable(()
                -> runParser(requestCode));
        mListSubscription = single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<ArrayList<City>>() {
                    @Override
                    public void onSuccess(ArrayList<City> resultList) {
                        view.hideProgressDialog();
                        view.showCitiesList(resultList);
                    }
                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                    }
                });
    }

    /**
     * @param requestCode
     * Method call if DB is empty
     * Gets JsonParser instance and run method depends by @param
     * @return ArrayList of City objects.
     * */
    private ArrayList<City> runParser(int requestCode) {
        if (requestCode == REQUEST_CODE_FROM) {
            JsonParser parser = JsonParser.getInstance(TutuTestApp.getContext());
            return parser.getListCityFrom(new AllCitiesFromSortedListSpecification());
        } else {
            JsonParser parser = JsonParser.getInstance(TutuTestApp.getContext());
            //return parser.getListCityTo(new AllCitiesToSortedListSpecification());
            return parser.getListCityFrom(new AllCitiesFromSortedListSpecification());
        }
    }

    /**
     * @param requestCode
     * Method call if DB is not empty
     * So can show list from repository depends by custom query (Specification).
     * @return ArrayList of City objects.
     * */
    private ArrayList<City> showList(int requestCode) {
        if (requestCode == REQUEST_CODE_FROM) {
            return (ArrayList<City>) cityRepository.query(new AllCitiesFromSortedListSpecification());
        } else {
            //return (ArrayList<City>) cityRepository.query(new AllCitiesToSortedListSpecification());
            return (ArrayList<City>) cityRepository.query(new AllCitiesFromSortedListSpecification());
        }
    }
}
