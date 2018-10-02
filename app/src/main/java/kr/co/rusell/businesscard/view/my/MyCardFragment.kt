package kr.co.rusell.businesscard.view.my

import kr.co.rusell.businesscard.R
import kr.co.rusell.businesscard.base.BaseFragment

class MyCardFragment : BaseFragment() {


	companion object {
		val TAG = this::class.java.simpleName ?: "MyCardFragment"
	}
	override fun getLayoutRes(): Int = R.layout.more_fragment

}
