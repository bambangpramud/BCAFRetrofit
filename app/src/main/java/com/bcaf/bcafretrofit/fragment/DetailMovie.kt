package com.bcaf.bcafretrofit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bcaf.bcafretrofit.R
import com.bcaf.bcafretrofit.model.ONDBResponse
import com.bcaf.bcafretrofit.model.ONDBResponseDetail
import com.bcaf.bcafretrofit.model.SearchItem
import com.bcaf.bcafretrofit.service.NetworkConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import kotlinx.android.synthetic.main.fragment_detail_movie.view.*
import kotlinx.android.synthetic.main.itemmovies.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailMovie.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailMovie : Fragment() {
    lateinit var detailMovie :ONDBResponseDetail
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_detail_movie, container, false)
        getDetailMovie(param1.toString())




        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment detailMovie.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailMovie().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

        fun getDetailMovie(title:String){
            NetworkConfig().getServiceOMDB().getMovieDetailByTitle(title).enqueue(object :
                Callback<ONDBResponseDetail> {
                override fun onResponse(
                    call: Call<ONDBResponseDetail>,
                    response: Response<ONDBResponseDetail>
                ){

            detailMovie= response.body()!!
                    txtTitleDetail.setText(detailMovie.title)
                    txtTypeDetail.setText(detailMovie.type)
                    txtYearDetail.setText(detailMovie.year)
                    txtActorsDetail.setText(detailMovie.actors)
                    txtRatedDetail.setText(detailMovie.rated)
                    txtIMDBRating.setText(detailMovie.imdbRating)
                    txtGenreDetail.setText(detailMovie.genre)
                    imgPosterDetail?.let {
                        Glide.with(requireContext())
                            .asBitmap()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .load(detailMovie.poster)
                            .into(it)
                    }

                }

                override fun onFailure(call: Call<ONDBResponseDetail>, t: Throwable) {
                   Toast.makeText(requireContext(),"gagal memuat Data",Toast.LENGTH_LONG).show()
                }
            })
    }





}