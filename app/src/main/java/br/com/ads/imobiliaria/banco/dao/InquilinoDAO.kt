package br.com.ads.imobiliaria.banco.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Environment
import br.com.ads.imobiliaria.banco.BancoImoveis
import br.com.ads.imobiliaria.model.Inquilino
import java.io.File
import java.io.FileOutputStream

class InquilinoDAO(context: Context) {

    private val meuBanco: SQLiteDatabase = BancoImoveis(context).writableDatabase

    fun inserir(inquilino: Inquilino) {
        val valores = ContentValues()
        valores.put("cpf", inquilino.cpf)
        valores.put("nome", inquilino.nome)
        valores.put("valor_caucao_depositado", inquilino.valorCaucaoDepositado)
        valores.put("imovel", inquilino.imovel)

        meuBanco.insert("Inquilino", null, valores)
    }

    fun obterTodos(): List<Inquilino>{

        val inquilinos = mutableListOf<Inquilino>()
        val cursor: Cursor = meuBanco.rawQuery("SELECT * FROM Inquilino", null)

        while (cursor.moveToNext()) {
            val cpf = cursor.getString(cursor.getColumnIndexOrThrow("cpf"))
            val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
            val imovel = cursor.getString(cursor.getColumnIndexOrThrow("imovel"))
            val valorCaucaoDepositado = cursor.getFloat(cursor.getColumnIndexOrThrow("valor_caucao_depositado"))

            inquilinos.add(Inquilino(cpf, nome, valorCaucaoDepositado, imovel))
        }
        cursor.close()
        return inquilinos
    }

    fun atualizar(inquilino: Inquilino) {
        val valores = ContentValues()
        valores.put("nome", inquilino.nome)
        valores.put("imovel", inquilino.imovel)
        valores.put("valor_caucao_depositado", inquilino.valorCaucaoDepositado)

        meuBanco.update("Inquilino", valores, "cpf = ?", arrayOf(inquilino.cpf))
    }

    fun excluir(cpf : String) {
        meuBanco.delete("Inquilino", "cpf = ?", arrayOf(cpf))
    }

    fun salvarArquivosInquilinos(){
        val dados = StringBuilder()
        val cursor: Cursor = meuBanco.rawQuery("SELECT * FROM Inquilino", null)

        while (cursor.moveToNext()) {
            val cpf = cursor.getString(cursor.getColumnIndexOrThrow("cpf"))
            val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
            val imovel = cursor.getString(cursor.getColumnIndexOrThrow("imovel"))
            val valorCaucaoDepositado = cursor.getFloat(cursor.getColumnIndexOrThrow("valor_caucao_depositado"))
            val inquilino = Inquilino(cpf, nome, valorCaucaoDepositado, imovel)
            dados.append("${inquilino}\n")
        }
        cursor.close()

        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(path, "inquilinos.txt")
        val outputStream = FileOutputStream(file)
        outputStream.write(dados.toString().toByteArray())
        outputStream.close()
    }
}
