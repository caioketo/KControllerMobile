package com.keto.controller.util;

/**
 * Created by developer on 02/09/13.
 */

import com.keto.controller.classes.Artista;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Util {

    public static String getJSON(String url) {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static ArrayList<JSONObject> parseJSON(String jsonStr) {
        ArrayList<JSONObject> result = new ArrayList<JSONObject>();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                result.add(jsonObject);
            }
        } catch (Exception e) {

        }
        return result;
    }

    public static ArrayList<Artista> fromJsonToArtista(ArrayList<JSONObject> jsonObjects) {
        ArrayList<Artista> retorno = new ArrayList<Artista>();
        for (JSONObject objeto : jsonObjects) {
            try {
                Artista artista = new Artista();
                artista.Id = objeto.getInt("id");
                artista.Nome = objeto.getString("name");
                artista.Url = objeto.getString("url");
                retorno.add(artista);
            }
            catch (Exception e) { }
        }
        return retorno;
    }
}
