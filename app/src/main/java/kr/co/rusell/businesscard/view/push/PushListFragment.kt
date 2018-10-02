package kr.co.rusell.businesscard.view.push

import kr.co.rusell.businesscard.R
import kr.co.rusell.businesscard.base.BaseFragment

class PushListFragment :BaseFragment() {

	companion object {
		val TAG = PushListFragment::class.java.simpleName ?: "PushListFragment"
	}

	override fun getLayoutRes(): Int  = R.layout.more_fragment


}