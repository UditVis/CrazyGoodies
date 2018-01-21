package inc.peace.crazygoodies.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import inc.peace.crazygoodies.R;
import inc.peace.crazygoodies.callbacks.CategoryTaskCallback;
import inc.peace.crazygoodies.communication.CommunicateToServer;
import inc.peace.crazygoodies.utils.UtilClass;

/**
 * Created by Udit on 1/7/2018.
 */

public class GetCategoryTask extends AsyncTask<String,Integer,JSONObject> {
    private HttpURLConnection httpURLConnection = null;
    private URL url = null;
    private Context mContext;
    private CategoryTaskCallback mCallback;
    private ProgressDialog progressDialog;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private String result = null;
    private boolean categoryCallback = false;
    private static final String POST_PARAMS = "action=GET_CATEGORIES";

    public GetCategoryTask(Context context){
        this.mContext = context;
        this.mCallback = (CategoryTaskCallback) context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Getting Categories...");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    protected void onPostExecute(JSONObject jobj) {
        super.onPostExecute(jobj);
        progressDialog.dismiss();
        if(categoryCallback){
            mCallback.setCategories();
            mCallback.setUpTabsAndViewPager();
            //new GetAudioFilesTask(mContext).execute("Audio files");
        }else{
            Toast.makeText(mContext,"Failed to retrieve categories",Toast.LENGTH_LONG).show();
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
            Log.d("CrazyGoodies","connection open  " + strings[0]);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            outputStream = httpURLConnection.getOutputStream();
            outputStream.write(POST_PARAMS.getBytes());
            outputStream.flush();
            outputStream.close();
            int respCode = httpURLConnection.getResponseCode();
            Log.d("Response Message","ss::" + respCode);

            httpURLConnection.connect();
            if(respCode == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
            }else{
                Toast.makeText(mContext,"There was a server error",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                inputStream = httpURLConnection.getErrorStream();
            }
            String result = UtilClass.convertStreamToString(inputStream);
            Log.d("String Result : ", result);
            JSONObject fromServer = UtilClass.convertStringToJson(result);
            this.categoryCallback = mCallback.getCategoryCallback(fromServer);
            //CommunicateToServer.postToServer(urlString,new JSONObject().put("data","data"));
            inputStream.close();
            httpURLConnection.disconnect();
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }





}
