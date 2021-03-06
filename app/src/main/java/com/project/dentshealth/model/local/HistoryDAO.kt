package com.project.dentshealth.model.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.dentshealth.model.History

@Dao
interface HistoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(history: History)

    @Query("SELECT * FROM History ORDER BY createdAt DESC")
    fun observeHistories(): LiveData<List<History>>

    @Update
    fun update(history: History)

    @Delete
    fun delete(history: History)
}