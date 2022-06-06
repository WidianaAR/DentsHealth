package com.project.dentshealth.ui.history

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.project.dentshealth.model.History
import com.project.dentshealth.repository.HistoryRepository
import com.project.dentshealth.utils.plusAssign
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val historyRepository: HistoryRepository, application: Application) : AndroidViewModel(application) {
    val PREFS_LABEL = "DentsHealth"
    val PREFS_NAME = "user"

    val sharedPreferences: SharedPreferences = getApplication<Application>().getSharedPreferences(PREFS_LABEL, Context.MODE_PRIVATE)
    val name: String = sharedPreferences.getString(PREFS_NAME, "user").toString()

    val histories = historyRepository.observeHistories(name)
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun create(history: History) {
        compositeDisposable += historyRepository.create(history).observeOn(AndroidSchedulers.mainThread()).subscribe({
        }) {
            Timber.e(it)
        }
    }

    fun update(history: History) {
        compositeDisposable += historyRepository.update(history).observeOn(AndroidSchedulers.mainThread()).subscribe({
        }) {
            Timber.e(it)
        }
    }

    fun delete(history: History) {
        compositeDisposable += historyRepository.delete(history).observeOn(AndroidSchedulers.mainThread()).subscribe({
        }) {
            Timber.e(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}