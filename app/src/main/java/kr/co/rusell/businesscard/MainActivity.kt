package kr.co.rusell.businesscard


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.rusell.businesscard.base.BaseFragment
import kr.co.rusell.businesscard.view.my.MyCardFragment
import kr.co.rusell.businesscard.view.push.PushListFragment
import kr.co.rusell.businesscard.view.more.MoreFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{

	companion object {
		const val MY_CARD = 0
		const val PUSH_LIST = 1
		const val MORE = 2
	}

	var currentFragment : BaseFragment? = null

	private fun getBindMyCardFragment() : MyCardFragment? =
			supportFragmentManager.findFragmentByTag(MyCardFragment.TAG) as? MyCardFragment

	private fun getBindPushListFragment() : PushListFragment? =
			supportFragmentManager.findFragmentByTag(PushListFragment.TAG) as? PushListFragment

	private fun getBindMoreFragment() : MoreFragment? =
			supportFragmentManager.findFragmentByTag(MoreFragment.TAG) as? MoreFragment


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		initView()
	}

	private fun initView() {
		mainNavigation?.setOnNavigationItemSelectedListener(this)

		getCurrentFragment().run {
			if(isNotEmpty()) moveTabFragment(this) else moveTabFragment(MyCardFragment.TAG)
		}

	}


	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		val tag = when(item.itemId) {
			R.id.tab_push -> PushListFragment.TAG
			R.id.tab_more -> MoreFragment.TAG
			else  -> MyCardFragment.TAG
		}

		bindFragment(tag)
		return true
	}

	private fun bindFragment(tag : String, bundle : Bundle? = null) {
		setCurrentFragment(tag)

		currentFragment?.let {
			it.arguments = bundle
			supportFragmentManager.beginTransaction().apply {
				replace(R.id.flContainer,it,tag)
				commitAllowingStateLoss()
			}
		}
	}

	private fun setCurrentFragment(tag : String) {
		currentFragment = when(tag) {
			PushListFragment.TAG -> PushListFragment()
			MoreFragment.TAG -> MoreFragment()
			else -> MyCardFragment()

		}
	}

	private fun moveTabFragment(fragmentTag : String) {
		val pos = when(fragmentTag) {
			MyCardFragment.TAG -> MY_CARD
			PushListFragment.TAG -> PUSH_LIST
			MoreFragment.TAG -> MORE
			else ->  -1
		}

		if(pos != -1) mainNavigation.selectedItemId = pos
	}


	private fun getCurrentFragment() : String = when {
		getBindMyCardFragment() != null -> MyCardFragment.TAG
		getBindPushListFragment() != null -> PushListFragment.TAG
		getBindMoreFragment() != null -> MoreFragment.TAG
		else -> ""
	}




}
