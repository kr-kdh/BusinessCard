package kr.co.rusell.businesscard.view.launcher

import io.reactivex.Observable
import kr.co.rusell.businesscard.base.BaseViewModel
import kr.co.rusell.businesscard.ext.with
import kr.co.rusell.businesscard.rx.rx.SchedulerProvider
import java.util.concurrent.TimeUnit

class LauncherViewModel(private val provider : SchedulerProvider) : BaseViewModel() {

	fun startSplashTimer(onSubscribe : ()  -> Unit) {
		addDisposable(Observable.timer(1, TimeUnit.SECONDS).with(provider).subscribe { onSubscribe() })
	}

}