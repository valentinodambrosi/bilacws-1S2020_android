package br.com.bilac.appbilacws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class cadastrarActivity extends AppCompatActivity {


    Button btCadastrar, btPesquisarID, btAtualizar, btApagar;
    EditText etId, etNome, etTelefone, etEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://192.168.15.20/bilacws/clienteControle.php";



        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btPesquisarID = (Button) findViewById(R.id.btPesquisarID);
        btAtualizar = (Button) findViewById(R.id.btAtualizar);
        btApagar = (Button) findViewById(R.id.btApagar);


        etId = (EditText) findViewById(R.id.etID);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etNome = (EditText) findViewById(R.id.etNome);



////////////////////////////////////////////////  CADASTRAR ///////////////////////////////////////////////////////////////////////////
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject postparams = new JSONObject();

                try{

                    postparams.put("id",  etId.getText());
                    postparams.put("nome" , etNome.getText());
                    postparams.put("telefone", etTelefone.getText());
                    postparams.put("email", etEmail.getText());





                } catch (JSONException e){

                    e.printStackTrace();

                }


                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, postparams, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG ).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "ERRO" , Toast.LENGTH_LONG).show();

                    }
                });


                queue.add(jsonObjReq);



            }
        });
////////////////////////////////////////////////  PESQUISAR POR ID  ///////////////////////////////////////////////////////////////////////////

        btPesquisarID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               String url_id = url + "?id=" + etId.getText();


               JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url_id, null, new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {


                       try{

                           etId.setText(response.get("id").toString());
                           etNome.setText(response.get("nome").toString());
                           etTelefone.setText(response.get("telefone").toString());
                           etEmail.setText(response.get("email").toString());


                       } catch (JSONException e){


                           Toast.makeText(getApplicationContext(), "ID não Localizado" , Toast.LENGTH_LONG).show();


                       }



                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               }


               );



                queue.add(jsonObjReq);








            }
        });


        ////////////////////////////////////////////////  ATUALIZAR ///////////////////////////////////////////////////////////////////////////
        btAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject postparams = new JSONObject();

                try{

                    postparams.put("id",  etId.getText());
                    postparams.put("nome" , etNome.getText());
                    postparams.put("telefone", etTelefone.getText());
                    postparams.put("email", etEmail.getText());





                } catch (JSONException e){

                    e.printStackTrace();

                }


                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT, url, postparams, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG ).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "ERRO" , Toast.LENGTH_LONG).show();

                    }
                });


                queue.add(jsonObjReq);



            }
        });




        ////////////////////////////////////////////////  APAGAR ///////////////////////////////////////////////////////////////////////////
        btApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject postparams = new JSONObject();

                try{

                    postparams.put("id",  etId.getText());
                    postparams.put("nome" , etNome.getText());
                    postparams.put("telefone", etTelefone.getText());
                    postparams.put("email", etEmail.getText());




                } catch (JSONException e){

                    e.printStackTrace();

                }


                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PATCH, url, postparams, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG ).show();

                        //etId.setText("");
                        etNome.setText("");
                        etTelefone.setText("");
                        etEmail.setText("");


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.toString() , Toast.LENGTH_LONG).show();

                        Log.e("ERRRRRR" , error.toString());

                    }
                });


                queue.add(jsonObjReq);



            }
        });











    }
}
