package fr.isen.ctavi

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

/*class PostModel {
    var userName:String? = null
    var profilePicture:String?= null
    var postName:String? = null
    var content:String? = null
    var ctavie:Int? = null
    var dislike:Int?= null
*/
    @IgnoreExtraProperties
    data class PostModel(
        var uid: String? = "",
        var author: String? = "",
        var title: String? = "",
        var body: String? = "",
        var starCount: Int = 0,
        var stars: MutableMap<String, Boolean> = HashMap()
    ) {

        // [START post_to_map]
        @Exclude
        fun toMap(): Map<String, Any?> {
            return mapOf(
                "uid" to uid,
                "author" to author,
                "title" to title,
                "body" to body,
                "starCount" to starCount,
                "stars" to stars
            )
        }
        // [END post_to_map]
    }
//}