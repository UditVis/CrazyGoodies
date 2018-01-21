package inc.peace.crazygoodies.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import inc.peace.crazygoodies.activity.MainActivity;
import inc.peace.crazygoodies.callbacks.AudioFilesCallback;
import inc.peace.crazygoodies.callbacks.CategoryTaskCallback;
import inc.peace.crazygoodies.fragments.ContentContainerFragment;
import inc.peace.crazygoodies.utils.UtilClass;

/**
 * Created by Udit on 1/12/2018.
 */

public class GetAudioFilesTask extends AsyncTask<String,Integer,JSONObject> {

    private HttpURLConnection httpURLConnection = null;
    private URL url = null;
    private Context mContext;
    private AudioFilesCallback mCallback;
    private ProgressDialog progressDialog;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    String result = null;
    private boolean audioCallback = false;
    private final String POST_PARAMS = "action=GET_AUDIO";

    public GetAudioFilesTask(Context context, ContentContainerFragment fragment){
        this.mContext = context;
        this.mCallback = (AudioFilesCallback) fragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Getting Audio Content...");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(JSONObject object) {
        super.onPostExecute(object);
        progressDialog.dismiss();
        if(audioCallback){
            //Do something
            mCallback.setAudioContent();
            mCallback.createBaseAudioList();
        }else{
            Toast.makeText(mContext,"Unable to get audio content",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        String urlString = "http://b59a6296.ngrok.io/trialS/getFileTypes";
        try{
            url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            outputStream = httpURLConnection.getOutputStream();
            outputStream.write(POST_PARAMS.getBytes());
            outputStream.flush();
            outputStream.close();
            int respCode = httpURLConnection.getResponseCode();
            httpURLConnection.connect();
            if(respCode == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
            }else{
                Toast.makeText(mContext,"There was a server error",Toast.LENGTH_LONG).show();
                inputStream = httpURLConnection.getErrorStream();
            }
            String result = UtilClass.convertStreamToString(inputStream);
            inputStream.close();
            httpURLConnection.disconnect();
            JSONObject fromServer = UtilClass.convertStringToJson(result);
            Log.d("GetAudioFilesTask","fromServer : " + fromServer.get("data"));
            audioCallback = mCallback.getAudioCallback(fromServer);
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
