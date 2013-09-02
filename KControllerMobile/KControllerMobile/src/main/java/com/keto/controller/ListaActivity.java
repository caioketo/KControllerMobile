package com.keto.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.keto.controller.classes.ArtistaAdapter;
import com.keto.controller.util.HttpHelper;
import com.keto.controller.util.Util;

import org.json.JSONObject;

import java.util.ArrayList;

public class ListaActivity extends Activity {
    public static ListaActivity lista;
    public ArrayList<JSONObject> jsons;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        lista = this;
        //ArtistaAdapter adapter = new ArtistaAdapter(Util.fromJsonToArtista(Util.parseJSON(Util.getJSON("http://jangadaserver.no-ip.info:5000/artists/json"))), this);
        HttpHelper helper = new HttpHelper();
        helper.execute("http://jangadaserver.no-ip.info:5000/artists/json");
        listView = (ListView)findViewById(R.id.lstLista);
        //lista.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lista, menu);
        return true;
    }

    public void refresh() {
        ArtistaAdapter adapter = new ArtistaAdapter(Util.fromJsonToArtista(this.jsons), this);
        listView.setAdapter(adapter);
    }
    
}
