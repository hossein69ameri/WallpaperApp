package ameri.hossein.wallpaperapp.ui.home

import ameri.hossein.wallpaperapp.R
import ameri.hossein.wallpaperapp.data.model.home.ResponseCategories
import ameri.hossein.wallpaperapp.data.model.home.ResponsePhotos.ResponsePhotosItem
import ameri.hossein.wallpaperapp.databinding.FragmentHomeBinding
import ameri.hossein.wallpaperapp.ui.home.adapters.CategoriesAdapter
import ameri.hossein.wallpaperapp.ui.home.adapters.ColorsAdapter
import ameri.hossein.wallpaperapp.ui.home.adapters.NewestPhotosAdapter
import ameri.hossein.wallpaperapp.utils.base.BaseFragment
import ameri.hossein.wallpaperapp.utils.network.NetworkRequest
import ameri.hossein.wallpaperapp.utils.setStatusBarIconsColor
import ameri.hossein.wallpaperapp.utils.setupRecyclerview
import ameri.hossein.wallpaperapp.utils.showSnackBar
import ameri.hossein.wallpaperapp.viewmodel.HomeViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    //Binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    @Inject
    lateinit var newestPhotosAdapter: NewestPhotosAdapter

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    @Inject
    lateinit var colorsAdapter: ColorsAdapter

    //Other
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set color for status bar icons
        requireActivity().setStatusBarIconsColor(true)
        //InitViews
        binding.apply {
            searchInpLay.setEndIconOnClickListener {
                val search = searchEdt.text.toString()
                if (search.isNotEmpty()) {
                    val direction = HomeFragmentDirections.actionToSearch(search)
                    findNavController().navigate(direction)
                } else {
                    root.showSnackBar(getString(R.string.searchCanNotBeEmpty))
                }
            }
        }
        //Init color tones
        initColorsRecycler()
        //Load data
        loadNewestPhotoData()
        loadCategoriesData()
    }

    //Newest
    private fun loadNewestPhotoData() {
        binding.apply {
            viewModel.newestPhotosData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                        progressBar.isVisible = true
                    }

                    is NetworkRequest.Success -> {
                        progressBar.isVisible = false
                        response.data?.let { data ->
                            if (data.isNotEmpty()) {
                                initNewestRecycler(data)
                            }
                        }
                    }

                    is NetworkRequest.Error -> {
                        progressBar.isVisible = false
                        root.showSnackBar(response.error!!)
                    }
                }
            }
        }
    }

    private fun initNewestRecycler(list: List<ResponsePhotosItem>) {
        newestPhotosAdapter.setData(list)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.newestList.setupRecyclerview(layoutManager, newestPhotosAdapter)
        //Click
        newestPhotosAdapter.setOnItemClickListener {
            val direction = HomeFragmentDirections.actionToDetail(it)
            findNavController().navigate(direction)
        }
    }

    //Color tone
    private fun initColorsRecycler() {
        colorsAdapter.setData(viewModel.getColorToneList())
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.colorToneList.setupRecyclerview(layoutManager, colorsAdapter)
        //Click
        colorsAdapter.setOnItemClickListener {
            val direction = HomeFragmentDirections.actionToSearch(it)
            findNavController().navigate(direction)
        }
    }

    //Categories
    private fun loadCategoriesData() {
        binding.apply {
            viewModel.categoriesData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                    }

                    is NetworkRequest.Success -> {
                        response.data?.let { data ->
                            if (data.isNotEmpty()) {
                                initCategoriesRecycler(data)
                            }
                        }
                    }

                    is NetworkRequest.Error -> {
                        root.showSnackBar(response.error!!)
                    }
                }
            }
        }
    }

    private fun initCategoriesRecycler(list: List<ResponseCategories.ResponseCategoriesItem>) {
        categoriesAdapter.setData(list)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.categoriesList.setupRecyclerview(layoutManager, categoriesAdapter)
        //Click
        categoriesAdapter.setOnItemClickListener { id, title ->
            val direction = HomeFragmentDirections.actionToCategories(id, title)
            findNavController().navigate(direction)
        }
    }
}