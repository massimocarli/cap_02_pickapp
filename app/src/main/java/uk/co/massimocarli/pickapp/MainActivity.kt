package uk.co.massimocarli.pickapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {

  companion object {
    const val CONTACT_REQUEST = 1
    const val PICTURE_REQUEST = 2
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    pictureImageView.setOnClickListener {
      selectPicture()
    }
    contactTextView.setOnClickListener {
      selectContact()
    }
  }

  private fun selectContact() {
    val intent = Intent().apply {
      action = Intent.ACTION_PICK
      type = ContactsContract.Contacts.CONTENT_TYPE
    }
    startActivityForResult(intent, CONTACT_REQUEST)
  }

  private fun selectPicture() {
    val intent = Intent().apply {
      action = Intent.ACTION_PICK
      type = "image/*"
    }
    startActivityForResult(intent, PICTURE_REQUEST)
  }

  override fun onActivityResult(
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == Activity.RESULT_CANCELED) {
      toast("Operation Cancelled!")
      return
    }
    when (requestCode) {
      PICTURE_REQUEST -> {
        data?.data?.let {
          pictureImageView.setImageURI(it)
        }
      }
      CONTACT_REQUEST -> {
        data?.data?.let {
          contactTextView.text = it.asContactName(this@MainActivity)
        }
      }
      else -> {
        throw IllegalStateException("Something wrong!")
      }
    }
  }
}
