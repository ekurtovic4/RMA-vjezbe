package ba.unsa.etf.rma.lab2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var editText: EditText
    private lateinit var button: Button
    // moze se koristiti i mutableListOf
    private val  listaVrijednosti = arrayListOf<String>()
    private lateinit var adapter : MyArrayAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Inicijalizirat cemo elemente
        button = findViewById(R.id.button1);
        editText = findViewById(R.id.editText1)
        listView = findViewById(R.id.listView1)
        //Koristi se androidov layout
        adapter = MyArrayAdapter(this, R.layout.element_list, listaVrijednosti)
        listView.adapter=adapter
        //Definisat cemo akciju u slucaju klik akcije
        button.setOnClickListener {
            addToList()
        }
    }
    //Poziva se na klik dugmenta
    private fun addToList() {
        // Novi tekst se dodaje kao prvi element
        listaVrijednosti.add(0,editText.text.toString())
        adapter.notifyDataSetChanged();
        editText.setText("");
    }
}