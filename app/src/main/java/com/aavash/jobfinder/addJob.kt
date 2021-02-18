package com.aavash.jobfinder

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import com.aavash.jobfinder.entity.User
import com.aavash.jobfinder.entity.job
import com.aavash.jobfinder.userRepository.jobRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class addJob : AppCompatActivity() {
    lateinit var etFullName : EditText
    lateinit var etAddress : EditText
    lateinit var etAge : EditText
    lateinit var rbMale : RadioButton
    lateinit var rbFemale : RadioButton
    lateinit var rbOthers : RadioButton
    lateinit var btnAdd : Button
    lateinit var imgAddImage : ImageView
    private var REQUEST_CAMERA_CODE  = 0
    private var REQUEST_GALLERY_CODE  = 1
    private var imageUrl: String?  = null
    var gender = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_job)
        etFullName = findViewById(R.id.etFullName)
        etAddress = findViewById(R.id.etAddress)
        etAge = findViewById(R.id.etAge)
        rbMale = findViewById(R.id.rbMale)
        rbFemale = findViewById(R.id.rbFemale)
        rbOthers = findViewById(R.id.rbOthers)
        btnAdd = findViewById(R.id.btnAdd)
        imgAddImage = findViewById(R.id.imgAddImage)
        btnAdd.setOnClickListener{
            when{
                rbMale.isChecked-> gender = rbMale.text.toString()
                rbFemale.isChecked-> gender = rbFemale.text.toString()
                rbOthers.isChecked-> gender = rbOthers.text.toString()
            }

            val job =
                job(fullname = etFullName.text.toString(), address = etAddress.text.toString(), age = etAge.text.toString().toInt(),gender = gender)
            CoroutineScope(Dispatchers.IO).launch {
                val repository = jobRepository()
                val response = repository.addJob(job)
                if(response.success == true){
                    if (imageUrl != null){
                        uploadImage(response.data!!._id!!)
                    }
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@addJob, "Success", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@addJob, "Error adding job", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        imgAddImage.setOnClickListener{
            loadPopUpMenu()
        }
    }

    private fun uploadImage(studentId: String) {
        if (imageUrl != null) {
            val file = File(imageUrl!!)
            val reqFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val body =
                MultipartBody.Part.createFormData("file", file.name, reqFile)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val jobRepository = jobRepository()
                    val response = jobRepository.uploadImage(studentId, body)
                    if (response.success==true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@addJob, "Uploaded", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.d("Error ", ex.localizedMessage)
                        Toast.makeText(
                            this@addJob,
                            ex.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){

            if (requestCode == REQUEST_GALLERY_CODE && data != null) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = contentResolver
                val cursor =
                    contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                imageUrl = cursor.getString(columnIndex)
                imgAddImage.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
                cursor.close()
            }
            else if (requestCode == REQUEST_CAMERA_CODE && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                imageUrl = file!!.absolutePath
                imgAddImage.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
            }
        }
    }

    private fun bitmapToFile(
        bitmap: Bitmap,
        fileNameToSave: String
    ): File? {
        var file: File? = null
        return try {
            file = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    .toString() + File.separator + fileNameToSave
            )
            file.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitMapData = bos.toByteArray()
            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }
    // Load pop up menu
    private fun loadPopUpMenu() {
        val popupMenu = PopupMenu(this@addJob, imgAddImage)
        popupMenu.menuInflater.inflate(R.menu.gallery_camera_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuCamera ->
                    openCamera()
                R.id.menuGallery ->
                    openGallery()
            }
            true
        }
        popupMenu.show()
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CAMERA_CODE)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/"
        startActivityForResult(intent,REQUEST_GALLERY_CODE)
    }
}
