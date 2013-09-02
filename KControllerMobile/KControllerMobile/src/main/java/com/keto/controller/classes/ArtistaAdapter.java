package com.keto.controller.classes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.keto.controller.R;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by developer on 02/09/13.
 */
public class ArtistaAdapter extends BaseAdapter {
    private ArrayList<Artista> Lista = new ArrayList<Artista>();
    private static LayoutInflater inflater=null;
    private Activity act;

    public ArtistaAdapter(ArrayList<Artista> data, Activity _act) {
        act = _act;
        Lista = data;
        inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return Lista.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.lista_item, null);

        assert vi != null;
        TextView tvwNome = (TextView)vi.findViewById(R.id.tvwNome);
        tvwNome.setText(Lista.get(position).Nome);
        try {
            URL url = new URL(Lista.get(position).Url);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            ImageView imageView = (ImageView)vi.findViewById(R.id.imvImagem);
            imageView.setImageBitmap(bmp);
        }
        catch (Exception ex) { }

        return vi;
    }
}
