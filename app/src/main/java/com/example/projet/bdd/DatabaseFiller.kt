package com.example.projet.bdd

import com.example.projet.bdd.entity.*
import com.example.projet.bdd.entity.relations.FormationSkillRelation
import com.example.projet.bdd.entity.relations.TrainingInitiatorRelation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
            // requests
            var aptitudes = queryRest<AptitudeEntity>("aptitude",AptitudeEntity::class.java);
            var containSkill = queryRest<FormationSkillRelation>("containSkill",FormationSkillRelation::class.java);
            var content = queryRest<ContentEntity>("content",ContentEntity::class.java);
            var formation = queryRest<FormationEntity>("formation",FormationEntity::class.java);
            var initiator = queryRest<InitiatorEntity>("initiator",InitiatorEntity::class.java);
            var level = queryRest<LevelEntity>("level",LevelEntity::class.java);
            var participation = queryRest<ParticipationEntity>("participation",ParticipationEntity::class.java);
            var session = queryRest<SessionEntity>("session",SessionEntity::class.java);
            var skill = queryRest<SkillEntity>("skill",SkillEntity::class.java);
            var status = queryRest<StatusEntity>("status",StatusEntity::class.java);
            var student = queryRest<StudentEntity>("student",StudentEntity::class.java);
            var trainingManager = queryRest<TrainingInitiatorRelation>("trainingManager",TrainingInitiatorRelation::class.java);


            // create entities
            aptitudes?.forEach {

            }
        }

         private fun <BaseEntity> queryRest(entity_rest_name: String, clazz: Class<BaseEntity>): List<BaseEntity>?{
             val json = RestAPIRequestThread(entity_rest_name).start()

             when(clazz){
                 AptitudeEntity::class.java -> println("AptitudeEntity")
                 ContentEntity::class.java -> println("AptitudeEntity")
                 FormationEntity::class.java -> println("AptitudeEntity")
                 InitiatorEntity::class.java -> println("AptitudeEntity")
                 LevelEntity::class.java -> println("AptitudeEntity")
                 ParticipationEntity::class.java -> println("AptitudeEntity")
                 SessionEntity::class.java -> println("AptitudeEntity")
                 SkillEntity::class.java -> println("AptitudeEntity")
                 StatusEntity::class.java -> println("AptitudeEntity")
                 StudentEntity::class.java -> println("AptitudeEntity")

                 FormationSkillRelation::class.java -> println("AptitudeEntity")
                 TrainingInitiatorRelation::class.java -> println("AptitudeEntity")
                 else -> println("BaseEntity")
             }

             return null;
         }

        private fun insertAll(allEntities: List<List<BaseEntity>>) {
            for (entities_type in allEntities) {
                for (entity in entities_type) {

                }
            }
        }


        private fun RestAPIRequestThread(rest_entity_name: String) : JSONArray? {
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

                        println(decodedJsonArray)
                        return decodedJsonArray
                    } else {
                        println("Error: ${connection.responseCode}")
                    }
                } catch (e: Exception) {
                    println("Error: ${e.message}")
                } finally {
                    connection?.disconnect()
                }
            }.start()

            return null
        }

    }
}
