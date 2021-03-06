package com.example.tarde.myapplication;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EdicaoActivity extends AppCompatActivity {

    private GastosHelper Helper;
    private Button btnInfo;
    private GastoDAO gastoDAO;
    private TextView editTitulo;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(EdicaoActivity.this,MainActivity.class));
                finishAffinity();
                break;
            default:
                break;
        }
        return true;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_);

        editTitulo = findViewById(R.id.editTitulo);
        editTitulo.setText("EDIT GASTOS");
        btnInfo = findViewById(R.id.btnInfo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("NEED CONTROL");

        Helper = new GastosHelper(this);

        Intent intent = getIntent();
        Gastos gastos = (Gastos) intent.getSerializableExtra(MainActivity.LISTA_GASTOS);
        if (gastos != null) {
            Helper.carregaCampos(gastos);
        }




        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gastos gastos = Helper.getGastos();
                if (gastoDAO.editar(gastos) ==-1){
                    Toast.makeText(EdicaoActivity.this,"Editado com sucesso",Toast.LENGTH_LONG).show();
                } else {
                    finish();
                }
            }
        });

    }


}

