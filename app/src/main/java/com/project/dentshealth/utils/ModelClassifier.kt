package com.project.dentshealth.utils

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.IOException

class ModelClassifier(context: Context, filenameJson: String, maxLen : Int ) {
    private var context : Context? = context
    private var filename : String = filenameJson
    private var maxlen : Int = maxLen
    private var vocabData : HashMap<String , Int> = hashMapOf()

    private fun loadJSONFromAsset(filename : String? ): String? {
        val json: String?

        try {
            val inputStream = context!!.assets.open(filename.toString())
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun tokenize ( message : String ): IntArray {
        val parts : List<String> = message.split(" " )
        val tokenizedMessage = ArrayList<Int>()
        for ( part in parts ) {
            if (part.trim() != ""){
                var index: Int? = 0
                index = if ( vocabData[part] == null ) {
                    0
                } else{
                    vocabData[part]
                }
                tokenizedMessage.add( index!! )
            }
        }
        return tokenizedMessage.toIntArray()
    }

    fun padSequence ( sequence : IntArray ) : IntArray {
        val maxlen = this.maxlen
        if ( sequence.size > maxlen) {
            return sequence.sliceArray( 0..maxlen )
        }
        else if ( sequence.size < maxlen ) {
            val array = ArrayList<Int>()
            array.addAll( sequence.asList() )
            for ( i in array.size until maxlen ){
                array.add(0)
            }
            return array.toIntArray()
        }
        else{
            return sequence
        }
    }

    fun processVocab( callback: VocabCallback ) {
        CoroutineScope( Dispatchers.Main ).launch {
            loadVocab(callback , loadJSONFromAsset(filename)!! )
        }
    }

    interface VocabCallback {
        fun onVocabProcessed()
    }

    private fun loadVocab(callback : VocabCallback, json : String )  {
        with( Dispatchers.Default ) {
            val jsonObject = JSONObject( json )
            val iterator : Iterator<String> = jsonObject.keys()
            val data = HashMap< String , Int >()
            while ( iterator.hasNext() ) {
                val key = iterator.next()
                data[key] = jsonObject.get( key ) as Int
            }
            with( Dispatchers.Main ){
                vocabData = data
                callback.onVocabProcessed()
            }
        }
    }
}