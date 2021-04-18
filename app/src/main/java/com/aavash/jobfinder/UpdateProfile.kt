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
import android.view.View
import android.widget.*
import com.aavash.jobfinder.api.ServiceBuilder
import com.aavash.jobfinder.entity.User
import com.aavash.jobfinder.userRepository.UserRepository
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class UpdateProfile : AppCompatActivity() {

    private lateinit var tvUpdateFirstName:TextView
    private lateinit var tvUpdateLastName:TextView
    private lateinit var tvUpdateAge:TextView
    private lateinit var tvUpdatePhone:TextView
    private lateinit var tvUpdateAddress:TextView
    private lateinit var tvUpdateExperience:TextView
    private lateinit var tvUpdateProjects:TextView
    private lateinit var tvUpdateUserbio:TextView
    private lateinit var tvUpdateEmail:TextView
    private lateinit var btnApplyEdit:Button
    private lateinit var imgUpdateProf:ImageView
    public  lateinit var id:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)



        tvUpdateUserbio = findViewById(R.id.tvUpdateUserbio)
        tvUpdatePhone = findViewById(R.id.tvUpdatePhone)
        tvUpdateAge = findViewById(R.id.tvUpdateAge)
        tvUpdateAddress =findViewById(R.id.tvUpdateAddress)
        tvUpdateEmail =findViewById(R.id.tvUpdateEmail)
        tvUpdateExperience =findViewById(R.id.tvUpdateExperience)
        tvUpdateProjects = findViewById(R.id.tvUpdateProjects)
        tvUpdateUserbio = findViewById(R.id.tvUpdateUserbio)
        btnApplyEdit = findViewById(R.id.btnApplyEdit)

        tvUpdateFirstName=findViewById(R.id.tvUpdateFirstName)
        tvUpdateLastName=findViewById(R.id.tvUpdateLastName)
        imgUpdateProf=findViewById(R.id.imgUpdateProf)
        btnApplyEdit=findViewById(R.id.btnApplyEdit)


        CoroutineScope(Dispatchers.Main).launch {


            val repository = UserRepository()
            val response = repository.getUser()




            id =response.data!!._id.toString()

            if (response.success==true) {

                tvUpdateFirstName.setText(response.data!!.firstname)
                tvUpdateLastName.setText(response.data!!.lastname)
                tvUpdateUserbio.setText(response.data!!.userbio)
                tvUpdateAddress.setText(response.data!!.address)
                tvUpdatePhone.setText(response.data!!.phone)
                tvUpdateEmail.setText(response.data!!.email)
                tvUpdateAge.setText(response.data!!.age)
                tvUpdateExperience.setText(response.data!!.experience)
                tvUpdateProjects.setText(response.data!!.projects)
                tvUpdateAddress.setText(response.data!!.address)

                val imagePath = ServiceBuilder.loadImagePath() + response.data.photo!!.replace("\\", "/");


                //load image with Glide library
                this@UpdateProfile?.let {
                    Glide.with(it)
                        .load(imagePath)
                        .into(imgUpdateProf)


                    if (imgUpdateProf != null) {
                        withContext(Main) {
                            Toast.makeText(this@UpdateProfile, "image uploading", Toast.LENGTH_LONG).show()
                        }
                        uploadImage(response.data!!._id!!)
                    }                }


            } else {
                withContext(Dispatchers.Main) {
//                    val snack =
//                        Snackbar.make(
//                            linearLayout,
//                            "Unexpected error",
//                            Snackbar.LENGTH_LONG
//                        )
//                    snack.setAction("OK", View.OnClickListener {
//                        snack.dismiss()
//
//                    })

//                    Toast.makeText(
//                        this@UpdateProfile,
//                        "Error", Toast.LENGTH_SHORT
//                    ).show()
                }
            }


        }

        imgUpdateProf.setOnClickListener {
            loadPopUpMenu()
        }

        btnApplyEdit.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {


                val repository = UserRepository()
                val firstname = tvUpdateFirstName.text.toString()
                val lastname = tvUpdateLastName.text.toString()
                val age = tvUpdateAge.text.toString()
                val address = tvUpdateAddress.text.toString()
                val phone = tvUpdatePhone.text.toString()
                val projects = tvUpdateProjects.text.toString()
                val experience = tvUpdateExperience.text.toString()
                val userbio = tvUpdateUserbio.text.toString()

                val response = repository.editUser(id,firstname,lastname,age,address,phone,projects,experience,userbio)


                if (response.success==true) {
                    Toast.makeText(
                        this@UpdateProfile,
                        response.data!!.firstname, Toast.LENGTH_SHORT
                    ).show()


                } else {
                    withContext(Dispatchers.Main) {
//                        val snack =
//                            Snackbar.make(
//                                linearLayout,
//                                "Unexpected error",
//                                Snackbar.LENGTH_LONG
//                            )
//                        snack.setAction("OK", View.OnClickListener {
//                            snack.dismiss()
//
//                        })

//                        Toast.makeText(
//                            this@UpdateProfile,
//                            "Error", Toast.LENGTH_SHORT
//                        ).show()
                    }
                }


            }


        }




    }

    private fun loadPopUpMenu() {
        val popupMenu = PopupMenu(this, imgUpdateProf)
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
    private fun uploadImage(studentId: String) {
        if (imageUrl != null) {
            val file = File(imageUrl!!)
            val reqFile =
                RequestBody.create(("image/"+file.extension.toLowerCase().replace("jpeg","jpg")).toMediaTypeOrNull(), file)
            val body =
                MultipartBody.Part.createFormData("photo", file.name, reqFile)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    ///upload code"nimag
                    val UserRepository = UserRepository()
                    val response = UserRepository.uploadImg(studentId,body)
                    if (response.success == true) {
                        //Update student with image path
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@UpdateProfile, "Uploaded", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.d("Mero Error ", ex.localizedMessage)
                        Toast.makeText(
                            this@UpdateProfile,
                            ex.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
    private var REQUEST_GALLERY_CODE = 0
    private var REQUEST_CAMERA_CODE = 1
    private var imageUrl: String? = null

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_CODE)
    }
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CAMERA_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_GALLERY_CODE && data != null) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = contentResolver
                val cursor =
                    contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                imageUrl = cursor.getString(columnIndex)
                imgUpdateProf.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
                cursor.close()
            } else if (requestCode == REQUEST_CAMERA_CODE && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                imageUrl = file!!.absolutePath
                imgUpdateProf.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
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
}