package com.oguzhancetin.androidprojecttemplate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oguzhancetin.androidprojecttemplate.databinding.UserCardBinding
import com.oguzhancetin.androidprojecttemplate.model.User

class UserListAdapter(
    private val onClickCard: (User) -> Unit
) :
   ListAdapter<User,UserListAdapter.ViewHolder>(TaskDiffCallBack()) {

    //This check runs on background thread
    class TaskDiffCallBack : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.userName == newItem.userName;
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(
        private val binding: UserCardBinding,
        private val onClickCard: (User) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: User) {
            binding.textView.text = user.userName
            binding.root.setOnClickListener {
                onClickCard.invoke(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UserCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickCard
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }








}
