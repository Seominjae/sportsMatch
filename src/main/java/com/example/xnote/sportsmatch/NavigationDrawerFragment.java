package com.example.xnote.sportsmatch;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawerFragment extends Fragment {

    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ExpandableListView mDrawerListView;
    private View mFragmentContainerView;

    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;

    private ArrayList<String> mGroupList = null;
    private ArrayList<ArrayList<String>> mChildList = null;
    private ArrayList<String> mChildListContent = null;

    private ArrayList<String> mChildListWriteContent = null;
    private ArrayList<String> mChildListSearchContent = null;
    private ArrayList<String> mChildListChattingContent = null;
    private ArrayList<String> mChildListMoreContent = null;

    OnFragmentChangeListener mListener;

    private int groupPosition;
    private int childPosition;

    Fragment fragment;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Read in the flag indicating whether or not the user has demonstrated awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }

        // Select either the default item (0) or the last selected item.
        selectItem(mCurrentSelectedPosition);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        mDrawerListView = (ExpandableListView) inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        mGroupList = new ArrayList<String>();
        mChildList = new ArrayList<ArrayList<String>>();
        mChildListWriteContent = new ArrayList<String>();
        mChildListSearchContent = new ArrayList<String>();
        mChildListChattingContent = new ArrayList<String>();
        mChildListMoreContent = new ArrayList<String>();
//        mGroupList.add("가위");
//        mGroupList.add("바위");
//        mGroupList.add("보");
//
//        mChildListContent.add("1");
//        mChildListContent.add("2");
//        mChildListContent.add("3");
//
//        mChildList.add(mChildListContent);
//        mChildList.add(mChildListContent);
//        mChildList.add(mChildListContent);

        mDrawerListView.setAdapter(new BaseExpandableAdapter(getActivity(), mGroupList, mChildList));

        mDrawerListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                setGroupPosition(groupPosition);
                return false;
            }
        });

        // 차일드 클릭 했을 경우 이벤트
        mDrawerListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                setChildPosition(childPosition);
                fragment = new WriteHomeFragment();

                //mListener.onFragmentChageListener() return state
                switchFragment(mListener.onFragmentChageListener());

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,fragment)
                        .commit();
                return false;
            }
        });

//        // 그룹이 닫힐 경우 이벤트
//        mDrawerListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getActivity().getApplicationContext(), "g Collapse = " + groupPosition,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // 그룹이 열릴 경우 이벤트
//        mDrawerListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getActivity().getApplicationContext(), "g Expand = " + groupPosition,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });



//        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                selectItem(position);
//            }
//        });
//        mDrawerListView.setAdapter(new ArrayAdapter<String>(
//                getActionBar().getThemedContext(),
//                android.R.layout.simple_list_item_activated_1,
//                android.R.id.text1,
//                new String[]{
//                        getString(R.string.title_section1),
//                        getString(R.string.title_section2),
//                        getString(R.string.title_section3),
//                }));
//        mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
        return mDrawerListView;
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    // The user manually opened the drawer; store this flag to prevent auto-showing
                    // the navigation drawer automatically in the future.
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
        // per the navigation drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
        if (mDrawerListView != null) {
            mDrawerListView.setItemChecked(position, true);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
            mListener = (OnFragmentChangeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks."
                    + " must implement mListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        if (mDrawerLayout != null && isDrawerOpen()) {
            inflater.inflate(R.menu.global, menu);
            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.action_example) {
            Toast.makeText(getActivity(), "Example action.", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Per the navigation drawer design guidelines, updates the action bar to show the global app
     * 'context', rather than just what's in the current screen.
     */
    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(R.string.app_name);
    }

    private ActionBar getActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(int position);
    }
    public ArrayList<String> getmGroupList() {
        return mGroupList;
    }

    public ArrayList<ArrayList<String>> getmChildList() {
        return mChildList;
    }

    public ArrayList<String> getmChildListContent() {
        return mChildListContent;
    }
    public ArrayList<String> getmChildListWriteContent() {
        return mChildListWriteContent;
    }

    public ArrayList<String> getmChildListSearchContent() {
        return mChildListSearchContent;
    }

    public ArrayList<String> getmChildListChattingContent() {
        return mChildListChattingContent;
    }

    public ArrayList<String> getmChildListMoreContent() {
        return mChildListMoreContent;
    }
    public void clearList()
    {
        mGroupList.clear();
        mChildListWriteContent.clear();
        mChildListSearchContent.clear();
        mChildListChattingContent.clear();
        mChildListMoreContent.clear();
        mChildList.clear();
    }
    public void setAdaptor()
    {
        mDrawerListView.setAdapter(new BaseExpandableAdapter(getActivity(), mGroupList, mChildList));
    }

    public interface OnFragmentChangeListener{
        public int onFragmentChageListener();
    }

    public int getGroupPosition() {
        return groupPosition;
    }

    public int getChildPosition() {
        return childPosition;
    }

    public void setGroupPosition(int groupPosition) {
        this.groupPosition = groupPosition;
    }

    public void setChildPosition(int childPosition) {
        this.childPosition = childPosition;
    }

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
    public void selectWriteChildFragment(int childPosition)
    {
        switch (childPosition)
        {
            default:
            case 0:
                fragment = new WriteHomeFragment();
                break;
            case 1:
                fragment = new WriteAwayFragment();
                break;
            case 2:
                fragment = new WriteIRemiseFragment();
                break;
            case 3:
                fragment = new WriteBeRemisedFragment();
                break;
            case 4:
                fragment = new WriteIFindMercenaryFragment();
                break;
            case 5:
                fragment = new WriteIWantToBeMercenaryFragment();
                break;
        }
    }
    public void selectSearchChildFragment(int childPosition)
    {
        switch (childPosition)
        {
            default:
            case 0:
                fragment = new SearchClosestFragment();
                break;
            case 1:
                fragment = new SearchRecommendedFragment();
                break;
            case 2:
                fragment = new SearchTextListFragment();
                break;
            case 3:
                fragment = new SearchStadiumFragment();
                break;
        }
    }
    public void selectChattingChildFragment(int childPosition)
    {
        switch (childPosition)
        {
            default:
            case 0:
                fragment = new ChattingChattingFragment();
                break;
        }
    }
    public void selectMoreChildFragment(int childPosition)
    {
        switch (childPosition)
        {
            default:
            case 0:
                fragment = new MoreMoreFragment();
                break;
        }
    }

    public void switchFragment(int state)
    {
        switch (state)
        {
            default:
            case 0:
                switch (groupPosition)
                {
                    default:
                    case 0:
                        selectWriteChildFragment(childPosition);
                        break;
                    case 1:
                        selectSearchChildFragment(childPosition);
                        break;
                    case 2:
                        selectChattingChildFragment(childPosition);
                        break;
                    case 3:
                        selectMoreChildFragment(childPosition);
                        break;
                }
                break;
            case 1:
                selectWriteChildFragment(childPosition);
                break;
            case 2:
                selectSearchChildFragment(childPosition);
                break;
            case 3:
                selectChattingChildFragment(childPosition);
                break;
            case 4:
                selectMoreChildFragment(childPosition);
                break;
        }
    }
}
