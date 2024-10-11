package ameri.hossein.wallpaperapp.ui.home.adapters

import ameri.hossein.wallpaperapp.data.model.home.ColorToneModel
import ameri.hossein.wallpaperapp.databinding.ItemColorsBinding
import ameri.hossein.wallpaperapp.utils.base.BaseDiffUtils
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class ColorsAdapter @Inject constructor() : RecyclerView.Adapter<ColorsAdapter.ViewHolder>() {

    private var items = emptyList<ColorToneModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemColorsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()

    inner class ViewHolder(private val binding: ItemColorsBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ColorToneModel) {
            binding.apply {
                //Color
                color.setBackgroundColor(item.color)
                //Click
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item.name)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<ColorToneModel>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}