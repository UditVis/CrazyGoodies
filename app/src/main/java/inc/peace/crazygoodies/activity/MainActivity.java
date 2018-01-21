package inc.peace.crazygoodies.activity;

import android.content.Context;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


import inc.peace.crazygoodies.callbacks.AudioFilesCallback;
import inc.peace.crazygoodies.fragments.ContentContainerFragment;
import inc.peace.crazygoodies.R;
import inc.peace.crazygoodies.adapters.ContentPagerAdapter;
import inc.peace.crazygoodies.callbacks.CategoryTaskCallback;
import inc.peace.crazygoodies.models.AudioFilesModel;
import inc.peace.crazygoodies.models.AudioFoldersModel;
import inc.peace.crazygoodies.models.CategoryModel;
import inc.peace.crazygoodies.tasks.GetAudioFilesTask;
import inc.peace.crazygoodies.tasks.GetCategoryTask;
import inc.peace.crazygoodies.utils.AscendingStringComparator;

/**
 * Created by Udit on 1/7/2018.
 */

public class MainActivity extends AppCompatActivity implements CategoryTaskCallback {
    private JSONObject dataFromServer = null;
    private JSONObject audioListFromServer = null;
    private ArrayList<CategoryModel> categories = null;
    private ContentPagerAdapter contentPagerAdapter = null;
    private ArrayList<AudioFoldersModel> audioFolders = null;
    private ArrayList<AudioFilesModel> audioFiles = null;

    private Toolbar toolbar;
    private TabLayout categoryTabs;
    private ViewPager tabContentPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView();
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        categoryTabs = (TabLayout) findViewById(R.id.category_tabs);
        tabContentPager = (ViewPager) findViewById(R.id.tab_content);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(false);


        Log.d("CrazyGoodies","StartActivity");
        GetCategoryTask getCategoryTask =  new GetCategoryTask(MainActivity.this);
        getCategoryTask.execute("category task");
    }


    @Override
    public void setCategories() {
        categories = getCategoryList(dataFromServer);
        Log.d("CrazyGoodies",categories.toString());
    }

    private ArrayList<CategoryModel> getCategoryList(JSONObject data){
        Log.d("CrazyGoodies","getCategoryList" + data);
        try {
            ArrayList<CategoryModel> categoryModels = new ArrayList<>();
            if(data != null){
               JSONArray categoryDataArray = new JSONArray(data.getString("data"));
               for(int i = 0;i < categoryDataArray.length();i++){
                   JSONObject categoryJson = categoryDataArray.getJSONObject(i);
                   CategoryModel categoryModel = new CategoryModel();
                   categoryModel.setCategoryName(categoryJson.getString("folder_name"));
                   categoryModel.setCategoryServerPath(categoryJson.getString("folder_path"));
                   categoryModel.setCategoryParent(categoryJson.getString("folder_parent"));
                    categoryModels.add(categoryModel);
               }
                Log.d("category list","categorymodels: " + categoryModels.toString());
            }
            return categoryModels;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<CategoryModel>();
    }



    @Override
    public boolean getCategoryCallback(JSONObject fromServer) {
        Log.d("CrazyGoodies","getCategoryCallback" + fromServer.toString());
        dataFromServer = fromServer;
        if(dataFromServer != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void setUpTabsAndViewPager(){
        contentPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
        ArrayList<String> categoryList = new ArrayList<String>();
        for(int i = 0;i < categories.size();i++){
            fragmentList.add(ContentContainerFragment.newInstance(categories.get(i).getCategoryName().toUpperCase()));
            categoryList.add(categories.get(i).getCategoryName());
        }
        //categoryList.sort(new AscendingStringComparator()); --> need to put here and LATER in settings (later means sometime in distant future)
        contentPagerAdapter.addFragmentsToPager(fragmentList,categoryList);
        tabContentPager.setAdapter(contentPagerAdapter);
        categoryTabs.setupWithViewPager(tabContentPager,true);
        categoryTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("onTabSelectedListener","Tab selected : " + tab.getPosition() + "::" + tab.getText() + "::" + tab.getContentDescription());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("onTabSelectedListener","onTabUnselected : " + tab.getPosition() + "::" + tab.getText() + "::" + tab.getContentDescription());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("onTabSelectedListener","onTabReselected : " + tab.getPosition() + "::" + tab.getText() + "::" + tab.getContentDescription());
            }
        });
        categoryTabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(tabContentPager));

        tabContentPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("addOnPageChangeListener","onPageScrolled : " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("addOnPageChangeListener","onPageSelected : " + position + "::" + tabContentPager.getAdapter().getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("addOnPageChangeListener","onPageScrollStateChanged : " + state);
            }
        });
    }

}
