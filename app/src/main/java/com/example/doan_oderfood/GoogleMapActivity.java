package com.example.doan_oderfood;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class GoogleMapActivity extends AppCompatActivity {
    private MapView mapView;
    private ImageView btn_back;
    private IMapController mapController;

    Double latCurrentPosition = Double.valueOf(0), longCurrentPosition = Double.valueOf(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        setContentView(R.layout.activity_google_map);

        btn_back = findViewById(R.id.icon_back);
        mapView = findViewById(R.id.mapview);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapController = (MapController) mapView.getController();
        mapController.setZoom(15);
        GeoPoint pointcenter = new GeoPoint(10.806326517273355, 106.62955722129318);
        mapController.setCenter(pointcenter);

        ArrayList<OverlayItem> items = new ArrayList<>();
        OverlayItem home = new OverlayItem("HUFI", "Trường Đại Học Công Nghiệp Thực Phẩm TP.HCM", new GeoPoint(10.806326517273355, 106.62955722129318));
        Drawable m = home.getMarker(0);
        items.add(home);
        items.add(new OverlayItem("Resto","chez bahar",new GeoPoint(10.806326517273355, 106.62955722129318)));

        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(),
                items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });

        mOverlay.setFocusItemsOnTap(true);
        mapView.getOverlays().add(mOverlay);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mapView.onResume();
    }
}