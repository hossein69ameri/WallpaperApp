package academy.nouri.wallpaperapp.ui.splash

import academy.nouri.wallpaperapp.R
import academy.nouri.wallpaperapp.databinding.FragmentSplashBinding
import academy.nouri.wallpaperapp.utils.SPLASH_DELAY
import academy.nouri.wallpaperapp.utils.base.BaseFragment
import academy.nouri.wallpaperapp.utils.loadImage
import academy.nouri.wallpaperapp.utils.network.NetworkRequest
import academy.nouri.wallpaperapp.utils.setStatusBarIconsColor
import academy.nouri.wallpaperapp.utils.showSnackBar
import academy.nouri.wallpaperapp.utils.showSnackBarLong
import academy.nouri.wallpaperapp.viewmodel.SplashViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    //Binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    //Other
    private val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set color for status bar icons
        requireActivity().setStatusBarIconsColor(false)
        //Call api
        viewModel.callRandomApi()
        //Load data
        loadData()
    }

    private fun loadData() {
        binding.apply {
            viewModel.randomData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                        loading.isVisible = true
                    }

                    is NetworkRequest.Success -> {
                        response.data?.let { data ->
                            data.urls?.regular?.let { animateBgImg.loadImage(it) }
                            loading.isVisible = false
                            //Navigate with delay
                            lifecycleScope.launch {
                                delay(SPLASH_DELAY)
                                findNavController().popBackStack(R.id.splashFragment, true)
                                findNavController().navigate(R.id.actionToHome)
                            }
                        }
                    }

                    is NetworkRequest.Error -> {
                        loading.isVisible = false
                        root.showSnackBarLong(response.error!!)
                    }
                }
            }
        }
    }
}