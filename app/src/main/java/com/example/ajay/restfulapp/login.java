package com.example.ajay.restfulapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    EditText e1,e2;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=findViewById(R.id.et_login);
        e2=findViewById(R.id.et_pass);
        b1=findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user=e1.getText().toString();
                final String pass=e2.getText().toString();

                RequestQueue requestQueue= Volley.newRequestQueue(login.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://technological-sash.000webhostapp.com/login.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        startActivity(new Intent(login.this,home.class));
                        Toast.makeText(login.this, "" + response, Toast.LENGTH_SHORT).show();
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(login.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        super.getParams();

                        Map<String,String> params=new HashMap<>();
                        params.put("user_email",user);
                        params.put("user_password",pass);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);


            }
        });
    }
}
