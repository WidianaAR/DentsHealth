package com.project.dentshealth.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.project.dentshealth.model.History
import com.project.dentshealth.model.Reminder

@Database(entities = [Reminder::class, History::class], version = 1)
abstract class STCDatabase : RoomDatabase() {

    abstract fun getReminderDAO(): ReminderDAO

    abstract fun getHistoryDAO(): HistoryDAO
}