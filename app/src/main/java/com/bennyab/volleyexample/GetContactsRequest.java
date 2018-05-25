package com.bennyab.volleyexample;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by bennya on 25/05/2018.
 */

public class GetContactsRequest extends JsonArrayRequest {

    public GetContactsRequest(int method, String url, JSONArray jsonRequest, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    public static ArrayList<Contact> parseContacts(JSONArray data){
        ArrayList<Contact> result = new ArrayList<>();
        try{

            for (int i=0;i<data.length();i++){
                Contact contact = new Contact(data.getJSONObject(i));
                result.add(contact);
            }
        }catch (Exception ex){

        }
        return result;
    }
}
