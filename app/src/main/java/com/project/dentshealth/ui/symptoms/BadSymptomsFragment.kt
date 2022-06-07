package com.project.dentshealth.ui.symptoms

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.project.dentshealth.ResultActivity
import com.project.dentshealth.databinding.FragmentBadSymptomsBinding
import com.project.dentshealth.utils.ModelClassifier
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class BadSymptomsFragment : Fragment() {
    private val max_len = 130
    private var interpreter: Interpreter? = null

    private lateinit var data: String
    private lateinit var binding: FragmentBadSymptomsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBadSymptomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val classifier = ModelClassifier( activity!!.applicationContext, "word_dict.json", max_len)
//
//        try {
//            interpreter = Interpreter(loadModelFile())
//        }catch (ex: Exception){
//            showText(ex.toString())
//        }
//
//        val progressDialog = ProgressDialog(activity!!.applicationContext)
//        progressDialog.setMessage( "Parsing word_dict.json ..." )
//        progressDialog.setCancelable( false )
//        progressDialog.show()
//        classifier.processVocab(object: ModelClassifier.VocabCallback {
//            override fun onVocabProcessed() {
//                progressDialog.dismiss()
//            }
//        })

        binding.tlUserSymptoms.setEndIconOnClickListener{
            data = binding.tiUserSymptoms.text.toString().toLowerCase().trim()
//            showText(data)
            val intent = Intent(activity, ResultActivity::class.java)
            intent.putExtra("SYMPTOMS", data)
            startActivity(intent)


//            if ( !TextUtils.isEmpty( data ) ){
//                // Tokenize and pad the given input text.
//                val tokenizedMessage = classifier.tokenize( data )
//                val paddedMessage = classifier.padSequence( tokenizedMessage )
//
//                val results = classifySequence( paddedMessage )
//                showText(results)
//            }
//            else{
//                showText("Please enter a message")
//            }
        }
    }

    private fun showText(text: String) {
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    }

    @Throws(IOException::class)
    private fun loadModelFile(): MappedByteBuffer {
        val assetFileDescriptor = context!!.assets.openFd("model_dh.tflite")
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun IntArray.argmax(): Int {
        return withIndex().maxByOrNull { it.value }!!.index
    }

    private fun classifySequence (sequence : IntArray ): String {
        // Input shape -> ( 1 , INPUT_MAXLEN )
//        val inputs : Array<FloatArray> = arrayOf( sequence.map { it.toFloat() }.toFloatArray() )
        // Output shape -> ( 1 , 2 ) ( as numClasses = 2 )
//        val outputs : Array<FloatArray> = arrayOf( FloatArray( 2 ) )
        val labels: ArrayList<String> = arrayListOf("Karies Gigi", "karang Gigi", "Abses Gigi", "Radang Gusi","Bukan Gejala Penyakit","Sariawan")
        val outputs = labels[sequence.argmax()]
        interpreter?.run( sequence , outputs )
        return outputs
    }
}