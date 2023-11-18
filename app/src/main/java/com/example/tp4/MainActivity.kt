package com.example.tp4

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4.database.BusScheduleApplication
import com.example.tp4.database.entities.Schedule
import com.example.tp4.viewmodels.BusScheduleViewModel
import com.example.tp4.viewmodels.BusScheduleViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var busStopsAdapter: BusStopAdapter
    private lateinit var adapter: BusStopAdapter
    private lateinit var busStops: List<Schedule>

    private val viewModel : BusScheduleViewModel by viewModels(){
        BusScheduleViewModelFactory((application as BusScheduleApplication).database.scheduleDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)


        val busStopAdapter = BusStopAdapter(emptyList()) { schedule->
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("stopName", schedule.stop_name)
            startActivity(intent)
            }
        recyclerView.adapter = busStopAdapter

        viewModel.fullSchedule().observe(this) {
            busStopAdapter.updateList(it)
        }

        /*
        val appDatabase = (application as BusScheduleApplication).database
        val scheduleDao = appDatabase.scheduleDao()

        // Fetch data from the database using DAO methods
        lifecycleScope.launch {
            // Suspend function for fetching data from the database
            busStops = withContext(Dispatchers.IO) {
                scheduleDao.getAll()
            }

            // Initialize the adapter with the fetched data
            adapter = BusStopAdapter(busStops)
            recyclerView.adapter = adapter
        }
        */


    }


}



