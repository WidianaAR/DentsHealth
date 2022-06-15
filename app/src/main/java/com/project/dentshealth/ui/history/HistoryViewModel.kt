package com.project.dentshealth.ui.history

import androidx.lifecycle.ViewModel
import com.project.dentshealth.model.History
import com.project.dentshealth.repository.HistoryRepository
import com.project.dentshealth.utils.plusAssign
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val historyRepository: HistoryRepository) : ViewModel() {
    val histories = historyRepository.observeHistories()
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