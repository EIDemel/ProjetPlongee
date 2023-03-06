package com.example.projet.bdd

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.projet.bdd.entity.BaseEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.Console
import java.net.HttpURLConnection
import java.net.URL

class DatabaseFiller {

    val host = "https://dev-restandroid.users.info.unicaen.fr/REST/";

    companion object {
/*        fun FillBase(){
            var aptEntity = AptitudeEntity(1,"nom",10,false);

            var aptitudes = queryRest<AptitudeEntity>("entities");
        }*/

        /* fun <T> queryRest(parameterName: String): List<T>{

        }*/

        fun insertAll(allEntities: List<List<BaseEntity>>) {
            for (entities in allEntities) {
                for (entity in entities) {

                }
            }
        }

        fun main() :String{
            var url = URL("http://www.unicaen.fr")
            var conn = url.openConnection() as HttpURLConnection
            conn.connectTimeout = 3000; // 3 s avant timeout (connect)
            conn.readTimeout = 1000; // 1 s avant timeout (lecture)
            conn.doInput = false; // pas de résultat
            conn.useCaches = false; // ne pas lire les caches
            conn.setRequestProperty("Accept","application/xml");
            conn.doOutput = true;
            val flux = conn.outputStream;
            flux.close();

            conn.connect();

            return conn.responseCode as String;
        }
    }
}