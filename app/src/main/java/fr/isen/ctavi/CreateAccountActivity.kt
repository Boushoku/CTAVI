package fr.isen.ctavi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.identifiant
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.validateButton

class CreateAccountActivity: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val TAG = "CreateAccountActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

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
                                            //.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
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

}
