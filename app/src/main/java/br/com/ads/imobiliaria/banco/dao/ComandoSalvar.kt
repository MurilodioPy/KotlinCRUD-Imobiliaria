package br.com.ads.imobiliaria.banco.dao

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

fun comandoSalvar(command: String) {
    val processBuilder = ProcessBuilder()
    processBuilder.command("bash", "-c", command)
    try {
        val process: Process = processBuilder.start()
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val line = reader.readText()
        println(line)
        val exitVal = process.waitFor()
        if (exitVal == 0) {
            println("Success!")
        } else {
            // handle error
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
}