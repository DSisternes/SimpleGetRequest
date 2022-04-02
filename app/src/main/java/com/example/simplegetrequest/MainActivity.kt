package com.example.simplegetrequest

import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.EditText
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var queue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        queue = Volley.newRequestQueue(this)
        val btnUpdatePokemon = findViewById(R.id.btnUpdatePokemon) as Button
        val etPokemonAmount = findViewById(R.id.etPokemonAmount) as EditText

        btnUpdatePokemon.setOnClickListener {
            //val listAmount=Integer.parseInt(etPokemonAmount.text.toString())
            //getPokemonList(listAmount)
            getPokemonList(Integer.parseInt(etPokemonAmount.text.toString()))

        }
    }

    fun getPokemonList(listAmount: Int){
        val url = "https://pokeapi.co/api/v2/pokemon/?limit=${listAmount}"

        val jsonRequest = JsonObjectRequest(url, Response.Listener<JSONObject>{ response ->
            Log.i("JSONRESPONSE", response.getJSONArray("results").toString())
        },

        Response.ErrorListener{ error ->

            Log.w("JSONRESPONSE", error.message as String)

        })
        queue.add(jsonRequest)
    }


    override fun onStop() {
        super.onStop()
        queue.cancelAll("Stopped")
    }
}