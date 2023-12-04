package br.com.ads.imobiliaria.banco.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Environment
import br.com.ads.imobiliaria.banco.BancoImoveis
import br.com.ads.imobiliaria.model.Imovel
import java.io.File
import java.io.FileOutputStream

class ImovelDAO(context: Context) {
    private val meuBanco: SQLiteDatabase = BancoImoveis(context).writableDatabase

    fun inserir(imovel: Imovel) {
        val valores = ContentValues()
        valores.put("matricula", imovel.matricula)
        valores.put("endereco", imovel.endereco)
        valores.put("valor_aluguel", imovel.valoraluguel)

        meuBanco.insert("Imovel", null, valores)
    }

    fun obterTodos(): List<Imovel>{
        val imoveis = mutableListOf<Imovel>()
        val cursor: Cursor = meuBanco.rawQuery("SELECT * FROM Imovel", null)

        while (cursor.moveToNext()) {
            val matricula = cursor.getString(cursor.getColumnIndexOrThrow("matricula"))
            val endereco = cursor.getString(cursor.getColumnIndexOrThrow("endereco"))
            val valorAluguel = cursor.getFloat(cursor.getColumnIndexOrThrow("valor_aluguel"))

            imoveis.add(Imovel(matricula, endereco, valorAluguel))
        }
        cursor.close()
        return imoveis
    }

    fun atualizar(imovel: Imovel) {
        val valores = ContentValues()
        valores.put("endereco", imovel.endereco)
        valores.put("valor_aluguel", imovel.valoraluguel)

        meuBanco.update("Imovel", valores, "matricula = ?", arrayOf(imovel.matricula))
    }

    fun excluir(matricula : String) {
        meuBanco.delete("Imovel", "matricula = ?", arrayOf(matricula))
    }

    fun salvarArquivosImoveis(){
        val dados = StringBuilder()
        val cursor: Cursor = meuBanco.rawQuery("SELECT * FROM Imovel", null)

        while (cursor.moveToNext()) {
            val matricula = cursor.getString(cursor.getColumnIndexOrThrow("matricula"))
            val endereco = cursor.getString(cursor.getColumnIndexOrThrow("endereco"))
            val valorAluguel = cursor.getFloat(cursor.getColumnIndexOrThrow("valor_aluguel"))
            val imovel = Imovel(matricula, endereco, valorAluguel)
            dados.append("${imovel}\n")
        }
        cursor.close()

        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(path, "Imoveis.txt")
        val outputStream = FileOutputStream(file)
        outputStream.write(dados.toString().toByteArray())
        outputStream.close()
    }
}