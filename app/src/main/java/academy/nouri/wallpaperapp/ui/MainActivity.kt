package academy.nouri.wallpaperapp.ui

import academy.nouri.wallpaperapp.databinding.ActivityMainBinding
import academy.nouri.wallpaperapp.utils.base.BaseActivity
import academy.nouri.wallpaperapp.utils.setStatusBarIconsColor
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    //Binding
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Transparent status bar
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
            //Change color of status bar icons
            setStatusBarIconsColor(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}