package fr.isen.ctavi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class WallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("ctavi-e8bf5")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(String::class.java)
                Log.d("test database", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("test", "Failed to read value.", error.toException())
            }
        })
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
