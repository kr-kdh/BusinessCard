package kr.co.rusell.businesscard.view.account

import android.os.Bundle
import kr.co.rusell.businesscard.R
import kr.co.rusell.businesscard.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountActivity : BaseActivity() {
	override fun getLayout(): Int = R.layout.activity_account

	val viewModel : AccountViewModel  by viewModel()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)



		viewModel.getUserInfo()
	}

}