package com.bcaf.bcafretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bcaf.bcafretrofit.R
import com.bcaf.bcafretrofit.model.PostDummyData
import com.bcaf.bcafretrofit.model.SearchItem

class DummyDraftAdapter (val data: ArrayList<PostDummyData?>): RecyclerView.Adapter<DummyDraftViewHolder>() {
    lateinit var parent: ViewGroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyDraftViewHolder {
        this.parent = parent

        return DummyDraftViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemdraft,parent,false))
    }

    override fun onBindViewHolder(holder: DummyDraftViewHolder, position: Int) {
        holder.bindData(this@DummyDraftAdapter,position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}