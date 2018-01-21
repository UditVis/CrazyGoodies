package inc.peace.crazygoodies.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import inc.peace.crazygoodies.models.AudioFilesModel;

/**
 * Created by Udit on 1/7/2018.
 */

public class UtilClass {
    public static JSONObject convertStringToJson(String jsonString) throws JSONException{
        //may throw error at some point of time, be aware.
        return new JSONObject(jsonString);
    }

    public static String convertStreamToString(InputStream inputStream){
      try {
          BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
          int ch;
          StringBuilder sb = new StringBuilder();
          while((ch = bufferedInputStream.read()) != -1){
              sb.append((char)ch);
          }
          String result = sb.toString();
          inputStream.close();
          return result;
      }catch (Exception e){
          e.printStackTrace();
          return "";
      }
    }

    public static boolean checkWhetherAudio(AudioFilesModel audioFilesModel){
        String fileExt = audioFilesModel.getAudioFileExt();
        if(fileExt.equalsIgnoreCase("mp3") || fileExt.equalsIgnoreCase("wav") || fileExt.equalsIgnoreCase("m3a")){
            return true;
        }else{
            return false;
        }
    }

}
