package com.example.apiproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apiproject.R;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class HomeFragment extends Fragment {

    View view;


    public HomeFragment() {
        // Required empty public constructor
    }


    TextView NewConfirmed, TotalConfirmed, NewDeaths, TotalDeaths, NewRecovered, TotalRecovered;
    RequestQueue requestQueue;
    String api_url = "https://api.covid19api.com/summary";

    LinearLayout parent;
    SpinKitView SpinKit;



    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        requestQueue = Volley.newRequestQueue(getContext());

        NewConfirmed = view.findViewById(R.id.tv_FrHome_NewConfirmed);
        TotalConfirmed = view.findViewById(R.id.tv_FrHome_TotalConfirmed);
        NewDeaths = view.findViewById(R.id.tv_FrHome_NewDeaths);
        TotalDeaths = view.findViewById(R.id.tv_FrHome_TotalDeaths);
        NewRecovered = view.findViewById(R.id.tv_FrHome_NewRecovered);
        TotalRecovered = view.findViewById(R.id.tv_FrHome_TotalRecovered);

        parent = view.findViewById(R.id.Linear_FrHome_parent);
        SpinKit = view.findViewById(R.id.spin_kit);
       // Sprite FadingCircle = new FadingCircle();
       // SpinKit.setIndeterminateDrawable(FadingCircle);

        getResponse();





        return view;
    }

    private void getResponse() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, api_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject global = jsonObject.getJSONObject("Global");

                    DecimalFormat decimalFormat = new DecimalFormat("###,###");

                    String NewConfirmed_ = global.getString("NewConfirmed");
                    String NewCon = decimalFormat.format(Integer.valueOf(NewConfirmed_));
                    NewConfirmed.setText(NewCon+" +");

                    String TotalConfirmed_ = global.getString("TotalConfirmed");
                    String TotalCon = decimalFormat.format(Integer.valueOf(TotalConfirmed_));
                    TotalConfirmed.setText(TotalCon+" +");

                    String NewDeaths_ = global.getString("NewDeaths");
                    String NewDea = decimalFormat.format(Integer.valueOf(NewDeaths_));
                    NewDeaths.setText(NewDea+" +");

                    String TotalDeaths_ = global.getString("TotalDeaths");
                    String TotalDea = decimalFormat.format(Integer.valueOf(TotalDeaths_));
                    TotalDeaths.setText(TotalDea+" +");

                    String NewRecovered_ = global.getString("NewRecovered");
                    String NewReco = decimalFormat.format(Integer.valueOf(NewRecovered_));
                    NewRecovered.setText(NewReco+" +");

                    String TotalRecovered_ = global.getString("TotalRecovered");
                    String TotalReco = decimalFormat.format(Integer.valueOf(TotalRecovered_));
                    TotalRecovered.setText(TotalReco+" +");

                    parent.setVisibility(View.VISIBLE);
                    SpinKit.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error" , error.getMessage() + "");

                Toast.makeText(getContext(), "خطا در دريافت اطلاعات", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}