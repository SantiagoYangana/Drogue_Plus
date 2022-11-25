package unicauca.edu.drogue_plus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import unicauca.edu.drogue_plus.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}