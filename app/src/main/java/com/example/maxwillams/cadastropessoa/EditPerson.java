package com.example.maxwillams.cadastropessoa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.client.methods.HttpPut;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

public class EditPerson extends AppCompatActivity {

    private EditText txtFirstName;
    private EditText txtlastName;
    private String id;
    private HttpClient httpClient = HttpClientBuilder.create().build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_person);

        txtFirstName = (EditText) findViewById(R.id.txt_firstname_edit);
        txtlastName = (EditText) findViewById(R.id.txt_lastname_edit);

        Intent intent = getIntent();

        //Recuperei a string da outra activity

        id = intent.getStringExtra("id");
        String nome = intent.getStringExtra("nome");
        String sobrenome = intent.getStringExtra("sobrenome");

        txtFirstName.setText(nome);
        txtlastName.setText(sobrenome);

    }

    public void  editar(View v) throws JSONException, IOException {

        HttpPut client = new HttpPut("http://wmjulio.herokuapp.com/api/v1.0/persons/" + id);

        JSONObject obj = new JSONObject();

        obj.put("firstName",txtFirstName.getText());
        obj.put("lastName",txtlastName.getText());

        client.addHeader("Content-Type", "application/json");
        client.addHeader("Accept", "application/json");

        StringEntity strObj = new StringEntity(obj.toString());

        client.setEntity(strObj);

        HttpResponse response = httpClient.execute(client);

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

        Toast.makeText(this, "Registro Salvo", Toast.LENGTH_SHORT).show();
    }
}
