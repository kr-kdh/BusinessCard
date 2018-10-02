package kr.co.rusell.businesscard.room

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Flowable

//Data access object
@Dao
interface UserDao {
	@Query("SELECT * FROM userdata")
	fun getUserData() : Flowable<UserData>
}