package unicauca.edu.drogue_plus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import unicauca.edu.drogue_plus.R
import unicauca.edu.drogue_plus.data.viewmodels.HomeViewModel
import unicauca.edu.drogue_plus.data.viewmodels.MedicineViewModel
import unicauca.edu.drogue_plus.databinding.FragmentHomeBinding
import unicauca.edu.drogue_plus.databinding.FragmentLocationBinding


class LocationFragment : Fragment(), OnMapReadyCallback {

    private lateinit var nMap: GoogleMap
    private var _binding: FragmentLocationBinding? = null
    private val binding : FragmentLocationBinding get() = _binding!!
    private val locationViewModel : MedicineViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val mapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.fragment_location_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        println("Va bien")
        observeViewModels()
    }
    override fun onMapReady(map: GoogleMap) {
        nMap=map
        nMap.uiSettings .isScrollGesturesEnabled =true
        nMap.uiSettings .isZoomControlsEnabled=true
        nMap.uiSettings .isZoomGesturesEnabled=true
        nMap.uiSettings .isCompassEnabled =true
        nMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        nMap.isTrafficEnabled=true
        this.locationViewModel.getLocation()
    }

    private fun observeViewModels(){
        this.locationViewModel.location.observe(viewLifecycleOwner, Observer {
            val lating: LatLng = LatLng(it.lat,it.lon)
            nMap.addMarker(
                MarkerOptions().position(lating)
                    .title(it.name))
            nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lating,12.0f))
        })
    }
}