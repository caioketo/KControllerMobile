package com.keto.controller.util;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.keto.controller.ListaActivity;
import com.keto.controller.MainActivity;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by developer on 02/09/13.
 */
public class HttpHelper extends AsyncTask<String, String, Void> {
    private ProgressDialog progressDialog = new ProgressDialog(MainActivity.ctx);
    InputStream inputStream = null;
    String result = "";
    ArrayList<JSONObject> array;

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Your progress dialog message...");
        progressDialog.show();
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface arg0) {
                HttpHelper.this.cancel(true);
            }
        });
    }


    @Override
    protected Void doInBackground(String... params) {
        String url = params[0];
        this.array = Util.parseJSON(Util.getJSON(url));
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        this.progressDialog.dismiss();
        ListaActivity.lista.jsons = this.array;
        ListaActivity.lista.refresh();
    }
}
