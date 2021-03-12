package com.example.apiproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apiproject.Adapter.CountryAdapter;
import com.example.apiproject.Model.CountryModel;
import com.example.apiproject.R;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CountryFragment extends Fragment {


    View view;
    RecyclerView recyclerView_Country;
    SpinKitView SKV;
    EditText ET_Search;
    String api_url = "https://api.covid19api.com/summary";
    LinearLayout parent;


    List<CountryModel> listCountries =  new ArrayList<>();
    CountryAdapter adapter;
    RequestQueue requestQueue;

    public CountryFragment() {
        // Required empty public constructor
    }

    public static CountryFragment getInstance(){
        return new CountryFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_country, container, false);

        requestQueue = Volley.newRequestQueue(getContext());


        recyclerView_Country =view.findViewById(R.id.rv_country);
        recyclerView_Country.setHasFixedSize(true);
        recyclerView_Country.setLayoutManager(new GridLayoutManager(getContext() , 2 ));
        //scroll smoothly
        recyclerView_Country.setNestedScrollingEnabled(false);

        SKV = view.findViewById(R.id.spin_kit_country);

        ET_Search =view.findViewById(R.id.et_search_country);
        parent = view.findViewById(R.id.parent_country);


        ET_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getResponse();
       /* Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
*/

        return view;

    }

    private void getResponse() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, api_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // access b klid ba countries
                    JSONArray countries = jsonObject.getJSONArray("Countries");

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();

                    for (int i=0 ; i<countries.length() ; i++){

                        CountryModel countries_ = gson.fromJson(String.valueOf(countries.getJSONObject(i)), CountryModel.class);

                        listCountries.add(countries_);

                    }


                    adapter = new CountryAdapter(getContext() , listCountries);
                    recyclerView_Country.setAdapter(adapter);

                    SKV.setVisibility(View.GONE);
                    parent.setVisibility(View.VISIBLE);



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.getMessage()+ "");

            }
        });


        requestQueue.add(stringRequest);



    }
}