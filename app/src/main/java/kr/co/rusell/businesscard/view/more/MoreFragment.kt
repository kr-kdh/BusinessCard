package kr.co.rusell.businesscard.view.more

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.more_fragment.*
import kr.co.rusell.businesscard.R
import kr.co.rusell.businesscard.base.BaseFragment
import kr.co.rusell.businesscard.view.account.AccountActivity

class MoreFragment : BaseFragment() {


	companion object {
		val TAG = MoreFragment::class.java.simpleName ?: "MoreFragment"
	}

	override fun getLayoutRes(): Int = R.layout.more_fragment



	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		btnAccount.setOnClickListener {
			startActivity(Intent(activity,AccountActivity::class.java))
		}
	}
}