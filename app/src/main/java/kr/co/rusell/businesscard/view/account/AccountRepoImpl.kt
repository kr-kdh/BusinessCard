package kr.co.rusell.businesscard.view.account

import io.reactivex.Flowable
import kr.co.rusell.api.API
import kr.co.rusell.businesscard.room.UserDao
import kr.co.rusell.businesscard.room.UserData

class AccountRepoImpl(val api : API,val dao: UserDao) : AccountRepo {

	override fun getUserData(): Flowable<UserData> {
		return dao.getUserData()
	}

}