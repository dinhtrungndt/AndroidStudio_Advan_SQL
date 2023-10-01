package com.example.asm1_nguyendinhtrung_pk02294.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Interpolator;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.asm1_nguyendinhtrung_pk02294.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class Maps_FPT_Activity extends FragmentActivity implements OnMapReadyCallback {

    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_fpt);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getLocation();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng hcm = new LatLng(10.8539175,106.6263469 );
        mMap.addMarker(new MarkerOptions().position(hcm).title("FPT Polytechnic - CS3"))
                .setIcon(bitmapDescriptor(getApplicationContext(),R.drawable.ic_baseline_school_24));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hcm));

        LatLng dl = new LatLng(12.7873477, 107.6794429);
        mMap.addMarker(new MarkerOptions().position(dl).title("Đăk Lăk"))
                .setIcon(bitmapDescriptor(getApplicationContext(),R.drawable.ic_baseline_self_improvement_24));

        LatLng hn = new LatLng(20.9740874, 105.3724793);
        mMap.addMarker(new MarkerOptions().position(hn).title("Hà Nội"))
                .setIcon(bitmapDescriptor(getApplicationContext(),R.drawable.ic_baseline_system_security_update_good_24));


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15);
                marker.showInfoWindow();
                mMap.animateCamera(cameraUpdate, 500, null);
                return true;
            }
        });
    }

    private BitmapDescriptor bitmapDescriptor (Context context, int vID){
        Drawable vD = ContextCompat.getDrawable(context,vID);
        vD.setBounds(0,0,vD.getIntrinsicWidth(),
                vD.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vD.getIntrinsicWidth(),
                vD.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vD.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Yêu cầu người dùng cấp quyền truy cập locction khi chưa được phép
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 7979);
        } else {
            //Thực hiện việc lấy location sau khi đã có quyền
            mFusedLocationClient.getLastLocation().addOnSuccessListener(
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (ActivityCompat.checkSelfPermission(Maps_FPT_Activity.this,
                                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                    && ActivityCompat.checkSelfPermission(Maps_FPT_Activity.this,
                                    android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            mMap.setMyLocationEnabled(true);
                        }
                    });
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 7979) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "Từ chối", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

