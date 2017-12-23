package main

/**
 * Created by Samer on 23/12/2017.
 */


import java.util.*

fun validate(text: String, disCharSize: Int): Boolean {
    if (disCharSize == 2) {
        var holder: Char? = null
        for (c in text) {
            if (holder == null) {
                holder = c
                continue
            }
            if (holder == c)
                return false
            holder = c
        }
        return true
    }
    return false
}

fun remove(line: String, list: List<Char>) =
        line.filterNot { list.contains(it) }

fun adjustIndexList(indexes: MutableList<Int>, size: Int) {
//    for (i in indexes.size - 1 downTo 0) {
//        if (indexes[i] != size - 1) {
//            indexes[i]++
//            break
//        } else {
//            indexes[i]--
//        }
//    }
    for (i in indexes.size - 1 downTo 0) {
        if (indexes[i] != size - (indexes.size - i)) {
            indexes[i]++
            var holder = indexes[i]
            for (j in i + 1 until indexes.size)
                indexes[j] = ++holder
            break
        }
    }
}

fun getFactoryFor(x: Int): Int {
    var res = 1
    for (i in (1..x)) {
        res *= i
    }
    return res
}

//use index instead of list
// if we wanna get all the problity for 3 char
// then we create list of 3 indexes
// first iteration will be 1 2 3 output is list of char in that index
// second 1 2 4
// third 1 3 4
// like clock
fun getRemovalList(disChar: Set<Char>, size: Int): MutableList<List<Char>> {
    val result = mutableListOf<List<Char>>()

    val disSize = disChar.size
    val repeatUntil = getFactoryFor(disSize) / (getFactoryFor(size) * getFactoryFor(disSize - size))

    val indexList = (0 until size).toMutableList()

    for (i in 1..repeatUntil) {
        val list = indexList.map { disChar.elementAt(it) }
        result.add(list)
        adjustIndexList(indexList, disSize)
    }


    return result
}

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

    if (disCharSize < 3) {
        if (validate(line, disCharSize)) {
            println(line.length)
        } else
            println(0)
        return
    }

    var maxLength = 0
    for (i in 1..disCharSize - 2) {
        val removalList = getRemovalList(distinctChar, i)
        for (list in removalList) {
            val testLine = remove(line, list)
            if (validate(testLine, disCharSize - i)) {
                if (testLine.length > maxLength) {
                    maxLength = testLine.length
                }
            }
        }
    }

    println(maxLength)
}

fun testProbabilty() {
    val line = "abcde"


    for (i in 1..line.length + 1)
        println(getRemovalList(line.toSet(), i).joinToString(","))

}
