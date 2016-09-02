package com.testapps.tututest.views;

import com.testapps.tututest.models.City;

import java.util.ArrayList;

/**
 * Created by affy on 30.08.16.
 */
public interface IChooserActivityView {

    void showCitiesList(ArrayList<City> listCities);
    void someDetailsItemSetToIntentData(City itemCity);
    void showErrorMessage(String error);
    void showProgressDialog();
    void hideProgressDialog();

    class EmptyDetailView implements IChooserActivityView {

        @Override
        public void showCitiesList(ArrayList<City> listCities) {

        }

        @Override
        public void someDetailsItemSetToIntentData(City itemCity) {

        }

        @Override
        public void showErrorMessage(String error) {

        }

        @Override
        public void showProgressDialog() {

        }

        @Override
        public void hideProgressDialog() {

        }
    }

}
