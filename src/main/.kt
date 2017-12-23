package main

/**
 * Created by Samer on 23/12/2017.
 */

import java.util.*

fun validate(text: String) = (1 until text.length).none { text[it - 1] == text[it] }


fun main(args: Array<String>) {

    val sc = Scanner(System.`in`)
    sc.nextLine()
    val line = sc.nextLine()
    // only 2 char

    if (line.isEmpty() || line.length == 1) {
        println(0)
        return
    }

    val distinctChar = line.toSet()
    val disCharSize = distinctChar.size

    var maxLength = 0

    for (i in 0 until disCharSize)
        for (j in i until disCharSize) {
            val testLine = line.filter { it == distinctChar.elementAt(i) || it == distinctChar.elementAt(j) }
            if (validate(testLine)) {
                if (testLine.length > maxLength) {
                    maxLength = testLine.length
                }
            }
        }


    println(maxLength)
}
