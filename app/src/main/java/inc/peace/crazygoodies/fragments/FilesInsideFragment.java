package inc.peace.crazygoodies.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import inc.peace.crazygoodies.models.AudioFilesModel;
import inc.peace.crazygoodies.models.AudioFoldersModel;

/**
 * Created by Udit on 1/16/2018.
 */

public class FilesInsideFragment extends Fragment {
    private ArrayList<AudioFilesModel> audioFiles;
    private AudioFoldersModel parentFolder;
    private RecyclerView filesRecyclerView;
    private RecyclerView.Adapter filesRecyclerAdapter;
    public FilesInsideFragment(){
        //Required Empty Constructor
    }

    public Fragment newInstance(JSONObject fromPrevFragment){
        initFragment(fromPrevFragment);
        FilesInsideFragment fragment = new FilesInsideFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private boolean initFragment(JSONObject fromPrevFragment){
        boolean fragmentInitialized = false;
        try{
            this.audioFiles = (ArrayList<AudioFilesModel>) fromPrevFragment.get("audio_files");
            this.parentFolder = (AudioFoldersModel) fromPrevFragment.get("parent_folder");
            fragmentInitialized = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return fragmentInitialized;
    }
}
