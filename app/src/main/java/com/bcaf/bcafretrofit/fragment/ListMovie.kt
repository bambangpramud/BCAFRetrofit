package com.bcaf.bcafretrofit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcaf.bcafretrofit.R
import com.bcaf.bcafretrofit.adapter.OMDBAdapter
import com.bcaf.bcafretrofit.model.ONDBResponse
import com.bcaf.bcafretrofit.model.SearchItem
import com.bcaf.bcafretrofit.service.NetworkConfig
import kotlinx.android.synthetic.main.fragment_list_movie.*
import kotlinx.android.synthetic.main.fragment_list_movie.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListMovie.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListMovie : Fragment() {
    var listMovie = ArrayList<SearchItem?>()
    lateinit var movieAdapter : OMDBAdapter
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

       val view = inflater.inflate(R.layout.fragment_list_movie, container, false)
        // Inflate the layout for this fragment
        view.btnSearch.setOnClickListener({
            searchMovie(txtSearch.text.toString())
        })
        return view
    }

    fun searchMovie (title:String){
        NetworkConfig().getServiceOMDB().searchMovie(title).enqueue(object :
            Callback<ONDBResponse> {
            override fun onResponse(
                call: Call<ONDBResponse>,
                response: Response<ONDBResponse>
            ) {
                Log.d("Response",response.toString())

                if (response.body()?.search != null) {
                    setupList(
                        response.body()?.search as List<SearchItem>
                    )

                    var listMovie = response.body()?.search!!

                    val movieAdapter = ArrayList(listMovie).let { OMDBAdapter(it) }
                    lstMovie.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = movieAdapter
                    }

                } else {
                    Toast.makeText(requireContext(),"Data yang dimasukkan tidak valid",Toast.LENGTH_LONG).show()
                }
//                movieAdapter = ArrayList(listMovie)?.let { OMDBAdapter(it) }!!
//                lstMovie.apply{
//                    layoutManager = LinearLayoutManager(requireContext())
//                    adapter = movieAdapter
//                }
            }

            override fun onFailure(call: Call<ONDBResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun setupList(list : List<SearchItem>){

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListMovie.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListMovie().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}