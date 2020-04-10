package com.assignment.telstra.ui.main.fact

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.telstra.R
import com.assignment.telstra.core.store.online.models.FactData
import com.assignment.telstra.glide.GlideApp
import kotlinx.android.synthetic.main.item_facts.view.*

class FactListAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var factList: List<FactData> = emptyList()

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(pos: Int) {
            val data = factList[pos]


                GlideApp.with(context)
                    .load(data.imageUrl) // Uri of the picture
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(itemView.imgView)

                itemView.txtTitle.text =data.title
                itemView.txtDescription.text = data.decription

        }

        var itemClickListener: View.OnClickListener = View.OnClickListener { v ->
            val pos=v.tag.toString().toInt()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_facts, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bindData(position)
    }

    override fun getItemCount(): Int {
        return factList.size
    }

    fun refreshList(requestList: List<FactData>) {
        this.factList =requestList
        notifyDataSetChanged()
    }


}