package ameri.hossein.wallpaperapp.ui.categories

import ameri.hossein.wallpaperapp.data.model.home.ResponsePhotos.ResponsePhotosItem
import ameri.hossein.wallpaperapp.databinding.ItemImagesCategoriesBinding
import ameri.hossein.wallpaperapp.utils.loadImage
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class CategoriesAdapter @Inject constructor() : PagingDataAdapter<ResponsePhotosItem,
        CategoriesAdapter.ViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImagesCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.setIsRecyclable(false)
    }

    inner class ViewHolder(private val binding: ItemImagesCategoriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponsePhotosItem) {
            binding.apply {
                //Image
                item.urls?.regular?.let {
                    image.loadImage(it)
                }
                //Click
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item.id!!)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<ResponsePhotosItem>() {
            override fun areItemsTheSame(oldItem: ResponsePhotosItem, newItem: ResponsePhotosItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ResponsePhotosItem, newItem: ResponsePhotosItem): Boolean {
                return oldItem == newItem

            }
        }
    }
}