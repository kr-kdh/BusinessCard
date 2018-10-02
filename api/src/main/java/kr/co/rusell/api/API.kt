package kr.co.rusell.api


import io.reactivex.Flowable

interface API {

	fun getUserData() : Flowable<Any>
}