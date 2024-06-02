package com.example.vehicalhealthmonitoringsys

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/register")
    fun registerVehicle(@Body vehicle: Vehicle): Call<String>

    @GET("Register/{vehiclenumber}")
    fun getVehicleDetails(@Path("vehiclenumber") vehiclenumber: String): Call<List<Vehicle>>


    @GET("/Status/{vehiclenumber}")
    fun getVehicleStatus(@Path("vehiclenumber") vehiclenumber: String): Call<List<VehicleStatus>>
}