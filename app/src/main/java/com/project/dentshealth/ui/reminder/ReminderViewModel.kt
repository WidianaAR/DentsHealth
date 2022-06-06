package com.project.dentshealth.ui.reminder

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.dentshealth.model.Reminder
import com.project.dentshealth.repository.ReminderRepository
import com.project.dentshealth.utils.plusAssign
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(private val reminderRepository: ReminderRepository, application: Application) : AndroidViewModel(application) {
    val PREFS_LABEL = "DentsHealth"
    val PREFS_NAME = "user"

    val sharedPreferences: SharedPreferences = getApplication<Application>().getSharedPreferences(PREFS_LABEL, Context.MODE_PRIVATE)
    val name: String = sharedPreferences.getString(PREFS_NAME, "user").toString()

    val reminders = reminderRepository.observeReminders(name)
    val time = MutableLiveData<Long>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun create(reminder: Reminder) {
        compositeDisposable += reminderRepository.create(reminder).observeOn(AndroidSchedulers.mainThread()).subscribe({
        }) {
            Timber.e(it)
        }
    }

    fun delete(reminder: Reminder) {
        compositeDisposable += reminderRepository.delete(reminder).observeOn(AndroidSchedulers.mainThread()).subscribe({
        }) {
            Timber.e(it)
        }
    }

    fun update(reminder: Reminder) {
        compositeDisposable += reminderRepository.update(reminder).observeOn(AndroidSchedulers.mainThread()).subscribe({
        }) {
            Timber.e(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}