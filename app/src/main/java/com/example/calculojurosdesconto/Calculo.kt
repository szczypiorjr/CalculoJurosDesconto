package com.example.calculojurosdesconto

import android.os.Parcelable
import org.parceler.Parcel

@Parcel
class Calculo(
    var valor: Double=0.0, var percentual: Double=0.0,
    var resultado: Double=0.0,
    var operacao: String? ="") :
    Parcelable {
    constructor(parcel: android.os.Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: android.os.Parcel, flags: Int) {
        parcel.writeDouble(valor)
        parcel.writeDouble(percentual)
        parcel.writeDouble(resultado)
        parcel.writeString(operacao)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Calculo> {
        override fun createFromParcel(parcel: android.os.Parcel): Calculo {
            return Calculo(parcel)
        }

        override fun newArray(size: Int): Array<Calculo?> {
            return arrayOfNulls(size)
        }
    }
}
