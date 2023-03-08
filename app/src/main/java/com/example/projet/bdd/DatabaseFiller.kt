package com.example.projet.bdd

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.projet.bdd.entity.AptitudeEntity
import com.example.projet.bdd.entity.BaseEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.Console
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.nio.charset.CodingErrorAction

class DatabaseFiller {

    companion object {
        private val endpoints = listOf("aptitude", "containSkill", "content", "formation",
                                        "initiator", "level", "participation", "session",
                                        "skill", "status", "student", "trainingManager")

        fun FillBase(){
//            var aptEntity = AptitudeEntity(1,"nom",10,false);

            for(endpoint in endpoints){
                println(endpoint);
                var aptitudes = queryRest<AptitudeEntity>(endpoint);
            }
//            var aptitudes = queryRest<AptitudeEntity>("aptitude");
        }

         fun <BaseEntity> queryRest(entity_rest_name: String): List<BaseEntity>?{
             RestAPIRequestThread(entity_rest_name).start();
             return null;
         }

        fun insertAll(allEntities: List<List<BaseEntity>>) {
            for (entities_type in allEntities) {
                for (entity in entities_type) {

                }
            }
        }

        fun main() :String?{
            return null;
        }
    }
}


class RestAPIRequestThread(val rest_entity_name: String) : Thread() {
    override fun run() {
        val urlString = "https://dev-restandroid.users.info.unicaen.fr/REST/$rest_entity_name/index.php"
        var connection: HttpURLConnection? = null

        try {
            val url = URL(urlString)

            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))

                val stringBuilder = StringBuilder()
                var line: String?

                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line).append('\n')
                }

                // encode in the right encoding format to get the letters with accents
                val jsonString = stringBuilder.toString()
                val jsonArray = JSONArray(jsonString)

                val utf8Charset = Charset.forName("UTF-8")
                val charsetDecoder = utf8Charset.newDecoder()
                charsetDecoder.onMalformedInput(CodingErrorAction.REPORT)
                charsetDecoder.onUnmappableCharacter(CodingErrorAction.REPORT)

                val decodedJsonArray = JSONArray()

                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val decodedJsonObject = JSONObject()
                    val keys = jsonObject.keys()
                    while (keys.hasNext()) {
                        val key = keys.next() as String
                        if (jsonObject.has(key)) {
                            val value = jsonObject.getString(key)
                            val valueBytes = value.toByteArray(utf8Charset)
                            val decodedValue = charsetDecoder.decode(ByteBuffer.wrap(valueBytes)).toString()
                            decodedJsonObject.put(key, decodedValue)
                        }
                    }
                    decodedJsonArray.put(decodedJsonObject)
                }

                println(decodedJsonArray)

            } else {
                println("Error: ${connection.responseCode}")
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        } finally {
            connection?.disconnect()
        }
    }
}