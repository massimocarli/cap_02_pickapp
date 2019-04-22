package uk.co.massimocarli.pickapp

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.widget.Toast


fun Activity.toast(message: String) =
  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


fun Uri.asContactName(context: Context): String? {
  var contactName: String? = null
  context.getContentResolver().query(this, null, null, null, null).use {
    if (it.moveToFirst()) {
      contactName = it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
    }
  }
  return contactName
}
