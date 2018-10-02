package kr.co.rusell.businesscard.user



data class UserInfoData(
		var isLogin : Boolean = false,
		var authKey : String = "",
		var name : String = "",
		var email : String)
