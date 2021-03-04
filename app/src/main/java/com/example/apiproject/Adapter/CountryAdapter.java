package com.example.apiproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiproject.ChartActivity;
import com.example.apiproject.Interface.ItemClickListener;
import com.example.apiproject.Model.CountryModel;
import com.example.apiproject.R;

import java.text.DecimalFormat;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    Context context;
    List<CountryModel> data;

    public CountryAdapter(Context context, List<CountryModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_country,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        DecimalFormat decimalFormat = new DecimalFormat("###,###");

        String NewCon = data.get(position).getNewConfirmed();
        holder.NewConfirmed.setText(decimalFormat.format(Integer.valueOf(NewCon))+" +");


        String TotalCon = data.get(position).getTotalConfirmed();
        holder.TotalConfirmed.setText(decimalFormat.format(Integer.valueOf(TotalCon))+" +");

        String NewDea = data.get(position).getNewDeaths();
        holder.NewDeaths.setText(decimalFormat.format(Integer.valueOf(NewDea))+" +");

        String TotalDea = data.get(position).getTotalDeaths();
        holder.TotalDeaths.setText(decimalFormat.format(Integer.valueOf(TotalDea))+" +");

        String NewReco = data.get(position).getNewRecovered();
        holder.NewRecovered.setText(decimalFormat.format(Integer.valueOf(NewReco))+" +");

        String TotalReco = data.get(position).getTotalRecovered();
        holder.TotalRecovered.setText(decimalFormat.format(Integer.valueOf(TotalReco))+" +");

        holder.name.setText(data.get(position).getCountry());


        holder.setListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , ChartActivity.class);
                intent.putExtra("NewConfirmed" , data.get(position).getNewConfirmed());
                intent.putExtra("TotalConfirmed" , data.get(position).getTotalConfirmed());
                intent.putExtra("NewDeaths" , data.get(position).getNewDeaths());
                intent.putExtra("TotalDeaths" , data.get(position).getTotalDeaths());
                intent.putExtra("NewRecovered" , data.get(position).getNewRecovered());
                intent.putExtra("TotalRecovered" , data.get(position).getTotalRecovered());
                intent.putExtra("name_country", data.get(position).getCountry());

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name , NewConfirmed, TotalConfirmed, NewDeaths, TotalDeaths, NewRecovered, TotalRecovered;

        ItemClickListener listener;

        public void setListener(ItemClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            NewConfirmed =itemView.findViewById(R.id.item_Country_NewConfirmed);
            TotalConfirmed =itemView.findViewById(R.id.item_Country_TotalConfirmed);
            NewDeaths =itemView.findViewById(R.id.item_Country_NewDeaths);
            TotalDeaths =itemView.findViewById(R.id.item_Country_TotalDeaths);
            NewRecovered =itemView.findViewById(R.id.item_Country_NewRecovered);
            TotalRecovered =itemView.findViewById(R.id.item_Country_TotalRecovered);
            name =itemView.findViewById(R.id.item_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v);
        }
    }
}
