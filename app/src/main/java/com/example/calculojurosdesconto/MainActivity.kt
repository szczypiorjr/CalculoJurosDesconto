package com.example.calculo

import com.example.calculojurosdesconto.Calculo
import com.example.calculojurosdesconto.ResultadoActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.calculojurosdesconto.R

//todo:Kotlin Android extensions pra acessar diretamente as widgets sem uso de findViewById
import kotlinx.android.synthetic.main.activity_main.*
import org.parceler.Parcels

class MainActivity : AppCompatActivity() {
    private var calculo: Calculo = Calculo(0.0, 0.0, 0.0, "Desconto")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val buttonCalc =findViewById<Button>(R.id.buttonCalc)as Button
        //val EditTextValor=findViewById(R.id.editTextNumberDecimalValor)as EditText
        //val EditTextPercentual=findViewById(R.id.editTextNumberDecimalPercentual)as EditText
        radioButtonJuros.setChecked(false)
        radioButtonDesconto.setChecked(true)

        radioButtonJuros.setOnClickListener {
            this.calculo.operacao = "Juros"
        }

        radioButtonDesconto.setOnClickListener {
            this.calculo.operacao = "Desconto"
        }

        buttonCalc.setOnClickListener {
            val percentual = editTextNumberDecimalPercentual.text.toString()
            val valor = editTextNumberDecimalValor.text.toString()

            if (this.validaCamposEntrada(percentual, valor)) {
                this.calculo.percentual = percentual.toDouble()
                this.calculo.valor = valor.toDouble()

                if (calcular(calculo)) {
                    Toast.makeText(this, "Calculando...", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ResultadoActivity::class.java)
                    intent.putExtra("calculo", Parcels.wrap(calculo))
                    startActivity(intent)
                } else
                    Toast.makeText(this, "informe a operação", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "informe os valores dos campos", Toast.LENGTH_SHORT).show()
     }
    }

    private fun calcular(calculo: Calculo): Boolean {
        var retorno: Boolean = true
        if (calculo.operacao.equals("Desconto"))
            aplicarDesconto(calculo)
        if (calculo.operacao.equals("Juros"))
            aplicarJuros(calculo)
        if (calculo.operacao.equals(""))
            retorno = false
        return retorno;
    }

    private fun validaCamposEntrada(percentual: String, valor: String): Boolean {
        var valido: Boolean = (percentual?.isNotEmpty() && valor?.isNotEmpty())
        return valido
    }

    private fun aplicarDesconto(calculo: Calculo): Calculo {
        var porcentagem: Double
        porcentagem = (calculo.percentual / 100)
        calculo.resultado = (calculo.valor - (calculo.valor * porcentagem))
        return calculo
    }

    private fun aplicarJuros(calculo: Calculo): Calculo {
        var porcentagem: Double
        porcentagem = (calculo.percentual / 100)
        calculo.resultado = (calculo.valor + (calculo.valor * porcentagem))
        return calculo
    }
}