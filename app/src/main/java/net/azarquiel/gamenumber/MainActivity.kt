package net.azarquiel.gamenumber
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() , OnClickListener {
    private lateinit var random: Random
    private lateinit var tvintentos:TextView
    private lateinit var tvmayormenor:TextView
    private lateinit var tvnumber:TextView
    var numberazar: Int = 0
    var number  = 0
    var intentos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        random = Random(System.currentTimeMillis())

        tvintentos = findViewById<TextView>(R.id.tvintentos)
        tvmayormenor = findViewById<TextView>(R.id.tvmayormenor)
        tvnumber = findViewById<TextView>(R.id.tvnumber)

        var llbotones = findViewById<LinearLayout>(R.id.llbotones)
        for (i in 0..< llbotones.childCount) {
            var btn = llbotones.getChildAt(i)
            btn.setOnClickListener(this)
        }
        newGame()
    }

    private fun newGame() {
        numberazar= (1..100).random(random)
        intentos = 0
        number = 50
        show()
    }

    private fun show() {
        tvnumber.text = "$number"
        tvintentos.text = "Intentos: $intentos"
    }

    override fun onClick(v: View?) {
        val btn = v as Button
        val n = (btn.tag as String).toInt()
        if ((number+n) < 1 || (number+n) > 100)
            return
        intentos++
        number += n
        show()
        check()
    }

    private fun check() {
        var cadena=""
        if (numberazar>number) {
            cadena ="Mayor"
        }
        else if (numberazar<number) {
            cadena = "Menor"
        }
        else {
            gameOver()
        }
        tvmayormenor.text = cadena

    }

    private fun gameOver() {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle("Felicidades.")
            .setMessage("Lo conseguistes en $intentos intentos.")
            .setPositiveButton("New Game") { dialog, which ->
                newGame()
            }
            .setNegativeButton("Fin") { dialog, which ->
                finish()
            }
            .show()
    }
}