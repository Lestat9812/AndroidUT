package com.example.utbus.Activities.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.utbus.R;
import com.example.utbus.includes.MyToolbar;
import com.example.utbus.providers.GoogleAPIProvider;
import com.example.utbus.utils.DecodePoints;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.SquareCap;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRequestActivity extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private SupportMapFragment mMapFragment;

    private double originLat;
    private double originLng;
    private double destLat;
    private double destLng;
    private String origin;
    private String destination;

    private LatLng mOriginLatLng;
    private LatLng mDestLatLng;

    private GoogleAPIProvider mGoogleAPIProvider;

    private List mPolylist;
    private PolylineOptions mPolylineOptions;

    private TextView mTextViewOrigin;
    private TextView mTextViewDest;
    private TextView mTextViewTiempo;
    private TextView mTextViewDistancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_request);
        MyToolbar.show(this, "Tus datos", true);

        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this::onMapReady);

        originLat=getIntent().getDoubleExtra("origin_lat", 0);
        originLng=getIntent().getDoubleExtra("origin_lng", 0);
        destLat=getIntent().getDoubleExtra("dest_lat", 0);
        destLng=getIntent().getDoubleExtra("dest_lng", 0);
        origin=getIntent().getStringExtra("origin");
        destination=getIntent().getStringExtra("destination");

        mOriginLatLng= new LatLng(originLat, originLng);
        mDestLatLng= new LatLng(destLat,destLng);

        mGoogleAPIProvider = new GoogleAPIProvider(DetailRequestActivity.this);

        mTextViewOrigin=findViewById(R.id.textViewOrigen);
        mTextViewDest=findViewById(R.id.textViewDestino);
        mTextViewTiempo=findViewById(R.id.textViewTiempo);
        mTextViewDistancia=findViewById(R.id.textViewDistancia);

        mTextViewOrigin.setText(origin);
        mTextViewDest.setText(destination);


    }

    private void drawRoute(){

        Log.d("route", "drawRoute: Ruta");
        mGoogleAPIProvider.getDirections(mOriginLatLng, mDestLatLng).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    Log.d("JSon", "onResponse: "+ jsonObject);
                    JSONArray jsonArray = jsonObject.getJSONArray("routes");
                    JSONObject route =jsonArray.getJSONObject(0);
                    JSONObject polylines = route.getJSONObject("overview_polyline");
                    String points = polylines.getString("points");
                    Log.d("points", "onResponse: "+points);
                    mPolylist= DecodePoints.decodePoly(points);
                    mPolylineOptions= new PolylineOptions();
                    mPolylineOptions.color(Color.DKGRAY);
                    mPolylineOptions.width(8f);
                    mPolylineOptions.startCap(new SquareCap());
                    mPolylineOptions.jointType(JointType.ROUND);
                    mPolylineOptions.addAll(mPolylist);
                    mMap.addPolyline(mPolylineOptions);

                    JSONArray legs= route.getJSONArray("legs");
                    JSONObject leg = legs.getJSONObject(0);
                    JSONObject distance= leg.getJSONObject("distance");
                    JSONObject duration= leg.getJSONObject("duration");
                    String distanceText=distance.getString("text");
                    String durationText=duration.getString("text");
                    mTextViewDistancia.setText(distanceText);
                    mTextViewTiempo.setText(durationText);


                }catch (Exception e){
                    Log.d("Error", "Error: "+e.getMessage());
                    Log.d("Error", "Error: catch");

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //mMap.setMyLocationEnabled(true);

        mMap.addMarker(new MarkerOptions().position(mOriginLatLng).title("Autobus").icon(BitmapDescriptorFactory.fromResource(R.drawable.icons8_autobus_40)));
        mMap.addMarker(new MarkerOptions().position(mDestLatLng).title("Tu parada").icon(BitmapDescriptorFactory.fromResource(R.drawable.icons8_persona_30)));

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                new CameraPosition.Builder()
                        .target(mDestLatLng)
                        .zoom(14f)
                        .build()
        ));

        drawRoute();




    }
}