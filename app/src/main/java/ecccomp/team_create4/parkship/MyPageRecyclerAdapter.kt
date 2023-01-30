package ecccomp.team_create4.parkship

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyPageRecyclerAdapter(val list: List<ClipData.Item>) : RecyclerView.Adapter<ViewHolderList>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderList {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_page_report_recycler_list, parent, false)
        return ViewHolderList(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderList, position: Int) {
        val item = list[position]

        holder.park_name.text = item.text
        holder.comment.text = item.htmlText
    }

    override fun getItemCount(): Int = list.size
}

class ViewHolderList (item: View) : RecyclerView.ViewHolder(item) {
    val park_name: TextView = item.findViewById(R.id.park_name)
    val comment: TextView = item.findViewById(R.id.comment)
}