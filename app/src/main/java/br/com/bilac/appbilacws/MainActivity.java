package br.com.bilac.appbilacws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button btCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCadastrar =  (Button) findViewById(R.id.btCadastrar);


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent cadastroIntent = new Intent(getApplicationContext(), cadastrarActivity.class);
                startActivity(cadastroIntent);



            }
        });





    }
}
