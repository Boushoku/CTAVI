package fr.isen.ctavi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_wall.*

class WallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall)
        val db = FirebaseFirestore.getInstance()
        Log.d("test",db.toString())
        val posts = db.collection("Posts")
        db.collection("Posts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("test", "${document.id} => ${document.data}")

                }
            }
            .addOnFailureListener { exception ->
                Log.w("test", "Error getting documents.", exception)
            }
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //recyclerView.adapter = PostAdapter(personList)
        /*val postList=ArrayList<PostModel>()
        postList.forEach()
        {
            val postModel = PostModel()
            postModel.userName=
                postModel.postName=
                postModel.profilePicture=
                postModel.content=
                postModel.ctavie=
                postModel.like=
        }*/

    }
}
