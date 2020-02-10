package fr.isen.ctavi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_update.*


class UpdateActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val intentWallActivity = Intent(this, WallActivity::class.java)

        updateButton.setOnClickListener {
            if (updateNameEditText.text.isNotEmpty()) {
                //TODO : GET THE CURRENT USER
                //TODO : UPDATE THE PROFILE WITH NEW NAME, EMAIL, PASSWORD etc


                startActivity(intentWallActivity)
            }
        }
    }
}