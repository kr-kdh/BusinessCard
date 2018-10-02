package kr.co.rusell.businesscard.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

	private val mCompositeDisposable: CompositeDisposable by lazy {
		CompositeDisposable()
	}

	private fun unDisposable()  { if ( mCompositeDisposable.isDisposed.not() ) mCompositeDisposable.clear() }

	fun addDisposable(disposable: Disposable) {
		mCompositeDisposable.add(disposable)
	}

	override fun onCleared() {
		unDisposable()
		super.onCleared()
	}
}