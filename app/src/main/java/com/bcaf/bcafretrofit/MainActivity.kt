package com.bcaf.bcafretrofit

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcaf.bcafretrofit.adapter.OMDBAdapter
import com.bcaf.bcafretrofit.fragment.ListMovie
import com.bcaf.bcafretrofit.model.ONDBResponse
import com.bcaf.bcafretrofit.model.SearchItem
import com.bcaf.bcafretrofit.service.NetworkConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var orientation:Int=0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        orientation=resources.configuration.orientation
        if (orientation== Configuration.ORIENTATION_PORTRAIT){
            loadFragmentFirst(ListMovie.newInstance("",""))
        }



    }
    fun loadFragmentFirst(fragment : Fragment){
        val fragManager = supportFragmentManager.beginTransaction()
        fragManager.replace(R.id.vFragment,fragment)

        fragManager.commit()
    }
    fun loadFragment(fragment : Fragment){
        val fragManager = supportFragmentManager.beginTransaction()
        fragManager.replace(R.id.vFragment,fragment)
        fragManager.addToBackStack("")
        fragManager.commit()
    }

}