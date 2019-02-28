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

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.et_usname);
        e2=findViewById(R.id.et_email);
        e3=findViewById(R.id.et_phone);
        e4=findViewById(R.id.et_pass);
        e5=findViewById(R.id.et_conpaa);
        b1=findViewById(R.id.btn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernem = e1.getText().toString();
                final String useremail = e2.getText().toString();
                final String usernumber = e3.getText().toString();
                final String userpassward = e4.getText().toString();
                final String userconfirmpassward = e5.getText().toString();
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);//store multiple request hold
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://technological-sash.000webhostapp.com/register.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        startActivity(new Intent(MainActivity.this,login.class));
                        Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                         super.getParams();

                         Map<String,String> params=new HashMap<>();
                         params.put("user_name",usernem);
                         params.put("user_email",useremail);
                         params.put("user_phone",usernumber);
                         params.put("password",userpassward);
                         params.put("password_confirm",userconfirmpassward);
                         return params;
                    }
                };
                requestQueue.add(stringRequest);

            }
        });
    }
}
