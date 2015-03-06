package com.example.xnote.sportsmatch;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,NavigationDrawerFragment.OnFragmentChangeListener,WriteHomeFragment.OnFragmentInteractionListener
                    ,WriteAwayFragment.OnFragmentInteractionListener,WriteIRemiseFragment.OnFragmentInteractionListener,WriteBeRemisedFragment.OnFragmentInteractionListener
                    ,WriteIFindMercenaryFragment.OnFragmentInteractionListener,WriteIWantToBeMercenaryFragment.OnFragmentInteractionListener
                    ,SearchClosestFragment.OnFragmentInteractionListener,SearchRecommendedFragment.OnFragmentInteractionListener,SearchTextListFragment.OnFragmentInteractionListener, SearchStadiumFragment.OnFragmentInteractionListener
                    ,ChattingChattingFragment.OnFragmentInteractionListener,MoreMoreFragment.OnFragmentInteractionListener{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private int state=0;
    final int DEFAULT_MODE=0;
    final int WRITE_MODE=1;
    final int SEARCH_MODE=2;
    final int CHATTING_MODE=3;
    final int MORE_MODE=4;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
    }
    public void initWidgets()
    {
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        setState(DEFAULT_MODE);

        Button writeBtn = (Button)findViewById(R.id.writeBtn);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d("state3", "state3 : " + state);
                setState(WRITE_MODE);
            }
        });

        Button searchBtn = (Button)findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d("state","state : " + state);
                setState(SEARCH_MODE);
            }
        });

        Button chattingBtn = (Button)findViewById(R.id.chattingBtn);
        chattingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d("state","state : " + state);
                setState(CHATTING_MODE);
            }
        });

        Button moreBtn = (Button)findViewById(R.id.moreBtn);
        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d("state","state : " + state);
                setState(MORE_MODE);
            }
        });

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Log.d("child item seledted : ","child item seledted : " );
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {

        switch (getState()) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public void setState(int newState)
    {
        this.state=newState;

        switch (getState())
        {
            default:
            case 0:
                mNavigationDrawerFragment.clearList();

                mNavigationDrawerFragment.getmGroupList().add("Write");
                mNavigationDrawerFragment.getmGroupList().add("Search");
                mNavigationDrawerFragment.getmGroupList().add("Chatting");
                mNavigationDrawerFragment.getmGroupList().add("More");

                mNavigationDrawerFragment.getmChildListWriteContent().add("Home");
                mNavigationDrawerFragment.getmChildListWriteContent().add("Away");
                mNavigationDrawerFragment.getmChildListWriteContent().add("양도해요");
                mNavigationDrawerFragment.getmChildListWriteContent().add("양도받아요");
                mNavigationDrawerFragment.getmChildListWriteContent().add("용병구해요");
                mNavigationDrawerFragment.getmChildListWriteContent().add("용병해요");

                mNavigationDrawerFragment.getmChildListSearchContent().add("주변검색");
                mNavigationDrawerFragment.getmChildListSearchContent().add("추천검색");
                mNavigationDrawerFragment.getmChildListSearchContent().add("글목록");
                mNavigationDrawerFragment.getmChildListSearchContent().add("경기장");

                 mNavigationDrawerFragment.getmChildListChattingContent().add("Chatting");

                mNavigationDrawerFragment.getmChildListMoreContent().add("More");

                mNavigationDrawerFragment.getmChildList().add(mNavigationDrawerFragment.getmChildListWriteContent());
                mNavigationDrawerFragment.getmChildList().add(mNavigationDrawerFragment.getmChildListSearchContent());
                mNavigationDrawerFragment.getmChildList().add(mNavigationDrawerFragment.getmChildListChattingContent());
                mNavigationDrawerFragment.getmChildList().add(mNavigationDrawerFragment.getmChildListMoreContent());

                mNavigationDrawerFragment.setAdaptor();
                break;
            case 1:
                mNavigationDrawerFragment.clearList();
                mNavigationDrawerFragment.getmGroupList().add("Write");
                mNavigationDrawerFragment.getmChildListWriteContent().add("Home");
                mNavigationDrawerFragment.getmChildListWriteContent().add("Away");
                mNavigationDrawerFragment.getmChildListWriteContent().add("양도해요");
                mNavigationDrawerFragment.getmChildListWriteContent().add("양도받아요");
                mNavigationDrawerFragment.getmChildListWriteContent().add("용병구해요");
                mNavigationDrawerFragment.getmChildListWriteContent().add("용병해요");
                mNavigationDrawerFragment.getmChildList().add(mNavigationDrawerFragment.getmChildListWriteContent());
                mNavigationDrawerFragment.setAdaptor();
                break;
            case 2:
                mNavigationDrawerFragment.clearList();
                mNavigationDrawerFragment.getmGroupList().add("Search");
                mNavigationDrawerFragment.getmChildListSearchContent().add("주변검색");
                mNavigationDrawerFragment.getmChildListSearchContent().add("추천검색");
                mNavigationDrawerFragment.getmChildListSearchContent().add("글목록");
                mNavigationDrawerFragment.getmChildListSearchContent().add("경기장");
                mNavigationDrawerFragment.getmChildList().add(mNavigationDrawerFragment.getmChildListSearchContent());
                mNavigationDrawerFragment.setAdaptor();
                break;
            case 3:
                mNavigationDrawerFragment.clearList();
                mNavigationDrawerFragment.getmGroupList().add("Chatting");
                mNavigationDrawerFragment.getmChildListChattingContent().add("Chatting");
                mNavigationDrawerFragment.getmChildList().add(mNavigationDrawerFragment.getmChildListChattingContent());
                mNavigationDrawerFragment.setAdaptor();
                break;
            case 4:
                mNavigationDrawerFragment.clearList();
                mNavigationDrawerFragment.getmGroupList().add("More");
                mNavigationDrawerFragment.getmChildListMoreContent().add("More");
                mNavigationDrawerFragment.getmChildList().add(mNavigationDrawerFragment.getmChildListMoreContent());
                mNavigationDrawerFragment.setAdaptor();
                break;
        }
    }

    public int getState()
    {
        return this.state;
    }

    public int onFragmentChageListener()
    {
        return getState();
    }
    public void onFragmentInteraction(Uri uri)
    {

    }
}
