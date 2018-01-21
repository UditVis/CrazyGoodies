package inc.peace.crazygoodies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import inc.peace.crazygoodies.models.AudioFilesModel;

/**
 * Created by Udit on 1/17/2018.
 */

public class AudioFileRecyclerAdapter extends RecyclerView.Adapter {
    private ArrayList<AudioFilesModel> audioFiles;

    public AudioFileRecyclerAdapter(ArrayList<AudioFilesModel> audioFiles) {
        this.audioFiles = audioFiles;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
