package com.example.maxwillams.cadastropessoa;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpDelete;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.util.EntityUtils;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private HttpClient httpClient = HttpClientBuilder.create().build();
    private CustomAdapter ca;
    private ArrayList<Person> people = new ArrayList<Person>();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            findAll();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }



    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (item.getItemId() == R.id.add_person) {
            Intent intent = new Intent(this, AddPerson.class);

            startActivity(intent);
            return true;
        }

        return false;
    }

    public void findAll() throws IOException, JSONException {
        HttpGet client = new HttpGet("http://wmjulio.herokuapp.com/api/v1.0/persons");

        client.addHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(client);

        String json = EntityUtils.toString(response.getEntity());

        JSONArray obj = new JSONArray(json);

        for (int i = 0; i < obj.length(); i++) {
            JSONObject data = obj.getJSONObject(i);

            people.add(new Person(data.getInt("id"), data.getString("firstName"), data.getString("lastName")));
        }

        ca =  new CustomAdapter(this, 0, people);
        listView.setAdapter(ca);

    }

    public void remove(View view) throws IOException {
        View v = (View)view.getParent();

        TextView txtId = (TextView) v.findViewById(R.id.txt_label_id);
        Integer id = Integer.parseInt(String.valueOf(txtId.getText()));

        HttpDelete client = new HttpDelete("http://wmjulio.herokuapp.com/api/v1.0/persons/" + id);

        client.addHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(client);

        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 200) {
            Log.d("MAIN", "Deleted");
        }

        updateUi(people);
    }

    public void updateUi(ArrayList<Person> itens) {
        ca.clear();

        if (itens != null) {
            for (Object object : itens) {
                ca.insert((Person) object, ca.getCount());
            }
        }
        Toast.makeText(this, "Deletado com sucesso!", Toast.LENGTH_SHORT).show();
        ca.notifyDataSetChanged();

        
    }
}