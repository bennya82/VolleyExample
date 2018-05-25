package com.bennyab.volleyexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txtTest);

        RequestQueue queue = Volley.newRequestQueue(this);
        String contactsurl = getString(R.string.url_get_contacts);
        final GetContactsRequest request = new GetContactsRequest(Request.Method.GET, contactsurl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.v("onResponse",response.toString());
                        ArrayList<Contact> data = GetContactsRequest.parseContacts(response);
                        Toast.makeText(MainActivity.this,Integer.toString(data.size()),Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse",error.getMessage());
                    }
                });

        queue.add(request);
    }
}
