package fr.isen.ctavi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(val post: ArrayList<PostModel>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_wall, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return post.count()
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val person = post[position]
        holder.bind(person)
    }

    class PostViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(post: PostModel) {

        }
    }
}
