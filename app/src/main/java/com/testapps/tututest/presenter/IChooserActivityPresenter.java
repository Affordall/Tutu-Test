package com.testapps.tututest.presenter;

import com.testapps.tututest.models.City;
import com.testapps.tututest.views.IChooserActivityView;

/**
 * Created by affy on 30.08.16.
 */
public interface IChooserActivityPresenter extends IBasePresenter<IChooserActivityView> {
    void onListItemClick(City itemCity);
    void getCityById(long id);
}
