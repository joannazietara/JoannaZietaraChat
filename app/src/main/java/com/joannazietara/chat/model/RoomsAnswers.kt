package com.joannazietara.chat.model

class RoomsAnswers {
    val keywords = hashMapOf<Int, List<List<String>>>(1 to listOf(listOf("gdzie", "dziekanat"), listOf("jakim", "miejscu", "dziekanat"), listOf("jakim", "budynku", "dziekanat")),
            2 to listOf(listOf("kiedy", "dziekanat"), listOf("jakich", "godzinach", "dziekanat"), listOf("jakie", "dni", "dziekanat"), listOf("godziny", "dziekanat")),
            3 to listOf(listOf("rozmieszczenie", "sal"), listOf("rozmieszczeniem", "sal"), listOf("znajde", "sale")),
            4 to listOf(listOf("ksero")),
            5 to listOf(listOf("szatnia", "szatnie")))

}