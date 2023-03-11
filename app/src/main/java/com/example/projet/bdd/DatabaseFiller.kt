package com.example.projet.bdd

import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.room.util.joinIntoString
import com.example.projet.Plongee
import com.example.projet.bdd.dao.AptitudeDAO
import com.example.projet.bdd.entity.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
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

        // reste à créer : initiator
        // en train d'être crée : trainingManager (TrainingInitiatorRelation, nécessite qu'Initiator soit crée)

        fun FillBase(){
            // requests ; elles sont dans l'ordre donc ne pas mettre le désordre

            Thread{
                var level = queryRest("level",LevelEntity::class.java);
                Thread.sleep(3000)
                var status = queryRest("status",StatusEntity::class.java);
                Thread.sleep(3000)
                var formation = queryRest("formation",FormationEntity::class.java);
                Thread.sleep(3000)
                var session = queryRest("session",SessionEntity::class.java);
                Thread.sleep(3000)
                var skill = queryRest("skill",SkillEntity::class.java);
                Thread.sleep(3000)
                var aptitudes = queryRest("aptitude", AptitudeEntity::class.java)
                Thread.sleep(3000)
                var student = queryRest("student",StudentEntity::class.java);
                Thread.sleep(3000)
                var content = queryRest("content",ContentEntity::class.java);
                Thread.sleep(3000)
                var participation = queryRest("participation",ParticipationEntity::class.java);
            }.start()
        }


        private fun queryRest(entity_rest_name: String, clazz: Class<out BaseEntity>) {
            val handler = Handler(Looper.getMainLooper(), object : Handler.Callback {
                override fun handleMessage(message: Message): Boolean {
                    val jsonArray = message.obj as JSONArray

                    createAndInsertEntities(jsonArray,clazz)
                    return true
                }
            })

            val json = RestAPIRequestThread(entity_rest_name, handler)
        }

        private fun createAndInsertEntities(jsonArray: JSONArray, clazz: Class<out BaseEntity>) {
            Thread{
                when(clazz){
                    // APTITUDE
                    AptitudeEntity::class.java ->{
                        println("AptitudeEntity")

                        for (i in 0 until jsonArray.length()) {
                            val dict = jsonArray.getJSONObject(i)

                            val aptitudeDAO = BDD.getInstance(Plongee.getAppContext()!!).AptitudeDao().insertOne(AptitudeEntity(
                                dict["id"].toString().toLong(),
                                dict["name"].toString(),
                                dict["skillId"].toString().toLong(),
                                dict["deleted"].toString().toBoolean()))
                        }
                    }

                    // CONTENT
                    ContentEntity::class.java -> {
                        println("ContentEntity")

                        for (i in 0 until jsonArray.length()) {
                            val dict = jsonArray.getJSONObject(i)

                            val contentDAO = BDD.getInstance(Plongee.getAppContext()!!).ContentDao().insertOne(
                                ContentEntity(
                                    dict["id"].toString().toLong(),
                                    dict["sessionId"].toString().toLong(),
                                    dict["aptitudeId"].toString().toLong()))
                        }
                    }

                    // FORMATION
                    FormationEntity::class.java -> {
                        println("FormationEntity")

                        for (i in 0 until jsonArray.length()) {
                            val dict = jsonArray.getJSONObject(i)

                            val formationDao = BDD.getInstance(Plongee.getAppContext()!!).FormationDao().insertOne(FormationEntity(
                                    dict["id"].toString().toLong(),
                                    dict["name"].toString(),
                                    dict["deleted"].toString().toBoolean(),
                                    dict["levelId"].toString().toLong()))
                        }
                    }

                    // LEVEL
                    LevelEntity::class.java -> {
                        println("LevelEntity")

                        for (i in 0 until jsonArray.length()) {
                            val dict = jsonArray.getJSONObject(i)

                            try{
                                val levelDao = BDD.getInstance(Plongee.getAppContext()!!).LevelDao().insertOne(
                                    LevelEntity(
                                        dict["id"].toString().toLong(),
                                        dict["name"].toString(),
                                        dict["deleted"].toString().toBoolean()
                                    ))
                            }catch(e: java.lang.Exception){
                                println("err")
                            }finally {
                                var lvl =
                                    listOf(BDD.getInstance(Plongee.getAppContext()!!).LevelDao().getAll())
                            }


                        }


                    }

                    // PARTICIPATION
                    ParticipationEntity::class.java -> {
                        println("ParticipationEntity")

                        for (i in 0 until jsonArray.length()) {
                            val dict = jsonArray.getJSONObject(i)

                            val participationDao = BDD.getInstance(Plongee.getAppContext()!!).ParticipationDao().insertOne(ParticipationEntity(
                                dict["id"].toString().toLong(),
                                dict["contentId"].toString().toLong(),
                                dict["studentId"].toString().toLong(),
                                dict["statusId"].toString().toLong(),
                                dict["commentary"].toString()))
                        }
                    }

                    // SESSION
                    SessionEntity::class.java -> {
                        println("SessionEntity")

                        for (i in 0 until jsonArray.length()) {
                            val dict = jsonArray.getJSONObject(i)

                            val sessionDao = BDD.getInstance(Plongee.getAppContext()!!).SessionDao().insertOne(SessionEntity(
                                dict["id"].toString().toLong(),
                                dict["date"].toString(),
                                dict["formationId"].toString().toLong(),
                                dict["deleted"].toString().toBoolean()))
                        }
                    }

                    // SKILL
                    SkillEntity::class.java -> {
                        println("SkillEntity")

                        for (i in 0 until jsonArray.length()) {
                            val dict = jsonArray.getJSONObject(i)

                            val skillDao = BDD.getInstance(Plongee.getAppContext()!!).SkillDao().insertOne(SkillEntity(
                                dict["id"].toString().toLong(),
                                dict["name"].toString(),
                                dict["deleted"].toString().toBoolean(),
                                dict["levelId"].toString().toLong()))
                        }
                    }

                    // STATUS
                    StatusEntity::class.java -> {
                        println("StatusEntity")

                        for (i in 0 until jsonArray.length()) {
                            val dict = jsonArray.getJSONObject(i)

                            val statusDao = BDD.getInstance(Plongee.getAppContext()!!).StatusDao().insertOne(StatusEntity(
                                dict["id"].toString().toLong(),
                                dict["name"].toString(),
                                dict["color"].toString()))
                        }
                    }

                    // STUDENT
                    StudentEntity::class.java -> {
                        println("StudentEntity")

                        for (i in 0 until jsonArray.length()) {
                            val dict = jsonArray.getJSONObject(i)

                            val studentDao = BDD.getInstance(Plongee.getAppContext()!!).StudentDao().insertOne(StudentEntity(
                                dict["id"].toString().toInt(),
                                dict["name"].toString(),
                                dict["formationId"].toString().toInt(),
                                dict["deleted"].toString().toBoolean(),
                                dict["phone"].toString()))
                        }
                    }

                    else -> println("BaseEntity")
                }
            }.start()

        }


        private fun RestAPIRequestThread(rest_entity_name: String, handler: Handler) {
            Thread{
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

                        var decodedJsonArray = JSONArray()

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

                        val message = handler.obtainMessage()
                        message.obj = decodedJsonArray
                        handler.sendMessage(message)
                    } else {
                        println("Error: ${connection.responseCode}")
                    }
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                } finally {
                    connection?.disconnect()
                }
            }.start()
        }

    }
}
