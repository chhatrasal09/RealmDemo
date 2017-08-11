package com.alpha.vidyaroha.activities;

import android.icu.text.DateFormat;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.vidyaroha.R;
import com.alpha.vidyaroha.adapters.ViewPagerAdapter;
import com.alpha.vidyaroha.fragments.Greetings;
import com.alpha.vidyaroha.fragments.TakePicture;
import com.alpha.vidyaroha.javaClass.RequiredObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class TabActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static RequiredObject requiredObject;
    public static Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        createActionBar();

        setUpLocalDatabase();
        initView();
    }

    private void setUpLocalDatabase() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        realm = Realm.getDefaultInstance();
        RealmResults<RequiredObject> results = realm.where(RequiredObject.class).findAll();
        if (results.size() == 0){
            realm.beginTransaction();
            requiredObject = realm.createObject(RequiredObject.class);
            requiredObject.setUserName("User");
            requiredObject.setCount(1);
            requiredObject.setDailyTimeStamp(SimpleDateFormat.getDateInstance().format(Calendar.getInstance().getTime()));
            realm.commitTransaction();
            requiredObject = results.get(0);
        }else {
            if (results.get(0).getDailyTimeStamp().equals(SimpleDateFormat.getDateInstance().format(Calendar.getInstance().getTime()))) {
                requiredObject = results.get(0);
                realm.beginTransaction();
                results.get(0).setCount(results.get(0).getCount() + 1);
                realm.commitTransaction();
            }else {
                realm.beginTransaction();
                requiredObject = results.get(0);
                requiredObject.setCount(1);
                requiredObject.setDailyTimeStamp(SimpleDateFormat.getDateInstance().format(Calendar.getInstance().getTime()));
                realm.copyToRealmOrUpdate(requiredObject);
                realm.commitTransaction();
            }
        }
    }


    // Action Bar
    private void createActionBar() {
        toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        ((TextView)toolbar.findViewById(R.id.toolbar_title)).setText("Vidyaroha Tab Assignment");
        actionBar.setDisplayShowTitleEnabled(false);
    }


    // Intializes the xml elements
    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setipViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }


    // creating the view pager
    private void setipViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        adapter.addFragment(new Greetings(), "Greetings");
        adapter.addFragment(new TakePicture(), "Take Picture");
        viewPager.setAdapter(adapter);
    }



}
