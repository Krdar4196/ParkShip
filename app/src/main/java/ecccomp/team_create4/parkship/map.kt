package ecccomp.team_create4.parkship

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private val ARR_MAX: Int = 10

class map : Fragment(), OnMapReadyCallback, LocationListener {


    lateinit var mMap: GoogleMap

    private lateinit var database: DatabaseReference

    lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    var locationCallback: LocationCallback? = null

    lateinit var nowLocation: LatLng
    var nowMarker: Marker? = null

    private var Park_LatLng: ArrayList<LatLng> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().setTitle("Park Map!!!")

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        database = Firebase.database.reference

        Log.d("firebase", "onCreate Through#####")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.value

                val dbdata = database.child("ksj:Dataset").child("gml:Point")

                for (i in 0..ARR_MAX){
                    dbdata.child("$i").child("gml:pos").child("0").get()
                        .addOnSuccessListener {
                            val ltString = it.value as String
                            val Park_String = ltString.split(" ") as ArrayList<String>
                            val latitude = Park_String.get(0).toDouble()
                            val longitude = Park_String.get(1).toDouble()
                            Park_LatLng.add(LatLng(latitude, longitude))
                            if (i == ARR_MAX){
                                MarkerInput()
                            }
                        }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(postListener)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView =  inflater.inflate(R.layout.fragment_map, container, false)
        return fragmentView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setInfoWindowAdapter(ParkInfoWindow(requireContext()))

        //パーミッションの確認
        checkPermission()

        //マーカーの詳細をタップした時の処理
        mMap.setOnInfoWindowClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.container, ParkDetailFragment())
                .commit()
        }
    }


    //パーミッションの状態を確認する
    private fun checkPermission(){
        if(ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
            Log.d("perm1", "状態OK")
            myLocationEnable()
        }else{
            Log.d("perm1", "状態NG")
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
        }
    }


    //requestPeermissionsのコールバック
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.d("perm1", "許可されました")
            myLocationEnable()
        }else{
            Log.d("perm1", "拒否されました")
        }
    }


    //自分の位置情報をオンにする
    private fun myLocationEnable(){
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //許可されていない
            Log.d("perm1", "位置情報が許可されていません")
            return
        }else{
            Log.d("perm1", "位置情報ON")
            mMap.isMyLocationEnabled = true

            val tasklc = fusedLocationProviderClient?.lastLocation

            tasklc.addOnSuccessListener { location ->
                if (location != null){
                    nowLocation = LatLng(location.latitude, location.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nowLocation, 13f))
                }
            }

            locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    nowLocation = LatLng(p0.lastLocation.latitude, p0.lastLocation.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nowLocation, 13f))
                    Log.d("perm1", "Lat : ${nowLocation.latitude}, Long : ${nowLocation.longitude}")
                }
            }
        }
    }


    override fun onLocationChanged(location: Location) {
        Log.d("perm1", "現在地が更新されました")

        nowMarker?.let {
            it.remove()
        }

        location?.let {
            nowLocation = LatLng(it.latitude, it.longitude)
            nowMarker = mMap.addMarker(MarkerOptions().position(nowLocation).title("現在地!!!"))
        }
    }


    fun MarkerInput(){
        for (i in 0..ARR_MAX){
            mMap.addMarker(MarkerOptions().position(Park_LatLng.get(i)).title("park : $i").snippet("Parkdayo!!!"))
        }
    }
}
