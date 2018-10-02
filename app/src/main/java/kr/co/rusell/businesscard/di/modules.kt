package kr.co.rusell.businesscard.di

import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import kr.co.rusell.api.API
import kr.co.rusell.businesscard.room.UserDatabase
import kr.co.rusell.businesscard.rx.rx.ApplicationSchedulerProvider
import kr.co.rusell.businesscard.rx.rx.SchedulerProvider
import kr.co.rusell.businesscard.user.UserInfo
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val apiModules : Module = module {
	single {
		Retrofit.Builder()
				.client(OkHttpClient.Builder()
						.addNetworkInterceptor(StethoInterceptor())
						.addInterceptor(get("loggingInterceptor"))
						.build())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl("https://naver.com")
				.build()
				.create(API::class.java)
	}


	factory ("loggingInterceptor") {
		HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
				as Interceptor
	}

	single { UserInfo(get()) }
}

val dbModules : Module = module {
	single { Room.databaseBuilder( androidApplication(), UserDatabase::class.java,"user").build()  }
	single { get<UserDatabase>().getUserDao()}
}


val rxModule: Module = module { single { ApplicationSchedulerProvider() as SchedulerProvider } }




val appModules = listOf(apiModules, rxModule, dbModules, vmModules)
