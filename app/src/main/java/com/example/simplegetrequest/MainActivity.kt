package com.example.simplegetrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.simplegetrequest.PokemonAdapter
import com.example.simplegetrequest.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var queue:RequestQueue
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        queue = Volley.newRequestQueue(this)

        val view = binding.root
        setContentView(view)

        binding.btnUpdatePokemon.setOnClickListener {
            val Pokemons = getPokemonList(binding.etPokemonAmount.text.toString().toInt())
        }
    }

    fun getPokemonList(listAmount: Int){
        val url = "https://pokeapi.co/api/v2/pokemon/?limit=${listAmount}"

        val jsonRequest = JsonObjectRequest(url, Response.Listener<JSONObject>{
            binding.rvPokemonEntries.adapter = PokemonAdapter(it.getJSONArray("results"))
        },
            Response.ErrorListener {
                Log.w("JSONRESPONSE", it.message as String)
            })

        queue.add(jsonRequest)
    }

    override fun onStop() {
        super.onStop()
        queue.cancelAll("Stopped")
    }
}