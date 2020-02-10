package fr.isen.ctavi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycle_view_wall.view.*

class PostAdapter(val post: MutableList<PostModel>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
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
            view.username.text=post.userName
            view.namePost.text=post.postName
            view.post.text=post.content
            view.ctavie.text="CTAVIE "+post.ctavie.toString()
            view.like.text="LIKE "+post.like.toString()
            Picasso.get().load(post.profilePicture).resize(200, 200).into(view.profilePicture)
            view.comment.id=post.comment.toInt()
        }
    }
}
