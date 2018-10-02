package kr.co.rusell.businesscard.view.launcher

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.android.synthetic.main.launcher_activity.*
import kr.co.rusell.businesscard.BuildConfig
import kr.co.rusell.businesscard.MainActivity
import kr.co.rusell.businesscard.R
import kr.co.rusell.businesscard.base.BaseActivity
import kr.co.rusell.businesscard.firebase.KEY_REMOTE_TEST
import org.koin.androidx.viewmodel.ext.android.viewModel

class LauncherActivity : BaseActivity() {

	override fun getLayout(): Int  = R.layout.launcher_activity

	val viewModel : LauncherViewModel by viewModel()

	val mRemoteConfig by lazy { FirebaseRemoteConfig.getInstance() }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		startMain()
	}

	private fun startMain() {
		viewModel.startSplashTimer {
			Intent(this@LauncherActivity, MainActivity::class.java).run { startActivity(this) }
		}
	}




	private fun testFbRemote() {
		mRemoteConfig.setConfigSettings(FirebaseRemoteConfigSettings
				.Builder()
				.setDeveloperModeEnabled(BuildConfig.DEBUG)
				.build())
		mRemoteConfig.setDefaults(R.xml.remote_config_defaults)

		fetch()

	}

	private fun fetch() {
		tvTest.text = mRemoteConfig.getString(KEY_REMOTE_TEST)

		mRemoteConfig.fetch().addOnCompleteListener { test ->
			if(test.isSuccessful) {
				mRemoteConfig.activateFetched()

				val message = mRemoteConfig.getString(KEY_REMOTE_TEST)
				Toast.makeText(this,"fetch successed",Toast.LENGTH_SHORT).show()
				tvTest.text = message

			}
			else {
				Toast.makeText(this,"fetch failed",Toast.LENGTH_SHORT).show()
			}

		}
	}

}