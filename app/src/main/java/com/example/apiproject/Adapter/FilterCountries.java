package com.example.apiproject.Adapter;

import android.widget.Filter;

import com.example.apiproject.Model.CountryModel;

import java.util.ArrayList;
import java.util.List;

public class FilterCountries extends Filter {

    CountryAdapter countryAdapter ;
    List<CountryModel> data;

    public FilterCountries(CountryAdapter countryAdapter, List<CountryModel> data) {
        this.countryAdapter = countryAdapter;
        this.data = data;
    }



    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

        FilterResults filterResults = new FilterResults();

        if(constraint != null && constraint.length()>0){
            //fix moshkel bozorg koochik
            constraint =  constraint.toString().toUpperCase();
            List<CountryModel> filterData = new ArrayList<>();

            for (int i = 0 ; i< data.size() ; i++){

                if(data.get(i).getCountry().toUpperCase().contains(constraint)){
                    filterData.add(data.get(i));
                }
            }


            filterResults.count = filterData.size();
            filterResults.values = filterData;
        }else {
            filterResults.count = data.size();
            filterResults.values = data;
        }




        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        countryAdapter.data = (List<CountryModel>) results.values;
        countryAdapter.notifyDataSetChanged();

    }
}
