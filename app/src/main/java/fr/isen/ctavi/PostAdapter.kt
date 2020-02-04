package fr.isen.ctavi

import android.app.Person
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class PersonAdapter(val post: ArrayList<PostModel>): RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_wall, parent, false)
        return PersonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return post.count()
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = post[position]
        holder.bind(person)
    }

    class PersonViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(person: PostModel) {

        }
    }
}
