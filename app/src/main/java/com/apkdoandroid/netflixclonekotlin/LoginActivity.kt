package com.apkdoandroid.netflixclonekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.apkdoandroid.netflixclonekotlin.databinding.ActivityLoginBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        verificarUsuarioLogado()

        binding.txtTelaCadastro.setOnClickListener{
            startActivity(Intent(this,CadastroActivity::class.java))
        }

        binding.btEntrar.setOnClickListener {
            val email = binding.editEmail.toString()
            val senha = binding.editSenha.toString()
            val mensagem_Erro = binding.mensagemErro
            if(email.isEmpty() || senha.isEmpty()){
                mensagem_Erro.setText("Preencha todos os campos")

            }else{
                autenticarUsuario()
            }

        }
    }

    private fun autenticarUsuario() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagem_erro = binding.mensagemErro

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this,"Login efetuado com Sucesso!",Toast.LENGTH_SHORT).show()
                IrParaTelaDeFilmes()
            }
        }.addOnFailureListener {

            var erro = it

            when{
                erro is FirebaseAuthInvalidCredentialsException -> mensagem_erro.setText("E-mail ou Senha estão incorretos")
                erro is FirebaseNetworkException -> mensagem_erro.setText("Sem conexão com a internet!")
                else -> mensagem_erro.setText("Erro ao logar usuário")
            }
        }
    }
    fun verificarUsuarioLogado(){
        val usuarioLogado = FirebaseAuth.getInstance().currentUser
        if(usuarioLogado != null){
            IrParaTelaDeFilmes()

        }
    }

    private fun IrParaTelaDeFilmes() {
        startActivity(Intent(this,ListFilmesActivity::class.java))
        finish()
    }

}