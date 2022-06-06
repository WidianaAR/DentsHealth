package com.project.dentshealth.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Reminder")
@Parcelize
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String?,
    val title: String,
    val description: String?,
    val time: Long,
    val enabled: Boolean = false
) : Parcelable
