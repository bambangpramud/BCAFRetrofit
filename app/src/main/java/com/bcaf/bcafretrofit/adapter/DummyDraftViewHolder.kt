package com.bcaf.bcafretrofit.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bcaf.bcafretrofit.MainActivity
import com.bcaf.bcafretrofit.fragment.DetailMovie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.itemdraft.view.*
import kotlinx.android.synthetic.main.itemmovies.view.*

class DummyDraftViewHolder(view: View): RecyclerView.ViewHolder(view) {

//    val id = view.txtIdDraft
    val owner = view.txtOwnerDraft
    val imageUrl = view.txtImageUrlDraft
    val text = view.txtTextDraft
    val like = view.txtLikesDraft
    val tags = view.txtTagsDraft



    fun bindData(adapter:DummyDraftAdapter, position:Int){




            owner.setText(adapter.data.get(position)?.owner)
            imageUrl.setText(adapter.data.get(position)?.image)

        text.setText(adapter.data.get(position)?.text)

        like.setText(adapter.data.get(position)?.likes.toString())

        tags.setText(adapter.data.get(position)?.tags.toString())




    }

}