package com.example.apiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {

    TextView nameCountry ;
    Bundle bundle;
    PieChart pieChart;
    BarChart barChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        bundle = getIntent().getExtras();


        nameCountry = findViewById(R.id.tv_chart_nameCountry);

        nameCountry.setText(bundle.getString("name_country"));

        pieChart= findViewById(R.id.piechart);
        barChart= findViewById(R.id.barchart);

        com.github.mikephil.charting.charts.PieChart mpPieChart = findViewById(R.id.mpPieChart);



        pieChart.addPieSlice(new PieModel("مبتلایان",Integer.valueOf(bundle.getString("NewConfirmed")), Color.parseColor("#4CAF50")));
        pieChart.addPieSlice(new PieModel("کل مبتلایان",Integer.valueOf(bundle.getString("TotalConfirmed")), Color.parseColor("#ff6f00")));
        pieChart.addPieSlice(new PieModel("فوت شدگان",Integer.valueOf(bundle.getString("NewDeaths")), Color.parseColor("#E6291B")));
        pieChart.addPieSlice(new PieModel("کل فوت شدگان",Integer.valueOf(bundle.getString("TotalDeaths")), Color.parseColor("#4CAF50")));
        pieChart.addPieSlice(new PieModel("بهبود یافتگان",Integer.valueOf(bundle.getString("NewRecovered")), Color.parseColor("#ffffff")));
        pieChart.addPieSlice(new PieModel("کل بهبود یافتگان",Integer.valueOf(bundle.getString("TotalRecovered")), Color.parseColor("#2196F3")));
        pieChart.startAnimation();



        barChart.addBar(new BarModel("A",Integer.valueOf(bundle.getString("NewConfirmed")), Color.parseColor("#4CAF50")));
        barChart.addBar(new BarModel("B",Integer.valueOf(bundle.getString("TotalConfirmed")), Color.parseColor("#ff6f00")));
        barChart.addBar(new BarModel("C",Integer.valueOf(bundle.getString("NewDeaths")), Color.parseColor("#E6291B")));
        barChart.addBar(new BarModel("D",Integer.valueOf(bundle.getString("TotalDeaths")), Color.parseColor("#4CAF50")));
        barChart.addBar(new BarModel("E",Integer.valueOf(bundle.getString("NewRecovered")), Color.parseColor("#ffffff")));
        barChart.addBar(new BarModel("F",Integer.valueOf(bundle.getString("TotalRecovered")), Color.parseColor("#2196F3")));
        barChart.startAnimation();

        ArrayList<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry(Integer.valueOf(bundle.getString("NewConfirmed")),"مبتلایان"));
        visitors.add(new PieEntry(Integer.valueOf(bundle.getString("TotalConfirmed")),"کل مبتلایان"));
        visitors.add(new PieEntry(Integer.valueOf(bundle.getString("NewDeaths")),"فوت شدگان"));
        visitors.add(new PieEntry(Integer.valueOf(bundle.getString("TotalDeaths")),"کل فوت شدگان"));
        visitors.add(new PieEntry(Integer.valueOf(bundle.getString("NewRecovered")),"بهبود یافتگان"));
        visitors.add(new PieEntry(Integer.valueOf(bundle.getString("TotalRecovered")),"کل بهبود یافتگان"));

        PieDataSet pieDataSet =new PieDataSet(visitors,"مبتلایان امروز");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);
        pieDataSet.setSliceSpace(3);




        PieData pieData = new PieData(pieDataSet);

        mpPieChart.setData(pieData);
        mpPieChart.getDescription().setEnabled(false);
        mpPieChart.setCenterText("مبتلایان امروز");
        mpPieChart.setDrawEntryLabels(false);
        mpPieChart.setHoleRadius(25f);
        mpPieChart.setTransparentCircleAlpha(0);
        mpPieChart.animate();






    }
}