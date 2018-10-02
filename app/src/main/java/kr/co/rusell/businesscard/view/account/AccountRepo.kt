package kr.co.rusell.businesscard.view.account

import io.reactivex.Flowable
import kr.co.rusell.businesscard.room.UserData

interface AccountRepo {
	fun getUserData() : Flowable<UserData>
}