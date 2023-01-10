package com.bcaf.bcafretrofit.adapter


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bcaf.bcafretrofit.MainActivity
import com.bcaf.bcafretrofit.fragment.DetailMovie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.itemmovies.view.*


class OMDBViewHolder(view:View):RecyclerView.ViewHolder(view) {
    val titlex = view.txtTitle
    val yearx = view.txtYear
    val typex = view.txtType
    val image = view.imgPoster
    val btnDetail = view.btnDetail



    fun bindData(adapter:OMDBAdapter, position:Int){

        titlex.setText(adapter.data.get(position)?.title)
        yearx.setText(adapter.data.get(position)?.year)
        typex.setText(adapter.data.get(position)?.type)

        image?.let {
            Glide.with(adapter.parent.context)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .load(adapter.data.get(position)?.poster)
                .into(it)


        }
        btnDetail.setOnClickListener(
            View.OnClickListener {
                it->

                (it.context as MainActivity).loadFragment(DetailMovie.newInstance(titlex.text.toString(),""))

            }
            )


    }

}