package com.bennyab.volleyexample;

import org.json.JSONObject;

/**
 * Created by bennya on 25/05/2018.
 */

public class Contact {
    public String firstName;
    public String lastName;

    public Contact(JSONObject data){
        if (data!=null){
            try {
                firstName = data.getString("fname");
                lastName = data.getString("lname");
            }catch (Exception ex){

            }
        }
    }

}
