package uz.anorgroup.mapapplication

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.mapapplication.databinding.ActivityMapsBinding
import uz.anorgroup.mapapplication.viewmodel.MapViewModelImpl

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val listPoints = ArrayList<LatLng>()
    private val viewmodel by viewModels<MapViewModelImpl>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = true
        val destanation = LatLng(41.26465, 69.21627)
        map.addMarker(MarkerOptions().position(destanation).title("Amir Temur"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(destanation, 15f))

        map.setOnMapLongClickListener {
            if (listPoints.size == 2) {
                listPoints.clear()
                map.clear()
            }
            listPoints.add(it)
            val markerOptions = MarkerOptions()
            markerOptions.position(it)
            if (listPoints.size == 1) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            } else {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            }
            map.addMarker(markerOptions)
            if (listPoints.size == 2) {
                viewmodel.getMap("41.334505,69.274003", "41.279401,69.217261", "AIzaSyBZiaoR6LhBci4bSDYJynj54hVLYZgm6pA")
            }
        }
        viewmodel.successFlow.onEach { latLangs ->
            val polylineOptions = PolylineOptions()
            polylineOptions.addAll(latLangs)
            polylineOptions.width(15f)
            polylineOptions.color(Color.BLUE)
            polylineOptions.geodesic(true)
            map.addPolyline(polylineOptions)
        }.launchIn(lifecycleScope)
    }


}
