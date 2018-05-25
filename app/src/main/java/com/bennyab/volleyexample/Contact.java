package com.bennyab.volleyexample;

import org.json.JSONObject;

/**
 * Created by bennya on 25/05/2018.
 */

public class Contact {
    public String fname;
    public String Lname;

    public Contact(){

    }

    public Contact(JSONObject data){
        if (data!=null){
            try {
                fname = data.getString("fname");
                Lname = data.getString("lname");
            }catch (Exception ex){

            }
        }
    }

}
