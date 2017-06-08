package com.gbizotto.retrofittest

import com.gbizotto.retrofittest.model.Datum

/**
 * Created by gabriela on 07/06/17.
 */
interface Forward {

    fun forwardToNext(datum: Datum)
}