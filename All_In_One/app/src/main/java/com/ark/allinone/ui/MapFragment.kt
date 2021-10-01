package com.ark.allinone.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ark.allinone.Maps.OfflineLatLng
import com.ark.allinone.R
import com.ark.allinone.utils.PermissionsHandle
import com.ark.allinone.viewmodel.Map_ViewModel
import com.ark.allinone.viewmodel.Map_ViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*
import kotlin.concurrent.schedule

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MapFragment : Fragment(), OnMapReadyCallback {
    private val permissions: Array<String>
        get() = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.CAMERA
        )
    private var param1: String? = null
    private var param2: String? = null
    var rootView: View? = null
    private  var mMap: GoogleMap? = null
    lateinit var mapFragment: SupportMapFragment
    lateinit var viewModel: Map_ViewModel
    lateinit var clickMe_Btn: Button
    lateinit var clickMe_Btn1: Button
    lateinit var permissionsHandle: PermissionsHandle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        rootView = inflater.inflate(R.layout.fragment_map, container, false)
//        return rootView
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        permissionsHandle = PermissionsHandle()
        clickMe_Btn = view.findViewById(R.id.clickme)
        clickMe_Btn1 = view.findViewById(R.id.clickme1)
        viewModel = ViewModelProvider(
            requireActivity(),
            Map_ViewModelFactory(OfflineLatLng(requireActivity()))
        ).get(Map_ViewModel::class.java)

        mapFragment.getMapAsync(this)

        clickMe_Btn.setOnClickListener {

        }


        viewModel.latLng.observe(viewLifecycleOwner, {
            mMap?.addMarker(
                MarkerOptions()
                    .position(it)
                    .title("Marker in Karachi")
            )

            mMap?.moveCamera(CameraUpdateFactory.newLatLng(it))
        })

        clickMe_Btn1.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                viewModel.requestforcurrentLocation()

            } else {
                requireActivity().requestPermissions(permissions, 9001)
            }
        }


    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        val sydney = LatLng(-34.0, 151.0)
        mMap?.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )

        mMap?.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

//    @RequiresPermission(allOf = [android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION])

    @SuppressLint("MissingPermission")
    override fun onStart() {
        super.onStart()
        if (!permissionsHandle.hasPermission(requireContext(), *permissions)) {
            Log.d("ARK", "Has PErmission")
            permissionsHandle.requestPermissions(requireActivity(), 9001, *permissions)
        } else {
            viewModel.requestforcurrentLocation()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d("ARK- Request", "onRequestPermissionsResult:")
        if (requestCode == 9001) {
            if (grantResults.isNotEmpty()) {
                for (i in grantResults.indices) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Log.d("ARK", "onRequestPermissionsResult: Granted ${permissions[i]}")
                        Toast.makeText(
                            requireContext(),
                            "Granted ${permissions[i]}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Log.d("ARK", "onRequestPermissionsResult: Not Granted ${permissions[i]}")
                        Toast.makeText(
                            requireContext(),
                            "Not Granted ${permissions[i]}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }
    }

    fun checkP(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }
}