package fr.isen.ctavi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_post.*

class CreatePostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val user = FirebaseAuth.getInstance().currentUser
        val db = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        if(content.text.isNotEmpty() && postName.text.isNotEmpty())
        {
            validatePost.setOnClickListener {
                val post = hashMapOf(
                    "userId" to user?.displayName.toString(),
                    "photoUrl" to user?.photoUrl.toString(),
                    "title" to postName.text.toString(),
                    "content" to content.text.toString(),
                    "ctavi" to "0",
                    "likes" to "0"
                )
                db.collection("Posts")
                    .add(post)
                    .addOnSuccessListener { documentReference -> Log.d("addPost", "DocumentSnapshot added with ID: ${documentReference.id}") }
                    .addOnFailureListener{
                        e -> Log.w("addPost", "Error adding document", e)
                    }
                startActivity(Intent(this,WallActivity::class.java))
            }
        }
    }
}
