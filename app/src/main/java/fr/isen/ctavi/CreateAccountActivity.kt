package fr.isen.ctavi

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_create_account.*
import java.util.*


class CreateAccountActivity: AppCompatActivity() {


    private var storage: FirebaseStorage? = null
    lateinit var filePath: Uri
    private lateinit var auth: FirebaseAuth
    private val TAG = "CreateAccountActivity"

    companion object {
        val pictureRequestCode = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        pictureButton.setOnClickListener {
            onChangePhoto()
        }

        auth = FirebaseAuth.getInstance()
        val intentLogin = Intent(this, LoginActivity::class.java)
        val intentWallActivity = Intent(this, WallActivity::class.java)
        validateButton.setOnClickListener{
            if(identifiant.text.isNotEmpty()&&password.text.isNotEmpty())
            {
                auth.createUserWithEmailAndPassword(identifiant.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            //updateUI(user)
                            auth.signInWithEmailAndPassword(identifiant.text.toString(), password.text.toString())
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful&&username.text.isNotEmpty()) {
                                        val user = auth.currentUser
                                        // Sign in success, update UI with the signed-in user's information
                                        val profileUpdates = UserProfileChangeRequest.Builder()
                                            .setDisplayName(username.text.toString())

                                            .setPhotoUri(Uri.parse(filePath.toString()))
                                            .build()
                                        user?.updateProfile(profileUpdates)
                                            ?.addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    Log.d(TAG, "User profile updated.")
                                                }
                                            }


                                        startActivity(intentWallActivity)
                                        Toast.makeText(this, "Welcome " + identifiant.text.toString(), Toast.LENGTH_LONG).show()
                                    } else {
                                        // If sign in fails: error message
                                        //updateUI(null)
                                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            //startActivity(intentLogin)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            //updateUI(null)
                        }

                        // ...
                    }



            }
            else
            {
                Toast.makeText(this, "Email or password is empty.",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == pictureRequestCode &&
            resultCode == Activity.RESULT_OK && data != null && data.data != null ) {
            if(data.data != null) { // Gallery
                filePath = data.data!!
                pictureButton.setImageURI(data.data)
                val storageReference = storage?.reference
                val riversRef = storageReference?.child("images/")
                Log.d(TAG, filePath.toString())
                filePath?.let { riversRef?.putFile(it) }


            } else { // Camera
                val bitmap = data.extras?.get("data") as? Bitmap
                bitmap?.let {
                    pictureButton.setImageBitmap(it)
                }
            }
        }
    }
    fun onChangePhoto() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val intentChooser = Intent.createChooser(galleryIntent, "Choose your picture library")
        intentChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        startActivityForResult(intentChooser, pictureRequestCode)
    }

}
