package fr.isen.ctavi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_wall.*

class CommentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        val com = intent.getStringExtra("postId")
        val db = FirebaseFirestore.getInstance()
        var comList = mutableListOf<PostModel>()
        val comment = db.collection("Comments").whereEqualTo("postId",com).get()
            .addOnSuccessListener { result ->
                    comList = result.documents.map {
                        Log.d("test", "${it.id} => ${it.data}")
                        CommentModel(
                            it.postId,
                            it.content,
                            it.userId
                        )
                    }.toMutableList()
                recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = PostAdapter(comList)
                }
            .addOnFailureListener { exception ->
                Log.w("test", "Error getting documents: ", exception)
            }
    }
}
