package br.com.tinoco.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.tinoco.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_category.view.*


class CategoryAdapter(openSourceListItems: MutableList<String>, private val context: Context, val clickListener: (String) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    private val categoryListItems: MutableList<String> = openSourceListItems


    override fun getItemCount() = categoryListItems.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val note = categoryListItems[position]
        holder.bindView(note)
    }

    internal fun addOpenSourcesToList(sources: List<String>) {
        this.categoryListItems.addAll(sources)
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(item: String) {
            setIsRecyclable(false)
            Glide.with(context)
                    .load(item)
                    .thumbnail(0.2f)
                    .into(itemView.ivDog)
            itemView.setOnClickListener { clickListener(item) }
        }
    }
}