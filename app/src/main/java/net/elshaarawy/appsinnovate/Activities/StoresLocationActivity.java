package net.elshaarawy.appsinnovate.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import net.elshaarawy.appsinnovate.Managers.GooglePlayServicesManager;
import net.elshaarawy.appsinnovate.R;
import net.elshaarawy.appsinnovate.ShopdCoordinates;

import java.util.HashMap;
import java.util.List;

public class StoresLocationActivity extends AppCompatActivity implements OnMapReadyCallback
        , GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GooglePlayServicesManager playServicesManager;
    private GoogleMap map;
    private ShopdCoordinates shopdCoordinates;
    private BitmapDescriptor etisalatMarker;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        playServicesManager = new GooglePlayServicesManager(this);
        if (!playServicesManager.isPlayServicesAvailable()) {
            setContentView(R.layout.empty);
        } else {
            setContentView(R.layout.activity_stores_location);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            SupportMapFragment mapFragment =
                    (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.content_stores_location);
            mapFragment.getMapAsync(this);

            shopdCoordinates = new ShopdCoordinates();
            etisalatMarker = BitmapDescriptorFactory.fromResource(R.drawable.etisalat_marker);

            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!googleApiClient.isConnected())
            googleApiClient.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (googleApiClient.isConnected())
            googleApiClient.disconnect();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        moveToEgypt(map);
        loadEtisalatShops(map);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        String[] permissionArray = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        int permissionCode = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCode != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissionArray, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            loadMyLocation(getLastLocation());
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Susended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    loadMyLocation(getLastLocation());
        }
    }

    public void moveToEgypt(GoogleMap map) {
        LatLng latLng = new LatLng(30.424201, 30.6117923);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 8);
        map.animateCamera(update);
    }

    public void loadEtisalatShops(GoogleMap map) {
        for (HashMap.Entry<String, List<Pair<Double, Double>>> entry : shopdCoordinates.coordinatesMap.entrySet()) {
            for (Pair<Double, Double> pair : entry.getValue()) {
                map.addMarker(new MarkerOptions().position(new LatLng(pair.first, pair.second))
                        .title(entry.getKey())
                        .icon(etisalatMarker));
            }
        }
    }

    private Location getLastLocation() {
        try {
            map.setMyLocationEnabled(true);
            Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            return location;
        } catch (SecurityException e) {
            return null;
        }
    }

    public void loadMyLocation(Location location) {
        if (location != null) {
            map.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())));
        }
    }

}
