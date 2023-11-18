package com.example.tp4.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tp4.database.ScheduleDao
import com.example.tp4.database.entities.Schedule


class BusScheduleViewModel(private val scheduleDao: ScheduleDao) : ViewModel() {

    // Méthode pour obtenir tous les horaires
    fun fullSchedule(): LiveData<List<Schedule>> = scheduleDao.getAll()

    // Méthode pour obtenir les horaires filtrés par nom d'arrêt
    fun scheduleForStopName(name: String): LiveData<List<Schedule>> = scheduleDao.getByStopName(name)
}