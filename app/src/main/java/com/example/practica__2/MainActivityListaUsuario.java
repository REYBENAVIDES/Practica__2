package com.example.practica__2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.practica__2.WebService.Asynchtask;
import com.example.practica__2.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivityListaUsuario extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lista_usuario);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://dummyjson.com/users",
                datos, MainActivityListaUsuario.this, MainActivityListaUsuario.this);
        ws.execute("GET");    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtusuarios = (TextView)findViewById(R.id.txtvUsuarios);
        String lstusuarios="";
        JSONObject users = new JSONObject(result);
        JSONArray JSONlista = users.getJSONArray("users");
        for(int i=0; i< JSONlista.length();i++){
            JSONObject user= JSONlista.getJSONObject(i);
            lstusuarios = lstusuarios + "\n" +
                    user.getString("firstName").toString() + ", " +
                    user.getString("age").toString() + ", " +
                    user.getString("email").toString();
        }
        txtusuarios.setText(lstusuarios);

    }
}