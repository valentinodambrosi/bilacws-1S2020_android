package br.com.bilac.appbilacws;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class listar_cliente extends AppCompatActivity {

    ListView listview;
    RequestQueue queue;
    ArrayAdapter<Cliente> adapter;
    String URL = "http://192.168.15.20/bilacws/clienteControle.php";
    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cliente);

        listview = (ListView) findViewById(R.id.listview);



        final List<Cliente> list=new ArrayList<>();
        queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    JSONArray array=object.getJSONArray("clientes");


                    for(int i=0;i<array.length();i++) {
                        JSONObject object1=array.getJSONObject(i);
                        String id =object1.getString("id");
                        String nome =object1.getString("nome");
                        String telefone =object1.getString("telefone");
                        String email =object1.getString("email");
                        list.add(new Cliente(id,nome,telefone,email));
                    }


                    adapter = new ArrayAdapter<Cliente>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                    listview.setAdapter(adapter);




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }
        });
        queue.add(request);




        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemSelecionado = adapter.getItem(position).id + "\n" + adapter.getItem(position).nome+ "\n" + adapter.getItem(position).telefone+ "\n" + adapter.getItem(position).email;
                Toast.makeText(getApplicationContext(), itemSelecionado, Toast.LENGTH_LONG).show();




            }
        });




    }
    private class Cliente {
        String nome,email,telefone,id;
        public Cliente(String id, String nome, String telefone ,String email) {
            this.nome=nome;
            this.email=email;
            this.id=id;
            this.telefone=telefone;

        }

        @NonNull
        @Override
        public String toString() {
            return "Id: "+id +"\nNome: "+nome +"\nTelefone: "+ telefone +"\nEmail: "+ email;
        }
    }
}
