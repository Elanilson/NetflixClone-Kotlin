package com.apkdoandroid.netflixclonekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apkdoandroid.netflixclonekotlin.adapter.FilmesAdapter
import com.apkdoandroid.netflixclonekotlin.databinding.ActivityDetalhesFilmeBinding
import com.apkdoandroid.netflixclonekotlin.databinding.ActivityListFilmesBinding
import com.apkdoandroid.netflixclonekotlin.model.addFilmes
import com.squareup.picasso.Picasso

class DetalhesFilmeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetalhesFilmeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        Toolbar()

        val recycler_outros_filmes = binding.recyclerOutrosFilmes
        recycler_outros_filmes.adapter = FilmesAdapter(addFilmes())
        recycler_outros_filmes.layoutManager = GridLayoutManager(applicationContext,3)

        val capaTheWitcher ="https://firebasestorage.googleapis.com/v0/b/netflix-kotlin.appspot.com/o/video.jpg?alt=media&token=e7a7159f-4e29-4fef-8620-504b3344b782"
        Picasso.get().load(capaTheWitcher).fit().into(binding.capa)

        binding.playVideo.setOnClickListener {
            val intent = Intent(this,VideoActivity::class.java)
            startActivity(intent)
        }

    }
    private fun Toolbar(){
        val toolbar_detalhes = binding.toolbarDetalhes
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            toolbar_detalhes.setNavigationIcon(getDrawable(R.drawable.ic_arrow_back_24))
        }
        toolbar_detalhes.setNavigationOnClickListener {
            val intent = Intent(this,ListFilmesActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}