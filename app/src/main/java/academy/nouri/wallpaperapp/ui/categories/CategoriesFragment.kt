package academy.nouri.wallpaperapp.ui.categories

import academy.nouri.wallpaperapp.R
import academy.nouri.wallpaperapp.databinding.FragmentCategoriesBinding
import academy.nouri.wallpaperapp.utils.LoadMoreAdapter
import academy.nouri.wallpaperapp.utils.base.BaseFragment
import academy.nouri.wallpaperapp.utils.setStatusBarIconsColor
import academy.nouri.wallpaperapp.viewmodel.CategoriesViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {
    //Binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentCategoriesBinding
        get() = FragmentCategoriesBinding::inflate

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    //Other
    private val viewModel by viewModels<CategoriesViewModel>()
    private val args by navArgs<CategoriesFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Args
        args.let {
            //Call api
            viewModel.updateCategoryId(it.id)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set color for status bar icons
        requireActivity().setStatusBarIconsColor(true)
        //InitViews
        binding.apply {
            //Back
            backImg.setOnClickListener { findNavController().popBackStack() }
            //Title
            args.let {
                categoriesTitle.text = "${it.title} ${getString(R.string.photos)}"
            }
        }
        //Load data
        loadData()
        //Init functions
        initRecyclerView()
        loadDataStates()
    }

    private fun loadData() {
        viewModel.categoriesPhotos.observe(viewLifecycleOwner) {
            categoriesAdapter.submitData(lifecycle, it)
        }
    }

    private fun initRecyclerView() {
        binding.categoriesList.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = categoriesAdapter.withLoadStateFooter(LoadMoreAdapter { categoriesAdapter.retry() })
        }
        //Click
        categoriesAdapter.setOnItemClickListener {
            val direction = CategoriesFragmentDirections.actionToDetail(it)
            findNavController().navigate(direction)
        }
    }

    private fun loadDataStates() {
        categoriesAdapter.addLoadStateListener { state ->
            binding.apply {
                loading.isVisible = state.source.refresh is LoadState.Loading
                categoriesList.isVisible = state.source.refresh is LoadState.NotLoading
            }
        }
    }
}