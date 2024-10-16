# WallpaperApp
This Android application, built with Kotlin and powered by the [Unsplash API](https://unsplash.com/), allows users to explore and download stunning photos. It features random photo display, categorized photo lists, and detailed photo information, offering a seamless experience with modern technology integration.

# Screen Shot
<div align="center">
    <img src="https://github.com/user-attachments/assets/27668d88-071f-407b-9405-e19a5bac7282" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="300" height="600" />
    <img src="https://github.com/user-attachments/assets/42f4b999-5739-43ab-a966-f7d29b7fbe7e" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="300" height="600" />
    <img src="https://github.com/user-attachments/assets/e6ca74e8-d76f-4a75-bca4-ab466ffa0c52" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="300" height="600" />
    <img src="https://github.com/user-attachments/assets/5e60edaf-54da-4b47-b11c-178f7002eb46" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="300" height="600" />
</div>

[wallpaper.webm](https://github.com/user-attachments/assets/71af986c-2ea2-419e-a6d7-d7a7147e8ef9)

# Technologies

* MVVM Architecture
* Kotlin Coroutines
* Kotlin Flow
* LiveData
* Single activity pattern
* REST API
* Safe Args
* Kotlin Parcelize
* Pagination
* Shimmer RecyclerView
* Base Activity ,Fragment
* Dependency injection

# Built with
[Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.

[Shimmer RecyclerView](https://github.com/omtodkar/ShimmerRecyclerView) - A custom Shimmer RecyclerView which adopt to list / grid transition automatically and also supports multiple view types while shimmering.

[RotateView](https://github.com/MrNouri/RotateView) - For Rotate Image.

[KenBurnsView](https://github.com/flavioarfaria/KenBurnsView) - Android ImageViews animated by Ken Burns Effect.

[Font Calligraphy](https://github.com/InflationX/ViewPump) - View Inflation you can intercept.

[Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/) - RESTful API and networking client.

[Hilt](https://dagger.dev/hilt/) - Dependency injection.

[ViewBinding](https://developer.android.com/topic/libraries/view-binding) - View binding is a feature that allows you to more easily write code that interacts with views.

[Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - A collections of libraries that help you design robust, testable, and maintainable apps.

[ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel) - UI related data holder, lifecycle aware.

[Navigation component](https://developer.android.com/guide/navigation) - Fragment routing handler.

[Coroutines](https://developer.android.com/kotlin/coroutines) - Concurrency design pattern for asynchronous programming.

[Flow](https://developer.android.com/kotlin/flow) - Stream of value that returns from suspend function.

[Coil](https://github.com/coil-kt/coil) - Image loading.

[DynamicSize](https://github.com/MrNouri/DynamicSizes) - New units of measure for support all of screen devices.

# Architectures

![android-mvvm-architecture](https://github.com/user-attachments/assets/9d9a470c-b022-4b78-94a1-6c60efa6fa24)

* **View**:  The purpose of this layer is to inform the ViewModel about the user’s action. This layer observes the ViewModel and does not contain any kind of application logic.
  
* **ViewModel**: It exposes those data streams which are relevant to the View. Moreover, it serves as a link between the Model and the View.

* **Model**: This layer is responsible for the abstraction of the data sources. Model and ViewModel work together to get and save the data.

# Contact
Have a project? DM me at

hossein.arabameri69@gmail.com

# Acknowledgments
Special thanks to [Mr Mohammad Nouri](https://github.com/MrNouri) for providing the course that helped me.

