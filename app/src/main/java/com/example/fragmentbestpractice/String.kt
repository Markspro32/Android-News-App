package com.example.fragmentbestpractice

operator fun String.times(n:Int):String{
    val builder = StringBuilder()
    repeat(n) {
        builder.append(this)
    }
    return builder.toString()
}