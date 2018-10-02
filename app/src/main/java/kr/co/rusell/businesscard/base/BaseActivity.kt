package kr.co.rusell.businesscard.base

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.rusell.businesscard.R
import kr.co.rusell.businesscard.ext.RequestActivityQueue
import kr.co.rusell.businesscard.ext.RequestPermissionQueue

abstract class BaseActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
		setContentView(getLayout())
	}

	abstract fun getLayout() : Int


	override fun startActivity(intent: Intent) {
		super.startActivity(intent)
		overridePendingStartTransition()
	}

	override fun startActivityForResult(intent: Intent, requestCode: Int) {
		super.startActivityForResult(intent, requestCode)
		overridePendingStartTransition()
	}

	override fun finish() {
		super.finish()
		overridePendingFinishTransition()
	}


	private fun overridePendingStartTransition() {
		overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left)
	}

	private fun overridePendingFinishTransition() {
		overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
	}


	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		RequestActivityQueue.findRequestActivity(requestCode)?.run {
			delegateActivityResult(requestCode, resultCode, data)
			consume()
		}
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		RequestPermissionQueue.findRequestPermissions(requestCode)?.run {
			if( grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				permissionGrantedAction.invoke()
			} else {
				permissionDeniedAction.invoke()
			}
			consume()
		}
	}

}