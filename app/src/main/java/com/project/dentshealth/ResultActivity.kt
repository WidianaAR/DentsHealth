package com.project.dentshealth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager
import com.google.firebase.ml.custom.FirebaseCustomLocalModel
import com.google.firebase.ml.custom.FirebaseCustomRemoteModel
import com.google.firebase.ml.custom.FirebaseModelDataType
import com.google.firebase.ml.custom.FirebaseModelInputOutputOptions
import com.google.firebase.ml.custom.FirebaseModelInputs
import com.google.firebase.ml.custom.FirebaseModelInterpreter
import com.google.firebase.ml.custom.FirebaseModelInterpreterOptions
import com.project.dentshealth.databinding.ActivityResultBinding
import com.project.dentshealth.utils.ModelClassifier
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class ResultActivity : AppCompatActivity() {
    private val max_len = 130

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val classifier = ModelClassifier( this, "word_dict.json", max_len)
//
//        try {
//            interpreter = Interpreter(loadModelFile())
//        }catch (ex: Exception){
//            showText(ex.toString())
//        }
//
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage( "Parsing word_dict.json ..." )
        progressDialog.setCancelable( false )
        progressDialog.show()
        classifier.processVocab(object: ModelClassifier.VocabCallback {
            override fun onVocabProcessed() {
                progressDialog.dismiss()
            }
        })

//        val data = intent.getStringExtra("SYMPTOMS").toString()
//        showText(data)

//        val localModel = FirebaseCustomLocalModel.Builder()
//            .setAssetFilePath("model_dh.tflite")
//            .build()

//        val options = FirebaseModelInterpreterOptions.Builder(localModel).build()
//        val interpreter = FirebaseModelInterpreter.getInstance(options)

        val remoteModel = FirebaseCustomRemoteModel.Builder("model_dh").build()
        val condition = FirebaseModelDownloadConditions.Builder()
            .requireWifi()
            .build()
//
        FirebaseModelManager.getInstance().download(remoteModel, condition)

        val options = FirebaseModelInterpreterOptions.Builder(remoteModel).build()
        val interpreter = FirebaseModelInterpreter.getInstance(options)

//        FirebaseModelManager.getInstance().isModelDownloaded(remoteModel)
//            .addOnSuccessListener { isDownloaded ->
//                val options =
//                    if (isDownloaded) {
//                        FirebaseModelInterpreterOptions.Builder(remoteModel).build()
//                    } else {
//                        FirebaseModelInterpreterOptions.Builder(localModel).build()
//                    }
//                val interpreter = FirebaseModelInterpreter.getInstance(options)
//            }

        val inputOutputOptions = FirebaseModelInputOutputOptions.Builder()
            .setInputFormat(0, FirebaseModelDataType.FLOAT32, intArrayOf(1, 130))
            .setOutputFormat(0, FirebaseModelDataType.FLOAT32, intArrayOf(1, 6))
            .build()

        val data = "Ada lapisan kasar dan keras di garis gusi saya"
        data.toLowerCase().trim()

        if ( !TextUtils.isEmpty(data) ){
            // Tokenize and pad the given input text.
            val tokenizedMessage = classifier.tokenize(data)
            val paddedMessage = classifier.padSequence(tokenizedMessage)

            val inputs = FirebaseModelInputs.Builder()
                .add(paddedMessage)
                .build()

            interpreter!!.run(inputs, inputOutputOptions)
                .addOnSuccessListener { result ->
                    val output = result.getOutput<Array<FloatArray>>(0)
                    val labels: ArrayList<String> = arrayListOf("Karies Gigi", "karang Gigi", "Abses Gigi", "Radang Gusi","Bukan Gejala Penyakit","Sariawan")
                    showText(output.toString())
                }
        }
        else{
            showText("Data Kosong")
        }
    }

    private fun showText(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

//    @Throws(IOException::class)
//    private fun loadModelFile(): MappedByteBuffer {
//        val assetFileDescriptor = assets.openFd("model_dh.tflite")
//        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
//        val fileChannel = fileInputStream.channel
//        val startOffset = assetFileDescriptor.startOffset
//        val declaredLength = assetFileDescriptor.declaredLength
//        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
//    }

    fun <T : Comparable<T>> Array<T>.argmax(): Int? {
        return withIndex().maxByOrNull { it.value }?.index
    }

//    private fun classifySequence (sequence : IntArray ): String {
//        // Input shape -> ( 1 , INPUT_MAXLEN )
////        val inputs : Array<FloatArray> = arrayOf( sequence.map { it.toFloat() }.toFloatArray() )
//        // Output shape -> ( 1 , 2 ) ( as numClasses = 2 )
////        val outputs : Array<FloatArray> = arrayOf( FloatArray( 2 ) )
//        val labels: ArrayList<String> = arrayListOf("Karies Gigi", "karang Gigi", "Abses Gigi", "Radang Gusi","Bukan Gejala Penyakit","Sariawan")
//        val inputs = sequence
//        val outputs = labels[inputs.argmax()]
//        interpreter?.run( inputs, outputs )
//        return outputs
//    }
}