package com.example.paginglibrary.extentions

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.os.Handler
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.paginglibrary.R

fun AppCompatActivity.showNetworkDialog() {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(getString(R.string.title_connection))
    builder.setMessage(getString(R.string.message_connection))

    builder.setPositiveButton(getString(R.string.setting)) { _, _ ->
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(this, intent, null)
    }
    builder.setNegativeButton(
            getString(R.string.cancel),
            DialogInterface.OnClickListener { _, _ ->
                finish()
                return@OnClickListener })
        .setCancelable(false)
        .create().show()
}

fun AppCompatActivity.showProgressDialog() {
    val progressDialog = ProgressDialog(this)
    progressDialog.setMessage("Connecting")
    progressDialog.setCancelable(false)
    progressDialog.show()

    Handler().postDelayed({
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }, 1000)
}
fun AppCompatActivity.showExitDialog(message: String) {

    val builder = AlertDialog.Builder(this)
    builder.setMessage(message)
    builder.setPositiveButton(getString(R.string.exit_button)) { _, _ ->
        finish()
    }
    builder.setNegativeButton(
        getString(R.string.cancel),
        DialogInterface.OnClickListener { _, _ -> return@OnClickListener })
        .setCancelable(false)
    val dialog = builder.create()
    dialog.show()
}
