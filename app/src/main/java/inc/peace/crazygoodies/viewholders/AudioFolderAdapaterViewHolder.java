package inc.peace.crazygoodies.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import inc.peace.crazygoodies.R;

/**
 * Created by Udit on 1/15/2018.
 */

public class AudioFolderAdapaterViewHolder extends RecyclerView.ViewHolder {

    public TextView folderNameTextView,numOfFilesTextView;

    public AudioFolderAdapaterViewHolder(View itemView) {
        super(itemView);
        folderNameTextView = (TextView) itemView.findViewById(R.id.audio_folder_name_text);
        numOfFilesTextView = (TextView) itemView.findViewById(R.id.audio_number_files_text);
    }
}
