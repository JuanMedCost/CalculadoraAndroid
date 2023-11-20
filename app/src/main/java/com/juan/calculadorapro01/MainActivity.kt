package com.juan.calculadorapro01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private var resultado : Double = 0.0
    private var numOp : Double = 0.0
    private var operation : Int = 0
    private var comillas : Boolean = false
    private var comillasSW : Boolean = false
    private var operationSW : Boolean = false
    private var primeraOp : Boolean = true
    private var prevResultado : Double = 0.0
    private var signo : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        unoBoton.setOnClickListener{añadirTextoOperaciones("1")}
        dosBoton.setOnClickListener{añadirTextoOperaciones("2")}
        tresBoton.setOnClickListener{añadirTextoOperaciones("3")}
        cuatroBoton.setOnClickListener{añadirTextoOperaciones("4")}
        cincoBoton.setOnClickListener{añadirTextoOperaciones("5")}
        seisBoton.setOnClickListener{añadirTextoOperaciones("6")}
        sieteBoton.setOnClickListener{añadirTextoOperaciones("7")}
        ochoBoton.setOnClickListener{añadirTextoOperaciones("8")}
        nueveBoton.setOnClickListener{añadirTextoOperaciones("9")}
        ceroBoton.setOnClickListener{añadirTextoOperaciones("0")}
        comaBoton.setOnClickListener{activarComillas()}
        sumarBoton.setOnClickListener{hacerOperaciones(Suma)}
        restarBoton.setOnClickListener{hacerOperaciones(Resta)}
        multiplicarBoton.setOnClickListener{hacerOperaciones(Multi)}
        dividirBoton.setOnClickListener{hacerOperaciones(Divi)}
        raizBoton.setOnClickListener{hacerOperaciones(Raiz)}
        potenciaBoton.setOnClickListener{hacerOperaciones(Potencia)}

        borrarBoton.setOnClickListener{
            numOp = 0.0
            resultadoPantalla.text = "0"
            comillas = false
            comillasSW = false
        }

        borrarTotalBoton.setOnClickListener {
            resultado = 0.0
            numOp = 0.0
            prevResultado = 0.0
            signo = ""
            resultadoAcumulado.text = "0"
            resultadoPantalla.text = "0"
            operation = NoOp
            comillas = false
            comillasSW = false
            operationSW = false
            primeraOp = true
        }

        igualBoton.setOnClickListener {
            prevResultado = resultado
            resultado = operaciones()
            comillas = false
            comillasSW = false
            operationSW = true
            primeraOp == true
            resultadoPantalla.text = resultado.toString()
            resultadoAcumulado.text = (prevResultado.toString() + " " + signo +" " + numOp.toString() + " = ")
            //resultadoAcumulado.text = resultadoAcumulado.text.filterNot { c -> " = ".contains(c) }

        }

        positivosNegativos.setOnClickListener {
            numOp = numOp * (-1)
            resultadoPantalla.text = numOp.toString()
        }
    }

    private fun operaciones(): Double {
        var result = when(operation){
            Suma -> resultado + numOp
            Resta -> resultado - numOp
            Multi -> resultado * numOp
            Divi -> resultado / numOp
            Raiz -> sqrt(resultado)
            Potencia -> resultado.pow(numOp)
            else -> 0.0
        }
        return result
    }

    private fun añadirTextoOperaciones(numero : String) {
        if(operationSW == true){
            operationSW = false
            numOp = 0.0
            comillas = false
            comillasSW = false
        }


        var indiceDecimal = numOp.toString().indexOf(".")
        var numEntero = numOp.toString().substring(0,indiceDecimal)
        var numDecimal = numOp.toString().substring(indiceDecimal)
        if(comillas){
            if(comillasSW){
                numDecimal = "."
                comillasSW = false
            }
            numOp = (numEntero + numDecimal + numero).toDouble()
        }else{
            numOp = (numEntero + numero + numDecimal).toDouble()
        }


        resultadoPantalla.text = numOp.toString()
    }

    companion object{
        const val NoOp = 0
        const val Suma = 1
        const val Resta = 2
        const val Multi = 3
        const val Divi = 4
        const val Raiz = 5
        const val Potencia = 6

    }

    private fun hacerOperaciones(operacion : Int) {
        prevResultado = resultado
        if(!operationSW){
            operationSW = true
            if(primeraOp == true){
                primeraOp = false
                resultado = numOp
            }else{
                resultado = operaciones()
            }
        }

        this.operation = operacion
        signo = when(operation){
            Suma -> " +"
            Resta -> " -"
            Multi -> " x"
            Divi -> " ÷"
            Raiz -> " √"
            Potencia -> " ^"
            else -> " ="
        }
        //resultadoPantalla.text = numOp.toString()
        resultadoAcumulado.text = (resultado.toString() + " " + signo +" ")
    }

    private fun activarComillas(){
        if(!comillas){
            comillas = true
            comillasSW = true
        }
    }
}



















