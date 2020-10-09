package com.saruj.viewvolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String URL = "http://192.168.1.7/android/AgamiLab/data.php" ;
    RecyclerView recyclerView ;
    private ArrayList<Product> mProduct = new ArrayList<>() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        LoadFollowItem() ;

    }

    private void LoadFollowItem() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Server", "response: "+ response) ;

                        try {
                            JSONObject object = new JSONObject(response);
//                            JSONObject object = new JSONObject(Constant.SAVED_JSON);
                            JSONArray sectionArray = object.getJSONArray("data");

                            for(int i=0;i<sectionArray.length();i++){
                                JSONObject mNavigateObject = sectionArray.getJSONObject(i);
                                Product product = new Product(
                                        mNavigateObject.getInt("id"),
                                        mNavigateObject.getString("title"),
                                        mNavigateObject.getString("desc"),
                                        mNavigateObject.getString("image")
                                ) ;

                                mProduct.add(product);
                            }
                            ProductAdapter mAdapter = new ProductAdapter(getApplicationContext(), mProduct);
                            recyclerView.setAdapter(mAdapter);
                            GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(manager);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Server", " json error: "+ e) ;
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Server", "error: "+ error) ;
                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }
}