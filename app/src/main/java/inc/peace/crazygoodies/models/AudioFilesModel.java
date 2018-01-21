package inc.peace.crazygoodies.models;

/**
 * Created by Udit on 1/15/2018.
 */

public class AudioFilesModel {
    private String audioFileName;
    private String audioFileExt;
    private String audioParentFolderName;
    private String filePath;

    public String getAudioFileName() {
        return audioFileName;
    }

    public void setAudioFileName(String audioFileName) {
        this.audioFileName = audioFileName;
    }

    public String getAudioFileExt() {
        return audioFileExt;
    }

    public void setAudioFileExt(String audioFileExt) {
        this.audioFileExt = audioFileExt;
    }

    public String getAudioParentFolderName() {
        return audioParentFolderName;
    }

    public void setAudioParentFolderName(String audioParentFolderName) {
        this.audioParentFolderName = audioParentFolderName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
