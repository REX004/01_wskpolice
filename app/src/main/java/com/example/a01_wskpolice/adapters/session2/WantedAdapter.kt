package com.example.a01_wskpolice.adapters.session2

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a01_wskpolice.data.Department
import com.example.a01_wskpolice.data.Wanted
import com.example.a01_wskpolice.databinding.WantedItemBinding


class WantedAdapter(private val context: Context) : ListAdapter<Wanted, WantedAdapter.Holder> (
    Comparator()
)  {
    private var isCheckboxVisible = false

    fun showCheckBoxes(isVisible: Boolean) {
        isCheckboxVisible = isVisible
        notifyDataSetChanged()
    }

    fun addItems(items: List<Wanted>) {
        submitList(currentList + items)
    }
    fun clearItems() {
        submitList(emptyList())
    }

    fun addItem(item: Wanted) {
        val updatedList = currentList.toMutableList()
        updatedList.add(item)
        submitList(updatedList)
    }

    inner class Holder(private val binding: WantedItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(wanted: Wanted) {
            with(binding) {
                firstName.text = wanted.first_name
                lastName.text = wanted.last_name

                Glide.with(context)
                    .load(wanted.photo)
                    .into(imagePerson)

                card.setOnClickListener {
                    val intent = Intent(context, ShowWantedActivity::class.java).apply {
                        putExtra("first_name", wanted.first_name)
                        putExtra("last_name", wanted.last_name)
                        putExtra("description", wanted.description)
                        putExtra("last_location", wanted.last_location)
                        putExtra("status", wanted.status)
                        putExtra("nicknames", wanted.nicknames)
                        putExtra("photo", wanted.photo)
                    }
                    context.startActivity(intent)
                }
            }
        }

        fun bindCheckbox(isVisible: Boolean) {
            binding.choseCB.visibility = if (isVisible) View.VISIBLE else View.GONE
            binding.imagePerson.visibility = if (isVisible) View.GONE else View.VISIBLE
        }
    }

    class Comparator : DiffUtil.ItemCallback<Wanted>() {
        override fun areItemsTheSame(oldItem: Wanted, newItem: Wanted): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Wanted, newItem: Wanted): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = WantedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        holder.apply {
            bind(item)
            bindCheckbox(isCheckboxVisible)
        }
    }
    override fun onViewRecycled(holder: Holder) {
        super.onViewRecycled(holder)
        holder.bindCheckbox(false)
    }
}