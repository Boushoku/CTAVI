package fr.isen.ctavi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    //Firebase references
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        var email = identifiant.text.toString()
        var password = password.text.toString()
        val intentHomePage = Intent(this, HomeActivity::class.java)
        val intentCreateAccount = Intent(this, CreateAccountActivity::class.java)

        validateButton.setOnClickListener{

            auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    //updateUI(user)
                    startActivity(intentHomePage)
                    Toast.makeText(this, "Welcome " + identifiant.text.toString(), Toast.LENGTH_LONG).show()
                } else {
                    // If sign in fails: error message
                    //updateUI(null)
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
        }
    }

        createAccountButton.setOnClickListener{
            startActivity(intentCreateAccount)

        }
    }

    /*
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        upateUI(currentUser)
    }
    */
}


