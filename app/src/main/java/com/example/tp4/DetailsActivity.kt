package com.example.tp4

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4.database.BusScheduleApplication
import com.example.tp4.viewmodels.BusScheduleViewModel
import com.example.tp4.viewmodels.BusScheduleViewModelFactory

class DetailsActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var busStopAdapter: BusStopAdapter
    private val viewModel : BusScheduleViewModel by viewModels() {
        BusScheduleViewModelFactory(
            (application as
                    BusScheduleApplication).database.scheduleDao()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        // Extract data from the intent (you may need to adjust this based on how you pass data)
        val stopName = intent.getStringExtra("stopName")

        recyclerView = findViewById(R.id.recyclerViewDetails)

        recyclerView.layoutManager = LinearLayoutManager(this)

        busStopAdapter= BusStopAdapter(emptyList()) { schedule ->
            // Handle item click, e.g., open DetailsActivity
            println()
        }
        recyclerView.adapter = busStopAdapter
        // Background work to fetch data from the database
        viewModel.scheduleForStopName(stopName!!).observe(this) {
            busStopAdapter.updateList(it)
        }

    }


}