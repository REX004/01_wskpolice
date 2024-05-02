package com.example.a01_wskpolice.adapters.session2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a01_wskpolice.data.Department
import com.example.a01_wskpolice.data.Wanted
import com.example.a01_wskpolice.databinding.ItemDepartmentBinding
import com.example.a01_wskpolice.databinding.WantedItemBinding
import com.example.a01_wskpolice.session2.ShowWantedActivity


class DepartmentAdapter(private val context: Context) : ListAdapter<Department, DepartmentAdapter.Holder>(
    Comparator()
)  {

    inner class Holder(private val binding: ItemDepartmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(department: Department) {
            with(binding) {
                firstName.text = department.name
                lastName.text = department.address

                card.setOnClickListener {
                    val intent = Intent(context, ShowWantedActivity::class.java).apply {
                        putExtra("name", department.name)
                        putExtra("address", department.address)
                        putExtra("description", department.description)
                        putExtra("boss", department.boss)
                        putExtra("phone", department.phone)
                        putExtra("email", department.email)
                        putExtra("coords", department.coords)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    class Comparator : DiffUtil.ItemCallback<Department>() {
        override fun areItemsTheSame(oldItem: Department, newItem: Department): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Department, newItem: Department): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemDepartmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        holder.bind(item)


    }
}