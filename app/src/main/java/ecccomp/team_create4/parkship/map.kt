package ecccomp.team_create4.parkship

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.collections.ArrayList


class map : Fragment(), OnMapReadyCallback {

    lateinit var mMap: GoogleMap

    private lateinit var database: DatabaseReference

    //private lateinit var Park_LatLng: ArrayList<java.util.HashMap<String, LatLng>>
    private lateinit var Park_ID: ArrayList<java.util.HashMap<String, String>>

    private var Park_LatLng: ArrayList<LatLng> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().setTitle("Park Map!!!")

        database = Firebase.database.reference

        Log.d("firebase", "onCreate Through#####")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.value

                val dbdata = database.child("ksj:Dataset").child("gml:Point")

//                for (i in 0..99){
//                    dbdata.child("$i").child("gml:pos").child("0").get()
//                        .addOnSuccessListener { Park_LatLng.add(it.value as LatLng) }
//                    //Log.d("firebase", "${Park_LatLng.get(i)}")
//                }

//                val dbid = database.child("ksj:Dataset").child("gml:Point").child("0").
//                child("attr").child("gml:id").get()

//                val dbLatLng = database.child("ksj:Dataset").child("gml:Point").child("0").
//                child("gml:pos").child("0").get()

                //val dbLatLng_all = database.child("ksj:Dataset").child("gml:Point").get()

//                dbid.addOnSuccessListener {
//                    Log.d("firebase", "Got value ${it.value}")
//                }.addOnFailureListener{
//                    Log.d("firebase", "Error getting data", it)
//                }
//
//                dbLatLng.addOnSuccessListener {
//                    Log.d("firebase", "Got value ${it.value!!::class.simpleName}")
//                }.addOnFailureListener{
//                    Log.d("firebase", "Error getting data", it)
//                }
//
//                dbLatLng_all.addOnSuccessListener {
//                    //Park_LatLng = it.value as ArrayList<java.util.HashMap<String, LatLng>>
//
//                    //Log.d("firebase", "Got value ${ParkDate}")
//
////                    Log.d("firebase", "${Park_LatLng.get(0)}")
////                    Log.d("firebase", "${Park_LatLng.get(0)::class.simpleName}")
////                    Log.d("firebase", "${Park_LatLng.get(0).get("gml:pos")}")
//                    //Log.d("firebase", "${ParkDate.get(0).get("attr")}")
////                    Log.d("firebase", "${Park_LatLng.size}")
//
//                    //型確認
//                    Log.d("firebase", "Got value ${it.value!!::class.simpleName}")
//                }.addOnFailureListener{
//                    Log.d("firebase", "Error getting data", it)
//                }

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
        //map = fragmentView.findViewById(R.id.mapView)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        //val customInfoWindow = ParkInfoWindow(requireContext())
        //mMap.setInfoWindowAdapter(context?.let { ParkInfoWindow(it) })
        val rcontext = requireContext()
        mMap!!.setInfoWindowAdapter(ParkInfoWindow(rcontext))

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("シドニー").snippet("Test!!!"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.setOnInfoWindowClickListener {

        }

    }

//    inner class CustomMapFragment : OnMapReadyCallback{
//        override fun onMapReady(googleMap: GoogleMap) {
//            mMap = googleMap
//
//            // Add a marker in Sydney and move the camera
//            val sydney = LatLng(-34.0, 151.0)
//            mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//
//            mMap.setOnInfoWindowClickListener {
//
//            }
//        }
//    }
}
