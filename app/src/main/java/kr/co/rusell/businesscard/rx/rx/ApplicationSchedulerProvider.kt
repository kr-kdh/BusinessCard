package kr.co.rusell.businesscard.rx.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.co.rusell.businesscard.rx.rx.SchedulerProvider

/**
 * Application providers
 */
class ApplicationSchedulerProvider : SchedulerProvider {
    override fun io() = Schedulers.io()

    override fun ui() = AndroidSchedulers.mainThread()

    override fun computation() = Schedulers.computation()
}