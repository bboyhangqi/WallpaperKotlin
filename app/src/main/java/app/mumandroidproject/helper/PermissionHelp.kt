package app.mumandroidproject.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

/**
 * Created by CodingHome on 4/24/18.
 */
class PermissionHelp private constructor() {

    companion object {
        fun checkPermission(activity: Activity) {
            var permissionList = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            var ret = permissionList.filter { ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED }
            if (ret.isNotEmpty()) {
                ActivityCompat.requestPermissions(activity, permissionList, 1)
            }
        }
    }
}