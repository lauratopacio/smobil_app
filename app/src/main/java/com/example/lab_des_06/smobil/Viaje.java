package com.example.lab_des_06.smobil;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.snowdream.android.widget.SmartImageView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Viaje extends AppCompatActivity {
    private ListView listView;
    ArrayList usuario=new ArrayList();
    ArrayList imagen=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viaje);
        listView=(ListView)findViewById(R.id.listView);
        descargarDatos();
    }
    private void descargarDatos(){
        usuario.clear();

        final ProgressDialog progressDialog= new ProgressDialog(Viaje.this);
        progressDialog.setMessage("cargando Datos...");
        progressDialog.show();
        AsyncHttpClient client=new  AsyncHttpClient();
        client.get("http://158.97.121.71/smobil_app/Modulos/Viajes/viaje.php", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    progressDialog.dismiss();

                    try {
                        JSONArray jsonArray=new JSONArray(new String(responseBody));

                        for (int i=0; i<jsonArray.length();i++){
                            //atrae todos los datos de la tabla por campos
                            usuario.add(jsonArray.getJSONObject(i).getString("usuario"));

                        }
                        listView.setAdapter(new ImagenAdapter(getApplicationContext()));
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    private class ImagenAdapter extends BaseAdapter {
        Context ctx;
        LayoutInflater layoutInflater;
        SmartImageView smartImageView;
        TextView vtusuario;
        public ImagenAdapter(Context applicationContext) {
            this.ctx=applicationContext;
            layoutInflater=(LayoutInflater)ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
          return imagen.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewGroup viewGroup=(ViewGroup)layoutInflater.inflate(R.layout.content_viaje,null);

            //instanciar o guardar los ID a las variables que arriba se declararon

            vtusuario=(TextView)viewGroup.findViewById(R.id.vtusuario);

            //asignar lo que nos trajo del servidor


            //Rect rect=new Rect(smartImageView.getLeft(),smartImageView.getTop(),smartImageView.getRight(),smartImageView.getBottom());


            vtusuario.setText(usuario.get(position).toString());


            return viewGroup;
        }
    }
}
