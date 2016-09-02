package com.testapps.tututest.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.testapps.tututest.R;
import com.testapps.tututest.common.BaseActivity;
import com.testapps.tututest.di.IHasComponent;
import com.testapps.tututest.di.components.DaggerIMainActivityComponent;
import com.testapps.tututest.di.components.IMainActivityComponent;
import com.testapps.tututest.di.components.TutuTestAppComponent;
import com.testapps.tututest.di.modules.MainActivityModule;
import com.testapps.tututest.presenter.impl.MainActivityPresenterImpl;
import com.testapps.tututest.utils.Utils;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        IMainActivityView, IHasComponent<IMainActivityComponent>, CalendarDatePickerDialogFragment.OnDateSetListener {

    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker";
    private final int REQUEST_CODE_FROM = 100;
    private final int REQUEST_CODE_TO = 200;

    private IMainActivityComponent mainActivityComponent;

    @Inject MainActivityPresenterImpl presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.tv_date) TextView tvDate;
    @BindView(R.id.custom_btn_pick_date) LinearLayout datePickerBtn;
    @BindView(R.id.custom_btn_search) LinearLayout searchBtn;
    @BindView(R.id.et_from) MaterialEditText fieldFrom;
    @BindView(R.id.et_to) MaterialEditText fieldTo;

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initToolbar();
        initNavDrawer();
        initButtons();
    }

    @Override
    protected void setupComponent(TutuTestAppComponent appComponent) {
        mainActivityComponent = DaggerIMainActivityComponent.builder()
                .tutuTestAppComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build();
        mainActivityComponent.inject(this);
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
            Intent intent = new Intent(MainActivity.this,
                    SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_schedule) {
            Intent intent = new Intent(this,
                    MainActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_about) {
            Utils.showAboutDialog(MainActivity.this);
        } else if (id == R.id.nav_send) {
            Utils.launchIntentToSendFeedback(MainActivity.this);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initNavDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initButtons() {
        datePickerBtn.setOnClickListener(view -> showDatePickerdialog());
        searchBtn.setOnClickListener(view -> runSearch());
    }

    private void runSearch() {
        if (!Utils.isEmpty(fieldFrom) && !Utils.isEmpty(fieldTo)) {
            searchBtn.setEnabled(true);
            String msg = getString(R.string.search_result_values, fieldFrom.getText(), fieldTo.getText(), tvDate.getText());
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Ooh! :(", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick({ R.id.et_from, R.id.et_to })
    public void pickDestination(View view) {
        int id = view.getId();
        if (id == R.id.et_from) {
            startActivityRes(REQUEST_CODE_FROM);
        } else {
            startActivityRes(REQUEST_CODE_TO);
        }
    }

    private void startActivityRes(int request_code) {
        Intent intent = new Intent(this, ChooserActivity.class);
        intent.putExtra("requestCode", request_code);
        startActivityForResult(intent, request_code);
    }

    @Override
    public IMainActivityComponent getComponent() {
        return mainActivityComponent;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        CalendarDatePickerDialogFragment calendarDatePickerDialogFragment = (CalendarDatePickerDialogFragment) getSupportFragmentManager()
                .findFragmentByTag(FRAG_TAG_DATE_PICKER);
        if (calendarDatePickerDialogFragment != null) {
            calendarDatePickerDialogFragment.setOnDateSetListener(this);
        }
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        String strCurrentDate = getString(R.string.calendar_date_picker_result_values, dayOfMonth, monthOfYear + 1, year);
        tvDate.setText(Utils.convertDate(strCurrentDate));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FROM:
                    String destinationFrom = data.getStringExtra("from");
                    fieldFrom.setText(destinationFrom);
                    break;
                case REQUEST_CODE_TO:
                    String destinationTo = data.getStringExtra("to");
                    fieldTo.setText(destinationTo);
                    break;
                default:
                    break;
            }
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.wrong_input), Toast.LENGTH_LONG).show();
        }
    }

    public void showDatePickerdialog() {
        CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(MainActivity.this)
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setDoneText(getString(android.R.string.ok))
                .setCancelText(getString(android.R.string.cancel));
        cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);
    }
}
