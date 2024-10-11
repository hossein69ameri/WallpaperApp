package ameri.hossein.wallpaperapp.ui.detail

import academy.nouri.rotateview.RotateView
import ameri.hossein.wallpaperapp.R
import ameri.hossein.wallpaperapp.databinding.FragmentDetailBinding
import ameri.hossein.wallpaperapp.utils.IMAGE_MIME_TYPE
import ameri.hossein.wallpaperapp.utils.JPG
import ameri.hossein.wallpaperapp.utils.base.BaseFragment
import ameri.hossein.wallpaperapp.utils.isVisible
import ameri.hossein.wallpaperapp.utils.network.NetworkRequest
import ameri.hossein.wallpaperapp.utils.setStatusBarIconsColor
import ameri.hossein.wallpaperapp.utils.showSnackBar
import ameri.hossein.wallpaperapp.viewmodel.DetailViewModel
import android.Manifest
import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.innfinity.permissionflow.lib.requestPermissions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    //Binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    //Other
    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()
    private val wallpaperManager by lazy { WallpaperManager.getInstance(requireContext()) }
    private lateinit var imageBitmap: Bitmap
    private lateinit var rotateView: RotateView
    private var isEnabledRotateView = false
    private var downloadId: Long = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set color for status bar icons
        requireActivity().setStatusBarIconsColor(false)
        //Args
        args.let {
            //Call api
            viewModel.callDetailApi(it.id)
        }
        //Load data
        loadData()
        //Register download broadcast receiver
        requireContext().registerReceiver(downloadImageCompleted(), IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    override fun onStop() {
        super.onStop()
        if (this::rotateView.isInitialized)
            rotateView.unRegisterListener()
    }

    private fun loadData() {
        binding.apply {
            viewModel.detailData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                        loading.isVisible(true, container)
                    }

                    is NetworkRequest.Success -> {
                        response.data?.let { data ->
                            //Init rotate view
                            initRotateView(data.urls?.regular!!)
                            activeRotateView()
                            //Set wallpaper
                            setWallpaperImg.setOnClickListener {
                                wallpaperManager.setBitmap(imageBitmap)
                                setWallpaperImg.setImageResource(R.drawable.check)
                            }
                            //Download
                            downloadLay.setOnClickListener {
                                requestPermission()
                                data.urls.full?.let {
                                    downloadImage(it, data.slug!!)
                                }
                            }
                            //Info
                            infoImg.setOnClickListener {
                                val direction = DetailFragmentDirections.actionDetailToInfo(data)
                                findNavController().navigate(direction)
                            }
                        }
                    }

                    is NetworkRequest.Error -> {
                        loading.isVisible(false, container)
                        root.showSnackBar(response.error!!)
                    }
                }
            }
        }
    }

    private fun initRotateView(imageUrl: String) {
        binding.apply {
            lifecycleScope.launch {
                //Create bitmap from image
                val loader = ImageLoader(requireContext())
                val request = ImageRequest.Builder(requireContext())
                    .data(imageUrl)
                    .allowHardware(false)
                    .build()
                val result = (loader.execute(request) as SuccessResult).drawable
                imageBitmap = (result as BitmapDrawable).bitmap
                //Delay
                delay(200)
                //Rotate view
                rotateView = RotateView.getInstance(requireContext())!!
                rotateView.apply {
                    setImageWithBitmap(coverImg, imageBitmap)
                    center()
                }
                //Hide loading
                loading.isVisible(false, container)
            }
        }
    }

    private fun activeRotateView() {
        binding.apply {
            rotateViewImg.apply {
                setOnClickListener {
                    if (rotateView.isDeviceSupported()) {
                        isEnabledRotateView = if (isEnabledRotateView.not()) {
                            rotateView.registerListener()
                            setBackgroundResource(R.drawable.bg_circle_azure_alpha_selected)
                            true
                        } else {
                            rotateView.unRegisterListener()
                            setBackgroundResource(R.drawable.bg_circle_azure_alpha)
                            false
                        }
                    } else {
                        root.showSnackBar(getString(R.string.notSupportRotateView))
                    }
                }
            }
        }
    }

    private fun requestPermission() {
        lifecycleScope.launch {
            requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE).collect {}
        }
    }

    private fun downloadImage(imageFile: String, fileName: String) {
        val dm = requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadUri = Uri.parse(imageFile)
        val request = DownloadManager.Request(downloadUri)
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setTitle(fileName)
            .setMimeType(IMAGE_MIME_TYPE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator + fileName + JPG)
        downloadId = dm.enqueue(request)
        //Show progress bar
        binding.downloadLoading.apply {
            isVisible = true
            isIndeterminate = true
        }
    }

    private fun downloadImageCompleted(): BroadcastReceiver {
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id == downloadId) {
                    binding.apply {
                        downloadLoading.isVisible = false
                        downloadImg.setImageResource(R.drawable.check)
                    }
                }
            }
        }
    }
}