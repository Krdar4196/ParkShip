package ecccomp.team_create4.parkship

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class ParkInfoWindow(private val context: Context) : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker): View? {
        val view: View = (context as Activity).layoutInflater.inflate(R.layout.infowindow_park, null)

        val tvTitle = view.findViewById<View>(R.id.tvTitle) as TextView
        val tvSnippet = view.findViewById<View>(R.id.tvSnippet) as TextView

        tvTitle.text = marker.title
        tvSnippet.text = marker.snippet

        return view
    }

    override fun getInfoWindow(p0: Marker): View? {
        return null
    }

//    override fun getInfoWindow(marker: Marker): View? = setupWindow(marker)
//
//    private fun setupWindow(marker: Marker): View =
//        LayoutInflater.from(context).inflate(R.layout.infowindow_park, null, false).apply {
//            //val station = marker.tag as Station
//
//            //findViewById<ImageView>(R.id.imageView).setImageResource(station.imageResId)
//            findViewById<TextView>(R.id.tvTitle).text = marker.title
//            findViewById<TextView>(R.id.tvSnippet).text = marker.snippet
//        }
}