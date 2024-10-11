package ameri.hossein.wallpaperapp.ui.splash

import ameri.hossein.wallpaperapp.R
import ameri.hossein.wallpaperapp.databinding.FragmentSplashBinding
import ameri.hossein.wallpaperapp.utils.SPLASH_DELAY
import ameri.hossein.wallpaperapp.utils.base.BaseFragment
import ameri.hossein.wallpaperapp.utils.loadImage
import ameri.hossein.wallpaperapp.utils.network.NetworkRequest
import ameri.hossein.wallpaperapp.utils.setStatusBarIconsColor
import ameri.hossein.wallpaperapp.utils.showSnackBarLong
import ameri.hossein.wallpaperapp.viewmodel.SplashViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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