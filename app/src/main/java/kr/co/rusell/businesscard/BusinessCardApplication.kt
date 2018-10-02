package kr.co.rusell.businesscard

import androidx.multidex.MultiDexApplication
import kr.co.rusell.businesscard.di.appModules
import org.koin.android.ext.android.startKoin


class BusinessCardApplication : MultiDexApplication () {

	override fun onCreate() {
		super.onCreate()

		startKoin(this, appModules)
	}

}