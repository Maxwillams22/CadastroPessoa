package com.example.maxwillams.cadastropessoa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

public class AddPerson extends AppCompatActivity {

    private EditText txtFirstName;
    private EditText txtlastName;
    private HttpClient httpClient = HttpClientBuilder.create().build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_person);

        txtFirstName = (EditText) findViewById(R.id.txt_firstname);
        txtlastName = (EditText) findViewById(R.id.txt_lastname);
    }

    public void  Save(View v) throws JSONException, IOException {

        HttpPost client = new HttpPost("http://wmjulio.herokuapp.com/api/v1.0/persons");


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
