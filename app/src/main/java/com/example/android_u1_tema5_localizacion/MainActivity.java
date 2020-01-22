package com.example.android_u1_tema5_localizacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity  extends AppCompatActivity implements OnMapReadyCallback {
  GoogleMap mapa;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    SupportMapFragment mapFragment = (SupportMapFragment)
        getSupportFragmentManager().findFragmentById(R.id.mapa);
    mapFragment.getMapAsync(this);
  }
  @Override
  public void onMapReady(GoogleMap googleMap) {
    mapa = googleMap;
    mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-18.013766, -70.255331), 15));
    if (ContextCompat.checkSelfPermission(this,
        android.Manifest.permission.ACCESS_FINE_LOCATION) ==
        PackageManager.PERMISSION_GRANTED) {
      mapa.setMyLocationEnabled(true);
      mapa.getUiSettings().setZoomControlsEnabled(false);
      mapa.getUiSettings().setCompassEnabled(true);
    } else {
      Button btnMiPos=(Button) findViewById(R.id.btnmiubi);
      btnMiPos.setEnabled(false);
    }
  }
  public void miubicacion(View view) {
    if (mapa.getMyLocation() != null)
      mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(
          new LatLng(mapa.getMyLocation().getLatitude(),
              mapa.getMyLocation().getLongitude()), 17));
  }
}
