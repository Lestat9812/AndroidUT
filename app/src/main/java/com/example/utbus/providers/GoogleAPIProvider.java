package com.example.utbus.providers;

import android.content.Context;

import com.example.utbus.R;
import com.example.utbus.retrofit.IGoogleAPI;
import com.example.utbus.retrofit.RetrofitClient;
import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Retrofit;

public class GoogleAPIProvider {

    private Context context;
    public GoogleAPIProvider(Context context){
        this.context=context;

    }

    public Call<String> getDirections(LatLng originLatLng, LatLng destLatLng){
        String baseUrl = "https://maps.googleapis.com/";
        String query = "/maps/api/directions/json?mode=driving&transit_routing_preferences=less_driving&"
                + "origin="+originLatLng.latitude+","+originLatLng.longitude+"&"
                + "destination="+destLatLng.latitude+","+destLatLng.longitude+"&"
                + "departure_time=" + (new Date().getTime() + (60*60*1000)) + "&"
                + "traffic_model=best_guess&"
                + "key=" + context.getResources().getString(R.string.google_maps_key);
        return RetrofitClient.getClient(baseUrl).create(IGoogleAPI.class).getDirections(baseUrl+query);
    }
}
