package com.ilham.myuas.kelas_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.ilham.myuas.Adapter.CatatanAdapter
import com.ilham.myuas.Api.ApiRetrofit
import com.ilham.myuas.Model.CatatanModel
import com.ilham.myuas.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class daftar_hadirA : AppCompatActivity() {

    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var catatanAdapter: CatatanAdapter
    private lateinit var lisNote : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tgbarat)

        setupList()
    }

    private fun setupList(){
        lisNote = findViewById(R.id.list_catatan)
        catatanAdapter = CatatanAdapter(arrayListOf())
        lisNote.adapter = catatanAdapter
    }

    override fun onStart() {
        super.onStart()
        getNote()
    }

    private fun getNote(){
        api.data().enqueue(object : Callback<CatatanModel> {

            override fun onResponse(call: Call<CatatanModel>, response: Response<CatatanModel>) {
                if (response.isSuccessful){
                    val listData = response.body()!!.sekolah
                    catatanAdapter.setData( listData )
//                    listData.forEach {
//                        Log.e("daftar_hadir","nama ${it.nama}")
//                    }
                }
            }

            override fun onFailure(call: Call<CatatanModel>, t: Throwable) {
                Log.e("daftar_hadir",t.toString())
            }

        })
    }
}