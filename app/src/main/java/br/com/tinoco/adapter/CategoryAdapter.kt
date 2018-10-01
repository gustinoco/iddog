package br.com.tinoco.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.tinoco.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import kotlinx.android.synthetic.main.fragment_detail_dog.view.*


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
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: Boolean): Boolean {
                            itemView.ivDog.setImageResource(R.drawable.dogerror)
                            return false
                        }
                        override fun onResourceReady(p0: Drawable?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                            itemView.ivDog.setImageResource(R.drawable.dogerror)
                            return false
                        }
                    })
                    .into(itemView.ivDog)

            itemView.setOnClickListener { clickListener(item) }
        }
    }
}