package com.example.calculojurosdesconto

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import org.parceler.Parcels

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_resultado.*

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val calculo = Parcels.unwrap<Calculo?>(intent.getParcelableExtra("calculo"))

        if (calculo != null) {
            textViewValorInformado?.text =calculo.valor.toString()
            textViewPercentualInformado?.text=calculo.percentual.toString()
            textViewOperacaoSelecionada?.text=calculo.operacao.toString()
            textViewResultadoInformado?.text=calculo.resultado.toString()
        }

    }
}