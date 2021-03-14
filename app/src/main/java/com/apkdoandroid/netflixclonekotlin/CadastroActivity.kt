package com.apkdoandroid.netflixclonekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.apkdoandroid.netflixclonekotlin.databinding.ActivityCadastroBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        toolbar()
        binding.btCadastrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagem_Erro =  binding.mensagemErro
            if(email.isEmpty() || senha.isEmpty()){
                mensagem_Erro.setText("Preencha todos os campos")

            }else{
                cadastrarUsuario()
            }
        }
    }

    private fun cadastrarUsuario() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagem_Erro =  binding.mensagemErro

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this,"Usuário cadastrado com sucesso",Toast.LENGTH_SHORT)
                binding.editEmail.setText("")
               binding.editSenha.setText("")
                mensagem_Erro.setText("")

            }
        }.addOnFailureListener {
            var error = it

            when{
                error is FirebaseAuthWeakPasswordException -> mensagem_Erro.setText("Digite uma senha com no minimo 6 caracteres")
                error is FirebaseAuthUserCollisionException -> mensagem_Erro.setText("Está conta já foi cadastrada")
                error is FirebaseNetworkException -> mensagem_Erro.setText("Sem conexão com a internet")
                else -> mensagem_Erro.setText("Erro ao cadastrar usuário!")

            }

        }
        //
    }

    private fun toolbar(){
        val toolbar = binding.toolbarCadastro
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            toolbar.setBackgroundColor(getColor(R.color.white))
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setNavigationIcon(getDrawable(R.drawable.ic_netflix_official_logo))
        }

    }
}