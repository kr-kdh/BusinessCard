package kr.co.rusell.businesscard.ext

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment



object RequestActivityQueue {
	val request : MutableList<RequestActivity> = mutableListOf()
	fun findRequestActivity(requestCode: Int): RequestActivity? = request.find { it.requestCode == requestCode }
}

class RequestActivity(val requestCode: Int,
                      val intent: Intent,
                      private val onActivityResult: OnActivityResult? ) {

	fun delegateActivityResult(requestCode: Int,
	                           resultCode: Int,
	                           data: Intent?) = onActivityResult?.invoke(requestCode, resultCode, data)
	fun consume() = RequestActivityQueue.request.remove(this)
}



typealias OnActivityResult = (requestCode: Int, resultCode: Int, data: Intent?) -> Unit


fun Activity.startActivityForResult(
		requestCode: Int,
		intent: Intent,
		onActivityResult: OnActivityResult) {
	RequestActivityQueue.request.add(RequestActivity(requestCode, intent, onActivityResult))
	startActivityForResult(intent, requestCode)
}

fun Fragment.startActivityForResult(
		requestCode: Int,
		intent: Intent,
		onActivityResult: OnActivityResult) {
	RequestActivityQueue.request.add(RequestActivity(requestCode, intent, onActivityResult))
	startActivityForResult(intent, requestCode)
}