package kr.co.rusell.businesscard.ext

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

const val REQCODE_CAMERA_PERMISSION = 1000
const val REQCODE_CALL_PERMISSION = REQCODE_CAMERA_PERMISSION + 1

object RequestPermissionQueue {
	val request: MutableList<RequestPermission> = mutableListOf()
	fun findRequestPermissions(requestCode: Int) : RequestPermission? = request.find { requestCode == it.requestCode }
}

class RequestPermission(val requestCode: Int,
                        val permissionGrantedAction: () -> Unit,
                        val permissionDeniedAction: () -> Unit) {
	fun consume() = RequestPermissionQueue.request.remove(this)
}

fun Activity.callPermission(permissionGrantedAction: ()->Unit,
                   permissionDeniedAction: ()->Unit) {

	if( ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED ) {
		permissionGrantedAction.invoke()
	} else {
		this.requestPermission(
				REQCODE_CALL_PERMISSION,
				listOf(Manifest.permission.CALL_PHONE).toTypedArray(),
				permissionGrantedAction,
				permissionDeniedAction)
	}
}


fun Activity.requestPermission(requestCode: Int,
                               permissions: Array<String>,
                               permissionGrantedAction: () -> Unit,
                               permissionDeniedAction: () -> Unit) {
	RequestPermissionQueue.request.add(RequestPermission(requestCode, permissionGrantedAction, permissionDeniedAction))
	requestPermissions(permissions,requestCode)

}

fun Fragment.requestPermission(requestCode: Int,
                               permissions: Array<String>,
                               permissionGrantedAction: () -> Unit,
                               permissionDeniedAction: () -> Unit) {
	RequestPermissionQueue.request.add(RequestPermission(requestCode, permissionGrantedAction, permissionDeniedAction))
	requestPermissions(permissions,requestCode)

}