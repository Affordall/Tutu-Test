package com.testapps.tututest.presenter;

public interface IBasePresenter<T> {
    void setView(T view);
    void clearView();
    void onDestroy();
    void onResume();
}
