package com.virtualbox.torchick.rog.Activity;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.virtualbox.torchick.rog.Fragment.BrsFragment;
import com.virtualbox.torchick.rog.Fragment.EnsiklopediaFragment;
import com.virtualbox.torchick.rog.Fragment.HomeFragment;
import com.virtualbox.torchick.rog.Fragment.InfografisFragment;
import com.virtualbox.torchick.rog.Model.LinkDataForm;
import com.virtualbox.torchick.rog.R;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    HomeFragment homeFragment = HomeFragment.newInstance("","");
    BrsFragment brsFragment = BrsFragment.newInstance("","");
    InfografisFragment infografisFragment = InfografisFragment.newInstance("", "");
    EnsiklopediaFragment ensiklopediaFragment = EnsiklopediaFragment.newInstance("", "");

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    BottomNavigationView navigation;

    // Custom Listener
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_brs:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_awe:
                    mViewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_ens:
                    mViewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }

    };

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    navigation.getMenu().getItem(0).setChecked(true);
                    return;
                case 1:
                    navigation.getMenu().getItem(1).setChecked(true);
                    return;
                case 2:
                    navigation.getMenu().getItem(2).setChecked(true);
                    return;
                case 3:
                    navigation.getMenu().getItem(3).setChecked(true);
                    return;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // Set up Nav
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Change menu highlight when swipe
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);

        // Tes
//        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//
//
//            int intColorCode=0;
//
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//
//
//                intColorCode=-(verticalOffset);
//                if(intColorCode>255)
//                    intColorCode=255;
//
//                toolbar.getBackground().setAlpha(intColorCode);
//                toolbar.setAlpha(intColorCode/256f);
//
//
//            }
//        });



    }

    public void onCardClick(View v){
//        Toast.makeText(this, "Todo : Retrieve table and graph from SISERA Web", Toast.LENGTH_SHORT).show();
        TextView textView = (TextView) v.findViewById(R.id.link_id);
        ImageView circle = (ImageView) v.findViewById(R.id.imageView4);
        ImageView icon = (ImageView) v.findViewById(R.id.imageView9);

        String link = textView.getText().toString();
        if(link.equalsIgnoreCase("0")){
            Toast.makeText(this, "Maaf Data Belum Tersedia", Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(this, LinkDataFormOfflineActivity.class);
//            startActivity(i);
            return;
        }

        if(link.equalsIgnoreCase("90")|| link.equalsIgnoreCase("91")||link.equalsIgnoreCase("99")){
            Intent i = new Intent(this, LinkDataFormOfflineActivity.class);
            i.putExtra("idLink", link);
            i.putExtra("circle", getBitmap(circle));
            i.putExtra("icon", getBitmap(icon));

            startActivity(i);
            return;
        }

        Intent i = new Intent(this, LinkDataFormActivity.class);
        i.putExtra("link", link);
        i.putExtra("circle", getBitmap(circle));
        i.putExtra("icon", getBitmap(icon));
        startActivity(i);



    }

    private Bitmap getBitmap(ImageView imageView){
        imageView.buildDrawingCache();
        Bitmap image= imageView.getDrawingCache();
        return image;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Add search menu
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(this.SEARCH_SERVICE);
        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }
        final SearchView sv = searchView;

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // Toast like print
//                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

                if(mViewPager.getCurrentItem()==0){
                    Intent i = new Intent(getApplicationContext(), LinkDataFormSearchActivity.class);
                    i.putExtra("link", s);
                    startActivity(i);
                }

//                if(mViewPager.getCurrentItem()==1){
//                    brsFragment.getPublikasiSearch(s);
//                }

                if(mViewPager.getCurrentItem()==1||mViewPager.getCurrentItem()==2){
                    Toast.makeText(getApplicationContext(), "API Search tidak didukung untuk publikasi atau infografis", Toast.LENGTH_SHORT).show();
                }

                if(mViewPager.getCurrentItem()==3){
                    ensiklopediaFragment.getIstilahBySearch(s);
                }



                if(!sv.isIconified()) {
                    sv.setIconified(true);
                }
                searchItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

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
            new AlertDialog.Builder(this)
                    .setTitle("About")
                    .setMessage("Aplikasi SISERA dimaksudkan sebagai alat untuk mempermudah akses data sosial ekonomi Provinsi Sulawesi Tenggara.\n" +
                            "Kritik dan Saran terkait pengembangan aplikasi lebih lanjut dapat menghubungi atqo@bps.go.id ")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(position + 1);

            switch (position) {
                case 0:
                    return homeFragment;
                case 1:
                    return brsFragment;
                case 2:
                    return infografisFragment;
                case 3:
                    return ensiklopediaFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
            }
            return null;
        }
    }
}
