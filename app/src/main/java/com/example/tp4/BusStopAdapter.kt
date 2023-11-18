package com.example.tp4
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4.database.entities.Schedule
import java.util.Date
import java.util.Locale


class BusStopAdapter(private var busStops: List<Schedule>, private val onItemClick: (Schedule) -> Unit) : RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {

    // ViewHolder représente chaque élément de la liste
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stopNameTextView: TextView = itemView.findViewById(R.id.stopNameTextView)
        val arrivalTimeTextView: TextView = itemView.findViewById(R.id.arrivalTimeTextView)
    }

    // Crée une nouvelle vue (invocée par le layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bus_stop, parent, false)
        return ViewHolder(view)
    }

    // Remplace le contenu de la vue (invocée par le layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentBusStop = busStops[position]

        //formater date
        holder.stopNameTextView.text = currentBusStop.stop_name
        val date = Date(currentBusStop.arrival_time * 1000L)
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val formattedTime = dateFormat.format(date)
        holder.arrivalTimeTextView.text = formattedTime

        // Set click listener on the item
        holder.itemView.setOnClickListener {
           onItemClick(currentBusStop)
        }
    }

    // Retourne la taille de votre ensemble de données (invocée par le layout manager)
    override fun getItemCount(): Int {
        return busStops.size
    }

    fun updateList(newList: List<Schedule>) {
        busStops = newList
        notifyDataSetChanged()
    }

}
