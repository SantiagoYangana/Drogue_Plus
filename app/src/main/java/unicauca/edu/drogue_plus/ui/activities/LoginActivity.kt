package unicauca.edu.drogue_plus.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import unicauca.edu.drogue_plus.databinding.ActivityLoginBinding
import unicauca.edu.drogue_plus.ui.fragments.LoginFragment

class LoginActivity : AppCompatActivity() {

    private var municipio: String? = "Popayan"

    private lateinit var binding: ActivityLoginBinding


    lateinit var datos : Bundle


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        datos = intent.extras!!
        municipio = datos.getString("departamento").toString()
        binding = ActivityLoginBinding.inflate(layoutInflater)

        val bundle =  Bundle()
        bundle.putString("departamento", municipio)

        val fragmentManager = supportFragmentManager

        setContentView(binding.root)
    }

    override fun onStart(){
        super.onStart()
    }

    fun getMunicipio(): String? {
        return this.municipio
    }
}