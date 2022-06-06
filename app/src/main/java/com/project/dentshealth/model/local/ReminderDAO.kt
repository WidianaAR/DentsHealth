package com.project.dentshealth.model.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.dentshealth.model.Reminder

@Dao
interface ReminderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(reminder: Reminder)

//    @Query("SELECT * FROM Reminder WHERE id = :reminderId")
//    fun observeReminder(reminderId: Int): LiveData<Reminder>

    @Query("SELECT * FROM Reminder WHERE username = :name")
    fun observeReminders(name:String): LiveData<List<Reminder>>

    @Update
    fun update(reminder: Reminder)

    @Delete
    fun delete(reminder: Reminder)
}