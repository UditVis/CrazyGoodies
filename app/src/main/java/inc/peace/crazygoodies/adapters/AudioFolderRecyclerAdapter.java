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

    public AudioFolderRecyclerAdapter(ArrayList<AudioFoldersModel> audioFolders){
        this.audioFolders = audioFolders;
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
        Log.d("tttt","onBindViewHolder()");
      try{
          AudioFoldersModel audioFolder = audioFolders.get(position);
          holder.folderNameTextView.setText(audioFolder.getFolderName());
          holder.numOfFilesTextView.setText("No. of files : " + audioFolder.getNumberOfFilesInside());
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    @Override
    public int getItemCount()
    {
        Log.d("tttt","getItemCount()");
        return audioFolders.size();
    }
}
