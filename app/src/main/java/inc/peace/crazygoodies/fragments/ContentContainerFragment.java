package inc.peace.crazygoodies.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import inc.peace.crazygoodies.R;
import inc.peace.crazygoodies.adapters.AudioFolderRecyclerAdapter;
import inc.peace.crazygoodies.callbacks.AudioFilesCallback;
import inc.peace.crazygoodies.listeners.AudioRecyclerClickListener;
import inc.peace.crazygoodies.listeners.AudioRecyclerClickListenerImpl;
import inc.peace.crazygoodies.models.AudioFilesModel;
import inc.peace.crazygoodies.models.AudioFoldersModel;
import inc.peace.crazygoodies.tasks.GetAudioFilesTask;
import inc.peace.crazygoodies.utils.UtilClass;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ContentContainerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentContainerFragment extends Fragment implements AudioFilesCallback {

    private String fragmentCategory = "";
    private JSONObject audioListFromServer = null;
    private ArrayList<AudioFilesModel> audioFiles = null;
    private ArrayList<AudioFoldersModel> audioFolders = null;
    private RecyclerView audioFolderRecycler;
    private AudioFolderRecyclerAdapter audioFolderAdapter,audioFileAdapter;


    // private OnFragmentInteractionListener mListener;

    public ContentContainerFragment() {
        // Required empty public constructor
    }

    public static ContentContainerFragment newInstance(String category) {
        ContentContainerFragment fragment = new ContentContainerFragment();
        Bundle fragArgBundle = new Bundle();
        fragArgBundle.putString("FRAG_CAT",category);
        fragment.setArguments(fragArgBundle);
        Log.d("ContentContainerFrag","newInstance :: " + category);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           fragmentCategory = getArguments().getString("FRAG_CAT");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("ContentContainerFrag","category : " + fragmentCategory);
        switch (fragmentCategory){
            case "AUDIO":
                new GetAudioFilesTask(getContext(),this).execute("Audio Files");
                return inflater.inflate(R.layout.fragment_audio_content,container,false);
            case "VIDEO":
                return inflater.inflate(R.layout.fragment_video_content,container,false);
            case "FILES":
                return inflater.inflate(R.layout.fragment_file_content,container,false);
            default:
                return inflater.inflate(R.layout.fragment_content_container,container,false);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
/*
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
*/

 /*   @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/
/*

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
/*    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/

    @Override
    public boolean getAudioCallback(JSONObject fromServer) {
        Log.d("cv","geteAudioCallback");
        this.audioListFromServer = fromServer;
        if(audioListFromServer != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void setAudioContent() {
        Log.d("cv","setAudioContent");
        try {
            if(audioListFromServer != null){
                audioFolders = new ArrayList<AudioFoldersModel>();
                audioFiles = new ArrayList<AudioFilesModel>();
                JSONObject audioContent = new JSONObject(audioListFromServer.get("data").toString());
                Log.d("Audio Content","Audio Content : " + audioContent);
                Iterator iterator = audioContent.keys();
                while (iterator.hasNext()){
                    AudioFoldersModel audioFolder = new AudioFoldersModel();
                    String folderPath = "";
                    audioFolder.setFolderName(iterator.next().toString());
                    JSONArray arrayOfFilesInFolder = audioContent.getJSONArray(audioFolder.getFolderName());
                    for(int i = 0;i<arrayOfFilesInFolder.length();i++){
                        JSONObject fileJsonObj = arrayOfFilesInFolder.getJSONObject(i);
                        String filePath = fileJsonObj.getString("file_path");
                        String fileParent = fileJsonObj.getString("file_parent");
                        String fileName = fileJsonObj.getString("file_name");
                        String fileExt = fileJsonObj.getString("file_extension");
                        AudioFilesModel audioFile = new AudioFilesModel();
                        audioFile.setAudioFileName(fileName);
                        audioFile.setAudioFileExt(fileExt);
                        audioFile.setFilePath(filePath);
                        audioFile.setAudioParentFolderName(audioFolder.getFolderName());
                        folderPath = fileParent;
                        audioFiles.add(audioFile);
                    }
                    audioFolder.setFolderPath(folderPath);
                    audioFolder.setNumberOfFilesInside(arrayOfFilesInFolder.length());
                    audioFolder.setFolderParent("audio");
                    audioFolders.add(audioFolder);
                }
                testMethod(audioFiles);
                testMethod2(audioFolders);
            }else{
                Toast.makeText(getContext(),"No Audio Content Found",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(),"No Audio Content Found",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void createBaseAudioList() {
        try{
            Log.d("cv","createBaseAudioList");
            audioFolderRecycler = (RecyclerView) getActivity().findViewById(R.id.audio_folder_recycler);
            audioFolderAdapter = new AudioFolderRecyclerAdapter(audioFolders);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            audioFolderRecycler.setLayoutManager(mLayoutManager);
            audioFolderRecycler.setItemAnimator(new DefaultItemAnimator());
            audioFolderRecycler.setAdapter(audioFolderAdapter);
            Log.d("cv",audioFolderAdapter.getItemCount()+"<<---- size ");
            audioFolderAdapter.notifyDataSetChanged();
            setRecyclerClickListeners();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<JSONObject> getFolderJsonList(ArrayList<AudioFoldersModel> audioFolders){
        ArrayList<JSONObject> returnList = new ArrayList<JSONObject>();
        try{
            JSONObject tempObj;
            for(AudioFoldersModel audioFolder : audioFolders){
                tempObj = new JSONObject();
                tempObj.put("folder_name",audioFolder.getFolderName()).put("parent","audio").put("no_of_files",audioFolder.getNumberOfFilesInside());
                returnList.add(tempObj);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnList;
    }

    public ArrayList<JSONObject> getFileJsonList (ArrayList<AudioFilesModel> audioFiles,String parentFolderName){
        ArrayList<JSONObject> returnList = new ArrayList<JSONObject>();
        try{
            JSONObject tempObj;
            for(AudioFilesModel audioFile : audioFiles){
                tempObj = new JSONObject();
                tempObj.put("file_name",audioFile.getAudioFileName()).put("parent",parentFolderName);
                returnList.add(tempObj);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnList;
    }

    @Override
    public void setRecyclerClickListeners() {
        audioFolderRecycler.addOnItemTouchListener(new AudioRecyclerClickListenerImpl(getContext(), audioFolderRecycler, new AudioRecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {
                String folderName = audioFolders.get(position).getFolderName();
                ArrayList<AudioFilesModel> filesInFolderList = getFilesInFolderList(folderName);
                Log.d("cv","files in folder " + folderName + " are :");
                for(AudioFilesModel afm : filesInFolderList){   //This is just for test
                    String msg = afm.getAudioFileName()+afm.getAudioFileExt();
                    Log.d("cv",msg);
                   // Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                }                                                             //Upto here
                //JSONObject objForNextFragment = new JSONObject();

                //Toast.makeText(getContext(),"pos: " + position + " : " + folderName,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    //Testing methods to be removed later on. Used for testing various sets of data and UI components
    public void testMethod(ArrayList<AudioFilesModel> list){
        Log.d("cv","testMethod");
        for(int i = 0;i< list.size();i++){
            Log.d("",list.get(i).getAudioFileName());
        }
    }
    public void testMethod2(ArrayList<AudioFoldersModel> list){
        Log.d("cv","testMethod2");
        for(int i = 0;i< list.size();i++){
            Log.d("",list.get(i).getFolderName());
        }
    }

    public ArrayList<AudioFilesModel> getFilesInFolderList(String folderName){
        ArrayList<AudioFilesModel>afmList = new ArrayList<AudioFilesModel>();
        for(AudioFilesModel afm : audioFiles){
            String parentFolder = afm.getAudioParentFolderName();
            if(parentFolder.equalsIgnoreCase(folderName) && UtilClass.checkWhetherAudio(afm)){
                afmList.add(afm);
            }
        }
        return afmList;
    }
}
