package kr.co.rusell.businesscard.ext

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import kr.co.rusell.businesscard.rx.rx.SchedulerProvider


/**
 * Use SchedulerProvider configuration for Observable
 */
fun Completable.with(schedulerProvider: SchedulerProvider): Completable =
		this.observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

/**
 * Use SchedulerProvider configuration for Single
 */
fun <T> Single<T>.with(schedulerProvider: SchedulerProvider): Single<T> =
		this.observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

/**
 * Use SchedulerProvider configuration for Observable
 */
fun <T> Observable<T>.with(schedulerProvider: SchedulerProvider): Observable<T> =
		this.observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

fun <T> Flowable<T>.with(schedulerProvider: SchedulerProvider): Flowable<T> =
		this.observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())