package kr.co.rusell.businesscard.view.account

import android.util.Log
import kr.co.rusell.businesscard.base.BaseViewModel
import kr.co.rusell.businesscard.ext.with
import kr.co.rusell.businesscard.room.UserData
import kr.co.rusell.businesscard.rx.rx.SchedulerProvider

class AccountViewModel(private val repo : AccountRepo, val provider : SchedulerProvider) : BaseViewModel() {


	fun getUserInfo() {
		addDisposable(repo.getUserData().with(provider).subscribe { AAA(it) })

	}

	private fun AAA(data : Any?) {
		Log.d("AAAA", "result = " + data )
	}
}