package kr.co.rusell.businesscard.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.rusell.businesscard.ext.RequestActivityQueue

abstract class BaseFragment : Fragment() {

	abstract fun getLayoutRes(): Int

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
			= inflater.inflate(getLayoutRes(), container, false)

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		RequestActivityQueue.findRequestActivity(requestCode)?.run {
			delegateActivityResult(requestCode, resultCode, data)
			consume()
		}
	}
}