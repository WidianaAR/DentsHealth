package com.project.dentshealth.repository

import com.project.dentshealth.model.History
import com.project.dentshealth.model.local.HistoryDAO
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepository @Inject constructor(private val historyDAO: HistoryDAO) {

    fun create(history: History): Completable = Completable.fromAction {
        historyDAO.create(history)
    }.subscribeOn(Schedulers.io())

    fun observeHistories() = historyDAO.observeHistories()

    fun update(history: History): Completable = Completable.fromAction {
        historyDAO.update(history)
    }.subscribeOn(Schedulers.io())

    fun delete(history: History): Completable = Completable.fromAction {
        historyDAO.delete(history)
    }.subscribeOn(Schedulers.io())
}