package unicauca.edu.drogue_plus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import unicauca.edu.drogue_plus.R
import unicauca.edu.drogue_plus.databinding.FragmentForgotBinding

class LocationFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentForgotBinding?=null
    private val binding : FragmentForgotBinding get() = _binding!!
    private lateinit var nMap:GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgotBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val mapFragment:SupportMapFragment = childFragmentManager.findFragmentById(R.id.fragment_location_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapReady(map: GoogleMap) {
        nMap=map
        nMap.uiSettings .isScrollGesturesEnabled =true
        nMap.uiSettings .isZoomControlsEnabled=true
        nMap.uiSettings .isZoomGesturesEnabled=true
        nMap.uiSettings .isCompassEnabled =true
        nMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        nMap.isTrafficEnabled=true

        val lating:LatLng = LatLng(2.4454181795496903, -76.61976473210028)

        nMap.addMarker(MarkerOptions().position(lating)
            .title("DroguePlus"))
        nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lating,12.0f))
    }


}