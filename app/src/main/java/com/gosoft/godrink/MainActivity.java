package com.gosoft.godrink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import test.thing.Country;
import test.thing.ImageModel;
import test.thing.SlidingImage_Adapter;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

    //List image for ad
    private int[] myImageList = new int[]{R.drawable.en, R.drawable.vn,
            R.drawable.ja,R.drawable.ru
            ,R.drawable.us};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        //tabLayout.setupWithViewPager((ViewPager)findViewById(R.id.photos_viewpager), true);

        //Example gridlayout
        List<Country> image_details = getListData();
        final GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new CustomGridAdapter(this, image_details));

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        init();
        // Khi người dùng click vào các GridItem
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = gridView.getItemAtPosition(position);
                Country country = (Country) o;
                Toast.makeText(MainActivity.this, "Selected :"
                        + " " + country, Toast.LENGTH_LONG).show();

            }
        });
    }

    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    //Init pager for ad
    private void init() {

        mPager = (ViewPager) findViewById(R.id.photos_viewpager);
        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this,imageModelArrayList));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator


        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    private  List<Country> getListData() {
        List<Country> list = new ArrayList<Country>();
        Country vietnam = new Country("Vietnam", "vn", 98000000);
        Country usa = new Country("United States", "us", 320000000);
        Country russia = new Country("Russia", "ru", 142000000);
        Country australia = new Country("Australia", "en", 23766305);
        Country japan = new Country("Japan", "jp", 126788677);

        list.add(vietnam);
        list.add(usa);
        list.add(russia);
        list.add(australia);
        list.add(japan);

        return list;
    }

}
