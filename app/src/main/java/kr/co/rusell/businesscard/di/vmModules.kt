package kr.co.rusell.businesscard.di

import kr.co.rusell.businesscard.view.account.AccountRepo
import kr.co.rusell.businesscard.view.account.AccountRepoImpl
import kr.co.rusell.businesscard.view.account.AccountViewModel
import kr.co.rusell.businesscard.view.launcher.LauncherViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val vmModules = module {

	/** Launcher **/
	viewModel { LauncherViewModel(get()) }


	/** Account **/
	viewModel { AccountViewModel(get(),get())}
	factory { AccountRepoImpl(get(),get()) as AccountRepo }
}