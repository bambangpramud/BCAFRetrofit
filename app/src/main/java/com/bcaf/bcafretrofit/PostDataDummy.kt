package com.bcaf.bcafretrofit

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcaf.bcafretrofit.adapter.DummyDraftAdapter
import com.bcaf.bcafretrofit.adapter.OMDBAdapter
import com.bcaf.bcafretrofit.database.DummyDatabase
import com.bcaf.bcafretrofit.model.PostDummyData
import com.bcaf.bcafretrofit.service.NetworkConfig
import com.bcaf.bcafretrofit.service.PostDummyDataResponse
import kotlinx.android.synthetic.main.activity_post_data_dummy.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostDataDummy : AppCompatActivity() {
    var selectionList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_data_dummy)
        loadData()
        txtTags.setOnClickListener({

            createMultipleSelecDialog()
        })

        btnPostDummy.setOnClickListener({
            val connectionManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectionManager.activeNetworkInfo

            if (networkInfo !=null && networkInfo.isConnected==true){
                postData()
            }else{
                GlobalScope.launch {
                    var dummyData = PostDummyData(0,"60d0fe4f5311236168a109f4","https://asset.kompas.com/crops/t203r65Uhv0N05wET3YD7XWClXQ=/378x18:1190x559/750x500/data/photo/2022/07/26/62df85f57f522.png",txtText.text.toString(),
                        txtLikes.text.toString().toInt() ,selectionList)
                    DummyDatabase.getInstance(applicationContext).dummyDao().insertDummy(dummyData)
                    runOnUiThread{
                        Toast.makeText(applicationContext,"maaf jaringan anda kureng", Toast.LENGTH_LONG).show()
                    }
                }
            }
//           postData()
        })


    }



    fun loadData(){


        GlobalScope.launch {
            var data : List<PostDummyData> = DummyDatabase.getInstance(applicationContext).dummyDao().getAll()

            Log.d("data",data.toString())
            val dummyAdapter = ArrayList(data).let { DummyDraftAdapter(it) }
            RVDummyAPI.apply {

                layoutManager = LinearLayoutManager(this@PostDataDummy)
                adapter=dummyAdapter
            }
        }


    }

    fun postData(){
        var dummyData = PostDummyData(0,"60d0fe4f5311236168a109f4","https://asset.kompas.com/crops/t203r65Uhv0N05wET3YD7XWClXQ=/378x18:1190x559/750x500/data/photo/2022/07/26/62df85f57f522.png",txtText.text.toString(),
            txtLikes.text.toString().toInt() ,selectionList)
        NetworkConfig().getServiceDummy().postData(dummyData).enqueue( object :Callback<PostDummyDataResponse>{
            override fun onResponse(
                call: Call<PostDummyDataResponse>,
                response: Response<PostDummyDataResponse>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(applicationContext,response.message()+" Data berhasil ditambah",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<PostDummyDataResponse>, t: Throwable) {
                Toast.makeText(applicationContext,"Gagal cuk",Toast.LENGTH_LONG).show()
            }

        })
    }


    fun createMultipleSelecDialog(){
        val listItem = arrayOf("Movies","Actor","Fun")
        val listChecked = booleanArrayOf(false,false,false)


        val builder = AlertDialog.Builder(this)

        builder.setTitle("Choose Tags")

        builder.setMultiChoiceItems(listItem,listChecked){
            dialog, which,isChecked ->
            listChecked[which] = isChecked
        }
        builder.setCancelable(false)
        builder.setNegativeButton("Cancel"){
            dialog,which ->
            Toast.makeText(applicationContext,"Cancel ${which}",Toast.LENGTH_LONG).show()
        }
        builder.setPositiveButton("Submit"){
            dialog,which ->
            txtTags.setText("")
            selectionList.clear()

            for((index,value) in listChecked.withIndex()){
                if (value){
                    selectionList.add(listItem[index])
                }
            }

            for (listItem in selectionList){
                txtTags.append("${listItem}, ")
            }

//            txtTags.setText(txtTags.text.toString().substring(0,(txtTags.text.toString().length-2)))
            txtTags.setText(txtTags.text.toString().dropLast(2))
            Toast.makeText(applicationContext,"Submit ${which}",Toast.LENGTH_LONG).show()
        }
        builder.create()
        val alertDialog = builder.create()
        alertDialog.show()
    }
}