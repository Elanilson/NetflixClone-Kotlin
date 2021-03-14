package com.apkdoandroid.netflixclonekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.apkdoandroid.netflixclonekotlin.adapter.FilmesAdapter
import com.apkdoandroid.netflixclonekotlin.databinding.ActivityListFilmesBinding
import com.apkdoandroid.netflixclonekotlin.model.addFilmes
import com.apkdoandroid.netflixclonekotlin.onclick.OnItemClickListener
import com.apkdoandroid.netflixclonekotlin.onclick.addOnItemClickListener
import com.google.firebase.auth.FirebaseAuth

class ListFilmesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListFilmesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recycler_filmes = binding.recyclerview
        recycler_filmes.adapter = FilmesAdapter(addFilmes())
        recycler_filmes.layoutManager = GridLayoutManager(applicationContext,3)

        recycler_filmes.addOnItemClickListener(object: OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {

                when{
                    position == 0 -> DetalhesFilme()
                }
            }
        })

    }

    private fun DetalhesFilme(){
        val intent = Intent(this,DetalhesFilmeActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.mennu_principal,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.deslogar -> {
                FirebaseAuth.getInstance().signOut()
                VoltarTelaLogin()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun VoltarTelaLogin(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}