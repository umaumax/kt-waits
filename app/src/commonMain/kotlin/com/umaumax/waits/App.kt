package com.umaumax.waits

import kotlinx.coroutines.*

class App {
  val greeting: String
    get() {
      return "Hello World!"
    }
}

fun main(args: Array<String>) {
  println(App().greeting)
  runBlocking {
    launch {
      delay(1000L)
      println("World")
    }
    val text = async { "coroutine!" }.await()
    println(text)
    println("Hello")
  }
}
