package com.example.autenticador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val btcadar: Button = findViewById(R.id.butao2)
        btcadar.setOnClickListener{
            performLogin()
        }
    }

    private fun performLogin() {
        val email: EditText = findViewById(R.id.login)
        val senha: EditText = findViewById(R.id.senha)

        if(email.text.isEmpty() || senha.text.isEmpty()){
            Toast.makeText( this,"Erro no prenchimento", Toast.LENGTH_SHORT).show()
            return
        }
        val inputemail = email.text.toString()
        val inputsenha = senha.text.toString()

        auth.createUserWithEmailAndPassword(inputemail, inputsenha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText( this,"Sucesso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText( this,"Erro", Toast.LENGTH_SHORT).show()
                }
            }

    }
}