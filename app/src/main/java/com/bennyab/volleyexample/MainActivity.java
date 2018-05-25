package com.bennyab.volleyexample;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    AlertDialog waitdialog;
    
    public AlertDialog setProgressDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(R.layout.waiting_dialog);
        waitdialog = builder.create();
        waitdialog.show();
        return  waitdialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txtTest);
        setProgressDialog();
        RequestQueue queue = Volley.newRequestQueue(this);
        String contactsurl = getString(R.string.url_get_contacts);
        final GetContactsRequest request = new GetContactsRequest(Request.Method.GET, contactsurl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.v("Logger","data recieved");

                        Gson gson = new Gson();
                        Contact[] result = gson.fromJson(response.toString(),Contact[].class);

//                        ArrayList<Contact> data = GetContactsRequest.parseContacts(response);
                        Log.v("Logger","after parse");

                        Toast.makeText(MainActivity.this,Integer.toString(result.length),Toast.LENGTH_LONG).show();
                        waitdialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e("onErrorResponse",error.getMessage());
                        waitdialog.dismiss();
                    }
                });

        Log.v("Logger","before request");
        queue.add(request);
    }
}
