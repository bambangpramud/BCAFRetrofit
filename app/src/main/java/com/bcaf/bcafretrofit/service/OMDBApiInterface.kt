package com.bcaf.bcafretrofit.service
import com.bcaf.bcafretrofit.model.ONDBResponse
import com.bcaf.bcafretrofit.model.ONDBResponseDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface OMDBApiInterface {

    @GET("/?apikey=f2bfee74")
    fun searchMovie(@Query("s") search:String): Call<ONDBResponse>
    @GET("/?apikey=f2bfee74")
    fun getMovieDetailByTitle(@Query("t") search:String): Call<ONDBResponseDetail>

    @GET("/?apikey=f2bfee74&s={title}")
    fun searchMovie2(@Path("title") title:String) : Call<ONDBResponse>


}