package inc.peace.crazygoodies.communication;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Udit on 1/7/2018.
 */



public class CommunicateToServer {
    /*public static JSONObject postToServer(String url,JSONObject object) throws JSONException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        JSONObject responseObject = null;
        try{
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("data",object.toString()));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            responseObject = parseResponse(response);
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseObject;

    }
    private static JSONObject parseResponse(HttpResponse response) throws IOException {
        HttpEntity respEntity = response.getEntity();
        JSONObject obj = null;
        InputStream inputStream = null;
        String respString = "";
        if(respEntity != null){
            try{
                inputStream = respEntity.getContent();
                respString = convertStreamToString(inputStream);
                obj = new JSONObject(respString);
                Log.d("obj response :::", obj.toString());
                Log.d("response  ::::::  ::: ",obj.getString("response"));
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    inputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
    private static String convertStreamToString(InputStream inputStream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return stringBuilder.toString();
    }*/
}