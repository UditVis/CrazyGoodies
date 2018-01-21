package inc.peace.crazygoodies.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import inc.peace.crazygoodies.R;
import inc.peace.crazygoodies.models.AudioFilesModel;
import inc.peace.crazygoodies.models.AudioFoldersModel;
import inc.peace.crazygoodies.viewholders.AudioFolderAdapaterViewHolder;

/**
 * Created by Udit on 1/15/2018.
 */

public class AudioFolderRecyclerAdapter extends RecyclerView.Adapter<AudioFolderAdapaterViewHolder> { //TODO if using same adapter for folders and files technique works then rename some of the elements to just audio(generic) instead of audio files and folders

    private ArrayList<AudioFoldersModel> audioFolders;
    private ArrayList<AudioFilesModel> audioFiles;
    private JSONObject objectForRecycler;
    private ArrayList<JSONObject> listForRecycler;


    public AudioFolderRecyclerAdapter(JSONObject objectForRecycler){
        this.objectForRecycler = objectForRecycler;
    }

    public AudioFolderRecyclerAdapter(ArrayList<JSONObject> listForRecycler){
        Log.d("tttt","audioFolderRecyclerAdapter()");
        this.listForRecycler = listForRecycler;
    }

    @Override
    public void onBindViewHolder(AudioFolderAdapaterViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        Log.d("tttt","onBindViewHolder()");
    }

    @Override
    public AudioFolderAdapaterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_folder_row,parent,false);
        Log.d("tttt","onCreateViewHolder()");
        return new AudioFolderAdapaterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AudioFolderAdapaterViewHolder holder, int position) {
      try{
          Log.d("tttt","onBindViewHolder()");
          JSONObject objectForRecycler = listForRecycler.get(position);
          Log.d("tttt","onBindViewHolder() ---> " + objectForRecycler);
          if(objectForRecycler.getString("parent").equalsIgnoreCase("audio")){
              holder.folderNameTextView.setText(objectForRecycler.getString("folder_name"));
              holder.numOfFilesTextView.setText("No. of files : " + objectForRecycler.getString("no_of_files"));
              Log.d("tttt","onBindViewHolder() ---> parent-> audio -------->  " + objectForRecycler);
          }else if(!objectForRecycler.getString("parent").equalsIgnoreCase("audio")){
              this.audioFiles = (ArrayList<AudioFilesModel>) objectForRecycler.get("list_for_adapter");
              AudioFilesModel audioFile = audioFiles.get(position);
              holder.folderNameTextView.setText(audioFile.getAudioFileName() + "." + audioFile.getAudioFileExt());
              Log.d("tttt","onBindViewHolder() ---> parent-> not audio -------->  " + objectForRecycler);
          }
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    @Override
    public int getItemCount()
    {
        Log.d("tttt","getItemCount()");
        return listForRecycler.size();
    }
}
