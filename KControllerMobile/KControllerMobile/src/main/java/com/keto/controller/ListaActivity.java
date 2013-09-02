package com.keto.controller;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

import com.keto.controller.classes.ArtistaAdapter;
import com.keto.controller.util.Util;

public class ListaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        ArtistaAdapter adapter = new ArtistaAdapter(Util.fromJsonToArtista(Util.parseJSON(Util.getJSON("http://jangadaserver.no-ip.info:5000/artists/json"))), this);
        ListView lista = (ListView)findViewById(R.id.lstLista);
        lista.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lista, menu);
        return true;
    }
    
}
