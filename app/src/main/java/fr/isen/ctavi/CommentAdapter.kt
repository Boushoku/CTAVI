package fr.isen.ctavi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycle_view_comment.view.*

class CommentAdapter(val comment: MutableList<CommentModel>): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_view_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comment.count()
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val person = comment[position]
        holder.bind(person)
    }

    class CommentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(comment: CommentModel) {
            view.postId.text = comment.postId
            view.userId.text = comment.content
            view.content.text = comment.userId

        }

    }
}