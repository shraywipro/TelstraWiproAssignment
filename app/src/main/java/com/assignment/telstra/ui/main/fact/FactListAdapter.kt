package com.assignment.telstra.ui.main.fact

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.telstra.R.*
import com.assignment.telstra.core.store.online.models.FactData
import com.assignment.telstra.glide.GlideApp
import kotlinx.android.synthetic.main.item_facts.view.*

class FactListAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var factList: List<FactData> = emptyList()

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(pos: Int) {
            val data = factList[pos]
            itemView.tag = data

            if (null != data.title) {
                itemView.txtTitle.text = data.title
            } else {
                itemView.txtTitle.text = context.getString(string.no_data_available)
            }

            if (null != data.decription) {
                itemView.txtDescription.text = data.decription
            } else {
                itemView.txtDescription.text = context.getString(string.no_data_available)
            }
            GlideApp.with(context)
                .load(data.imageUrl) // Uri of the picture
                .centerCrop()
                .placeholder(mipmap.ic_launcher)
                .error(mipmap.ic_launcher)
                .into(itemView.imgView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(layout.item_facts, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bindData(position)
    }

    override fun getItemCount(): Int {
        return factList.size
    }

    fun refreshList(requestList: List<FactData>) {
        this.factList = requestList
        notifyDataSetChanged()
    }


}