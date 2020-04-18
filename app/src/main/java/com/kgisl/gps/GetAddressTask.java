package com.kgisl.gps;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class GetAddressTask extends AsyncTask<Void,Void,String> {

    @Override
    protected String doInBackground(Void... voids) {
        HttpGet request = new HttpGet("https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=11.0282311&lon=77.03662419");
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse res = null;
        try {
            res = client.execute(request);
            HttpEntity httpEntity = res.getEntity();
            JSONObject locationObject = new JSONObject(EntityUtils.toString(httpEntity));
            Log.d("address",locationObject.get("address").toString());
            client.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
