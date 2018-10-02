package kr.co.rusell.businesscard.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserData(
		@PrimaryKey val authKey :String,
		val name : String,
		val email : String
)