package unicauca.edu.drogue_plus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import unicauca.edu.drogue_plus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    var item = "--"

    private lateinit var bindingDep: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDep = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingDep.root)
        val dep = resources.getStringArray(R.array.departamento_menu)
        val adapter = ArrayAdapter(
            this,
            R.layout.lista_departamentos,
            dep
        )

        with(bindingDep.dropdownmenu) {
            setAdapter(adapter)
            onItemClickListener = this@MainActivity
        }



    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int,id:Long) {
        item = parent?.getItemAtPosition(position).toString()
        Toast.makeText(this@MainActivity,item,Toast.LENGTH_SHORT).show()

        val btnGoLogin : Button = findViewById(R.id.btnGoLogin)
        val miIntent = Intent(this,LoginActivity::class.java)

        val miBundle = Bundle()

        miBundle.putString("departamento",item)

        miIntent.putExtras(miBundle)

        btnGoLogin.setOnClickListener {
            startActivity(miIntent)
        }
    }
}