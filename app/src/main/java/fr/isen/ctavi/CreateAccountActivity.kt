package fr.isen.ctavi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.identifiant
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.validateButton

class CreateAccountActivity: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        auth = FirebaseAuth.getInstance()
        val intentLogin = Intent(this, LoginActivity::class.java)
        validateButton.setOnClickListener{
            if(identifiant.text.isNotEmpty()&&password.text.isNotEmpty())
            {
                auth.createUserWithEmailAndPassword(identifiant.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            //updateUI(user)
                            startActivity(intentLogin)
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
