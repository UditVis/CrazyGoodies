package inc.peace.crazygoodies.callbacks;

import org.json.JSONObject;

/**
 * Created by Udit on 1/12/2018.
 */

public interface AudioFilesCallback {
    public boolean getAudioCallback(JSONObject fromServer);
    public void setAudioContent();
    public void createBaseAudioList();
    public void setRecyclerClickListeners();
}
