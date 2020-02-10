package fr.isen.ctavi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_wall.*
import kotlinx.android.synthetic.main.recycle_view_wall.*


class WallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall)
        val db = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        var postList = mutableListOf<PostModel>()
        val commentAct = Intent(this, CommentActivity::class.java)
        Log.d("test",db.toString())
        val posts = db.collection("Posts")
        posts
            .get()
            .addOnSuccessListener { result ->
                 postList = result.documents.map {
                     Log.d("test", "${it.id} => ${it.data}")
                     Log.d("test", user?.photoUrl.toString())
                     commentAct.putExtra("postId",it.id)
                     PostModel(
                         it["userId"].toString(),
                         user?.photoUrl.toString(),
                         it["title"].toString(),
                         it["content"].toString(),
                         it["ctavi"].toString(),
                         it["likes"].toString(),
                         it.id
                     )
                 }.toMutableList()
                recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = PostAdapter(postList)
                comment.setOnClickListener{
                    startActivity(commentAct)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("test", "Error getting documents.", exception)
            }
    }
}
