package fr.isen.ctavi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_wall.*

class WallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall)
        val db = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        var postList = mutableListOf<PostModel>()
        Log.d("test",db.toString())
        val posts = db.collection("Posts")
        posts
            .get()
            .addOnSuccessListener { result ->
                 postList = result.documents.map {
                     Log.d("test", "${it.id} => ${it.data}")
                     Log.d("test", user?.photoUrl.toString())
                     PostModel(
                         it["userId"].toString(),
                         user?.photoUrl.toString(),
                         it["title"].toString(),
                         it["content"].toString(),
                         it["ctavi"].toString(),
                         it["likes"].toString()
                     )
                 }.toMutableList()
                recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = PostAdapter(postList)
            }
            .addOnFailureListener { exception ->
                Log.w("test", "Error getting documents.", exception)
            }
    }
}
