package academy.nouri.wallpaperapp.data.model.splash


import com.google.gson.annotations.SerializedName

data class ResponseRandom(
    @SerializedName("alt_description")
    val altDescription: String?, // a bunch of cauliflower on a white surface
    @SerializedName("blur_hash")
    val blurHash: String?, // L6MQkHo#*0?axuWBf6j[.9RiRPWB
    @SerializedName("breadcrumbs")
    val breadcrumbs: List<Any?>?,
    @SerializedName("color")
    val color: String?, // #c0c0a6
    @SerializedName("created_at")
    val createdAt: String?, // 2023-10-14T16:08:12Z
    @SerializedName("current_user_collections")
    val currentUserCollections: List<Any?>?,
    @SerializedName("description")
    val description: String?, // flat lay of white cauliflower florets arranged in a grid on a background painted in a similar colour
    @SerializedName("downloads")
    val downloads: Int?, // 1961
    @SerializedName("exif")
    val exif: Exif?,
    @SerializedName("height")
    val height: Int?, // 4320
    @SerializedName("id")
    val id: String?, // L8dApO4UXgo
    @SerializedName("liked_by_user")
    val likedByUser: Boolean?, // false
    @SerializedName("likes")
    val likes: Int?, // 31
    @SerializedName("links")
    val links: Links?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("promoted_at")
    val promotedAt: String?, // 2023-10-19T08:24:02Z
    @SerializedName("public_domain")
    val publicDomain: Boolean?, // false
    @SerializedName("slug")
    val slug: String?, // a-bunch-of-cauliflower-on-a-white-surface-L8dApO4UXgo
    @SerializedName("sponsorship")
    val sponsorship: Any?, // null
    @SerializedName("tags")
    val tags: List<Tag?>?,
    @SerializedName("tags_preview")
    val tagsPreview: List<TagsPreview?>?,
    @SerializedName("topic_submissions")
    val topicSubmissions: TopicSubmissions?,
    @SerializedName("topics")
    val topics: List<Any?>?,
    @SerializedName("updated_at")
    val updatedAt: String?, // 2023-10-26T22:38:55Z
    @SerializedName("urls")
    val urls: Urls?,
    @SerializedName("user")
    val user: User?,
    @SerializedName("views")
    val views: Int?, // 218093
    @SerializedName("width")
    val width: Int? // 3240
) {
    data class Exif(
        @SerializedName("aperture")
        val aperture: String?, // 4.5
        @SerializedName("exposure_time")
        val exposureTime: String?, // 1/125
        @SerializedName("focal_length")
        val focalLength: String?, // 13.8
        @SerializedName("iso")
        val iso: Int?, // 100
        @SerializedName("make")
        val make: String?, // Panasonic
        @SerializedName("model")
        val model: String?, // DMC-FZ100
        @SerializedName("name")
        val name: String? // Panasonic, DMC-FZ100
    )

    data class Links(
        @SerializedName("download")
        val download: String?, // https://unsplash.com/photos/L8dApO4UXgo/download?ixid=M3w1MTk4Njh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2OTg0MjQ5ODl8
        @SerializedName("download_location")
        val downloadLocation: String?, // https://api.unsplash.com/photos/L8dApO4UXgo/download?ixid=M3w1MTk4Njh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2OTg0MjQ5ODl8
        @SerializedName("html")
        val html: String?, // https://unsplash.com/photos/a-bunch-of-cauliflower-on-a-white-surface-L8dApO4UXgo
        @SerializedName("self")
        val self: String? // https://api.unsplash.com/photos/a-bunch-of-cauliflower-on-a-white-surface-L8dApO4UXgo
    )

    data class Location(
        @SerializedName("city")
        val city: String?, // Lelystad
        @SerializedName("country")
        val country: String?, // Netherlands
        @SerializedName("name")
        val name: String?, // Lelystad, Netherlands
        @SerializedName("position")
        val position: Position?
    ) {
        data class Position(
            @SerializedName("latitude")
            val latitude: Double?, // 52.518537
            @SerializedName("longitude")
            val longitude: Double? // 5.471422
        )
    }

    data class Meta(
        @SerializedName("index")
        val index: Boolean? // true
    )

    data class Tag(
        @SerializedName("source")
        val source: Source?,
        @SerializedName("title")
        val title: String?, // netherlands
        @SerializedName("type")
        val type: String? // search
    ) {
        data class Source(
            @SerializedName("ancestry")
            val ancestry: Ancestry?,
            @SerializedName("cover_photo")
            val coverPhoto: CoverPhoto?,
            @SerializedName("description")
            val description: String?, // Stunningly delicious street food, magnificent banquets, quiet family dinners: each is beautiful in it's own right. Unsplash captures that beauty, and lets you choose from a curated selection of the finest food images on the web (and always free).
            @SerializedName("meta_description")
            val metaDescription: String?, // Choose from hundreds of free food pictures. Download HD food photos for free on Unsplash.
            @SerializedName("meta_title")
            val metaTitle: String?, // 20+ Best Free Food Pictures on Unsplash
            @SerializedName("subtitle")
            val subtitle: String?, // Download free food images
            @SerializedName("title")
            val title: String? // Food images & pictures
        ) {
            data class Ancestry(
                @SerializedName("category")
                val category: Category?,
                @SerializedName("subcategory")
                val subcategory: Subcategory?,
                @SerializedName("type")
                val type: Type?
            ) {
                data class Category(
                    @SerializedName("pretty_slug")
                    val prettySlug: String?, // Food
                    @SerializedName("slug")
                    val slug: String? // food
                )

                data class Subcategory(
                    @SerializedName("pretty_slug")
                    val prettySlug: String?, // Flower
                    @SerializedName("slug")
                    val slug: String? // flower
                )

                data class Type(
                    @SerializedName("pretty_slug")
                    val prettySlug: String?, // Images
                    @SerializedName("slug")
                    val slug: String? // images
                )
            }

            data class CoverPhoto(
                @SerializedName("alt_description")
                val altDescription: String?, // variety of sliced fruits
                @SerializedName("blur_hash")
                val blurHash: String?, // LmPshlDN9FX8xbWBafWBb_o~ozni
                @SerializedName("breadcrumbs")
                val breadcrumbs: List<Any?>?,
                @SerializedName("color")
                val color: String?, // #f3f3f3
                @SerializedName("created_at")
                val createdAt: String?, // 2017-03-29T20:14:13Z
                @SerializedName("current_user_collections")
                val currentUserCollections: List<Any?>?,
                @SerializedName("description")
                val description: String?, // ‘Tis the season of rhubarb. And strawberry. And blood orange. Praise be. Amen.
                @SerializedName("height")
                val height: Int?, // 3171
                @SerializedName("id")
                val id: String?, // 08bOYnH_r_E
                @SerializedName("liked_by_user")
                val likedByUser: Boolean?, // false
                @SerializedName("likes")
                val likes: Int?, // 7144
                @SerializedName("links")
                val links: Links?,
                @SerializedName("plus")
                val plus: Boolean?, // false
                @SerializedName("premium")
                val premium: Boolean?, // false
                @SerializedName("promoted_at")
                val promotedAt: String?, // 2017-03-30T07:13:59Z
                @SerializedName("slug")
                val slug: String?, // variety-of-sliced-fruits-08bOYnH_r_E
                @SerializedName("sponsorship")
                val sponsorship: Any?, // null
                @SerializedName("topic_submissions")
                val topicSubmissions: TopicSubmissions?,
                @SerializedName("updated_at")
                val updatedAt: String?, // 2023-10-18T04:01:17Z
                @SerializedName("urls")
                val urls: Urls?,
                @SerializedName("user")
                val user: User?,
                @SerializedName("width")
                val width: Int? // 3997
            ) {
                data class Links(
                    @SerializedName("download")
                    val download: String?, // https://unsplash.com/photos/08bOYnH_r_E/download
                    @SerializedName("download_location")
                    val downloadLocation: String?, // https://api.unsplash.com/photos/08bOYnH_r_E/download
                    @SerializedName("html")
                    val html: String?, // https://unsplash.com/photos/08bOYnH_r_E
                    @SerializedName("self")
                    val self: String? // https://api.unsplash.com/photos/08bOYnH_r_E
                )

                data class TopicSubmissions(
                    @SerializedName("health")
                    val health: Health?
                ) {
                    data class Health(
                        @SerializedName("approved_on")
                        val approvedOn: String?, // 2020-04-06T14:20:25Z
                        @SerializedName("status")
                        val status: String? // approved
                    )
                }

                data class Urls(
                    @SerializedName("full")
                    val full: String?, // https://images.unsplash.com/photo-1490818387583-1baba5e638af?ixlib=rb-4.0.3&q=85&fm=jpg&crop=entropy&cs=srgb
                    @SerializedName("raw")
                    val raw: String?, // https://images.unsplash.com/photo-1490818387583-1baba5e638af?ixlib=rb-4.0.3
                    @SerializedName("regular")
                    val regular: String?, // https://images.unsplash.com/photo-1490818387583-1baba5e638af?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max
                    @SerializedName("small")
                    val small: String?, // https://images.unsplash.com/photo-1490818387583-1baba5e638af?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max
                    @SerializedName("small_s3")
                    val smallS3: String?, // https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1490818387583-1baba5e638af
                    @SerializedName("thumb")
                    val thumb: String? // https://images.unsplash.com/photo-1490818387583-1baba5e638af?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max
                )

                data class User(
                    @SerializedName("accepted_tos")
                    val acceptedTos: Boolean?, // true
                    @SerializedName("bio")
                    val bio: String?, // Real Food fanatic. Bike rider. People lover. Running around with a camera.
                    @SerializedName("first_name")
                    val firstName: String?, // Brooke
                    @SerializedName("for_hire")
                    val forHire: Boolean?, // true
                    @SerializedName("id")
                    val id: String?, // pVJLqUK0Dh4
                    @SerializedName("instagram_username")
                    val instagramUsername: String?, // brookelark
                    @SerializedName("last_name")
                    val lastName: String?, // Lark
                    @SerializedName("links")
                    val links: Links?,
                    @SerializedName("location")
                    val location: String?, // SLC, UT
                    @SerializedName("name")
                    val name: String?, // Brooke Lark
                    @SerializedName("portfolio_url")
                    val portfolioUrl: String?, // http://www.brookelark.com
                    @SerializedName("profile_image")
                    val profileImage: ProfileImage?,
                    @SerializedName("social")
                    val social: Social?,
                    @SerializedName("total_collections")
                    val totalCollections: Int?, // 0
                    @SerializedName("total_likes")
                    val totalLikes: Int?, // 3
                    @SerializedName("total_photos")
                    val totalPhotos: Int?, // 149
                    @SerializedName("twitter_username")
                    val twitterUsername: String?, // LISUNCHASER
                    @SerializedName("updated_at")
                    val updatedAt: String?, // 2023-10-11T01:06:45Z
                    @SerializedName("username")
                    val username: String? // brookelark
                ) {
                    data class Links(
                        @SerializedName("followers")
                        val followers: String?, // https://api.unsplash.com/users/brookelark/followers
                        @SerializedName("following")
                        val following: String?, // https://api.unsplash.com/users/brookelark/following
                        @SerializedName("html")
                        val html: String?, // https://unsplash.com/@brookelark
                        @SerializedName("likes")
                        val likes: String?, // https://api.unsplash.com/users/brookelark/likes
                        @SerializedName("photos")
                        val photos: String?, // https://api.unsplash.com/users/brookelark/photos
                        @SerializedName("portfolio")
                        val portfolio: String?, // https://api.unsplash.com/users/brookelark/portfolio
                        @SerializedName("self")
                        val self: String? // https://api.unsplash.com/users/brookelark
                    )

                    data class ProfileImage(
                        @SerializedName("large")
                        val large: String?, // https://images.unsplash.com/profile-1496175100457-27a8e68786eb?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128
                        @SerializedName("medium")
                        val medium: String?, // https://images.unsplash.com/profile-1496175100457-27a8e68786eb?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64
                        @SerializedName("small")
                        val small: String? // https://images.unsplash.com/profile-1496175100457-27a8e68786eb?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32
                    )

                    data class Social(
                        @SerializedName("instagram_username")
                        val instagramUsername: String?, // brookelark
                        @SerializedName("paypal_email")
                        val paypalEmail: Any?, // null
                        @SerializedName("portfolio_url")
                        val portfolioUrl: String?, // http://www.brookelark.com
                        @SerializedName("twitter_username")
                        val twitterUsername: String? // LISUNCHASER
                    )
                }
            }
        }
    }

    data class TagsPreview(
        @SerializedName("title")
        val title: String?, // netherlands
        @SerializedName("type")
        val type: String? // search
    )

    class TopicSubmissions

    data class Urls(
        @SerializedName("full")
        val full: String?, // https://images.unsplash.com/photo-1697298987002-f7b39ea574cd?crop=entropy&cs=srgb&fm=jpg&ixid=M3w1MTk4Njh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2OTg0MjQ5ODl8&ixlib=rb-4.0.3&q=85
        @SerializedName("raw")
        val raw: String?, // https://images.unsplash.com/photo-1697298987002-f7b39ea574cd?ixid=M3w1MTk4Njh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2OTg0MjQ5ODl8&ixlib=rb-4.0.3
        @SerializedName("regular")
        val regular: String?, // https://images.unsplash.com/photo-1697298987002-f7b39ea574cd?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1MTk4Njh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2OTg0MjQ5ODl8&ixlib=rb-4.0.3&q=80&w=1080
        @SerializedName("small")
        val small: String?, // https://images.unsplash.com/photo-1697298987002-f7b39ea574cd?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1MTk4Njh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2OTg0MjQ5ODl8&ixlib=rb-4.0.3&q=80&w=400
        @SerializedName("small_s3")
        val smallS3: String?, // https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1697298987002-f7b39ea574cd
        @SerializedName("thumb")
        val thumb: String? // https://images.unsplash.com/photo-1697298987002-f7b39ea574cd?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1MTk4Njh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2OTg0MjQ5ODl8&ixlib=rb-4.0.3&q=80&w=200
    )

    data class User(
        @SerializedName("accepted_tos")
        val acceptedTos: Boolean?, // true
        @SerializedName("bio")
        val bio: String?, // Hi, I'm Zæmôn/foodism360, the founder of the plant-powered stock image boutique rebootanika. 
        @SerializedName("first_name")
        val firstName: String?, // rebootanika
        @SerializedName("for_hire")
        val forHire: Boolean?, // false
        @SerializedName("id")
        val id: String?, // kVsHbbKSThA
        @SerializedName("instagram_username")
        val instagramUsername: String?, // rebootanika
        @SerializedName("last_name")
        val lastName: Any?, // null
        @SerializedName("links")
        val links: Links?,
        @SerializedName("location")
        val location: String?, // The Netherlands
        @SerializedName("name")
        val name: String?, // rebootanika
        @SerializedName("portfolio_url")
        val portfolioUrl: String?, // https://www.rebootanika-visual.boutique/
        @SerializedName("profile_image")
        val profileImage: ProfileImage?,
        @SerializedName("social")
        val social: Social?,
        @SerializedName("total_collections")
        val totalCollections: Int?, // 0
        @SerializedName("total_likes")
        val totalLikes: Int?, // 368
        @SerializedName("total_photos")
        val totalPhotos: Int?, // 110
        @SerializedName("twitter_username")
        val twitterUsername: Any?, // null
        @SerializedName("updated_at")
        val updatedAt: String?, // 2023-10-27T10:42:16Z
        @SerializedName("username")
        val username: String? // foodism360
    ) {
        data class Links(
            @SerializedName("followers")
            val followers: String?, // https://api.unsplash.com/users/foodism360/followers
            @SerializedName("following")
            val following: String?, // https://api.unsplash.com/users/foodism360/following
            @SerializedName("html")
            val html: String?, // https://unsplash.com/@foodism360
            @SerializedName("likes")
            val likes: String?, // https://api.unsplash.com/users/foodism360/likes
            @SerializedName("photos")
            val photos: String?, // https://api.unsplash.com/users/foodism360/photos
            @SerializedName("portfolio")
            val portfolio: String?, // https://api.unsplash.com/users/foodism360/portfolio
            @SerializedName("self")
            val self: String? // https://api.unsplash.com/users/foodism360
        )

        data class ProfileImage(
            @SerializedName("large")
            val large: String?, // https://images.unsplash.com/profile-1642421456570-25a7d9593ce1image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128
            @SerializedName("medium")
            val medium: String?, // https://images.unsplash.com/profile-1642421456570-25a7d9593ce1image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64
            @SerializedName("small")
            val small: String? // https://images.unsplash.com/profile-1642421456570-25a7d9593ce1image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32
        )

        data class Social(
            @SerializedName("instagram_username")
            val instagramUsername: String?, // rebootanika
            @SerializedName("paypal_email")
            val paypalEmail: Any?, // null
            @SerializedName("portfolio_url")
            val portfolioUrl: String?, // https://www.rebootanika-visual.boutique/
            @SerializedName("twitter_username")
            val twitterUsername: Any? // null
        )
    }
}