package com.example.archpatternandroid.register

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.example.archpatternandroid.R
import com.example.archpatternandroid.images.ImagesContract
import com.example.archpatternandroid.images.ImagesPresenter
import com.example.archpatternandroid.login.LoginActivity
import com.example.archpatternandroid.networking.Injection
import com.example.archpatternandroid.repository.RegisterRepositoryImpl
import com.example.archpatternandroid.utils.displayPhotosPreview
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.activity_register_actiivty.*
import org.jetbrains.anko.*
import java.io.File
import java.io.IOException
import java.util.*

class RegisterActiivty : AppCompatActivity(), ImagesContract.View, RegisterContract.View {

    companion object {
        const val REQUEST_CAMERA = 0
        const val REQUEST_GALLERY: Int = 1

    }

    private lateinit var imagesPresenter: ImagesPresenter

    private lateinit var presenter: RegisterPresenter

    private lateinit var compressor: Compressor

    var filePicture: File? = null

    var level: String = ""

    val allPermission = arrayOf<String>(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_actiivty)

        initPresenter()
        initCompressImage()
        initRb()

        iv_profile.setOnClickListener { onShowDialogSelectedPhotos() }
        rb_admin.setOnClickListener { level = "Admin" }
        rb_user.setOnClickListener { level = "User" }
        btn_register.setOnClickListener {
            presenter.regis(edt_nama.text.toString(), edt_email.text.toString(), edt_password.text.toString(), level, imagesPresenter.getPhotos())

        }
        tv_login.setOnClickListener { startActivity<LoginActivity>() }
    }

    private fun initPresenter() {
        val service = Injection.provideApiService()
        val repository = RegisterRepositoryImpl(service)
        presenter = RegisterPresenter(repository)
        imagesPresenter = ImagesPresenter(this)
    }

    private fun initCompressImage() {
        compressor = Compressor(this)
    }

    private fun initRb() {
        level = if (rb_admin.isChecked) {
            "Admin"
        } else {
            "User"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RegisterActiivty.REQUEST_CAMERA && resultCode == Activity.RESULT_OK)
            try {
                val file = Compressor(this).compressToFile(filePicture)
                imagesPresenter.savePhotos(file.path)
                imagesPresenter.showPreview(file)
            } catch (e: IOException) {
                e.printStackTrace()
            } else if (requestCode == RegisterActiivty.REQUEST_GALLERY && resultCode == Activity.RESULT_OK) {
            val uri = data?.getData()
            try {
                val file = Compressor(this).compressToFile(File(getRealPathFromUri(uri!!)))
                imagesPresenter.savePhotos(file.path)
                imagesPresenter.showPreview(file)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDettachView()
    }

    override fun checkSelfPermission(): Boolean {
        for (permission in allPermission) {
            val result = ActivityCompat.checkSelfPermission(this, permission)
            if (result == PackageManager.PERMISSION_DENIED) return false
        }
        return true
    }

    override fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }

    override fun onShowPermissionDialog(isGallery: Boolean) {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report!!.areAllPermissionsGranted()) {
                        if (isGallery) {
                            imagesPresenter.selectPhotos()
                        } else {
                            imagesPresenter.takePhotos()
                        }
                    }
                    if (report.isAnyPermissionPermanentlyDenied)
                        alert("Coba allow permissionnya ya") {
                            yesButton {
                                it.cancel()
                                openSettings()
                            }
                            noButton {
                                it.cancel()
                            }
                        }.show()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token!!.continuePermissionRequest()
                }

            })
            .withErrorListener { onShowErrorDialog() }
            .onSameThread()
            .check()
    }

    override fun takePhotosView(file: File?) {
        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePicture.resolveActivity(packageManager) != null) {
            if (file != null) {
                val mPhotoUri = FileProvider.getUriForFile(this, "com.example.archpatternandroid.fileprovider", file)
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri)
                filePicture = file
                startActivityForResult(takePicture, RegisterActiivty.REQUEST_CAMERA)
            } else {
                Log.d("TAG", "File null")
            }
        } else {
            toast("Can't take photos")
        }
    }

    override fun selectPhotosView() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivityForResult(intent, RegisterActiivty.REQUEST_GALLERY)
    }

    override fun onShowPhotosPreview(file: File) {
        displayPhotosPreview(this, iv_profile, file)

    }

    override fun onShowDialogSelectedPhotos() {
        val items = arrayOf("Take photos", "Choose from library", "Cancel")
        val builder = AlertDialog.Builder(this)
        builder.setItems(items, { dialog, item ->
            if (items[item] == "Take photos") {
                imagesPresenter.takePhotos()
            } else if (items[item] == "Choose from library") {
                imagesPresenter.selectPhotos()
            } else if (items[item] == "Cancel") {
                dialog.dismiss()
            }
        })
        builder.show()
    }

    override fun onShowErrorDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRealPathFromUri(uri: Uri): String {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = contentResolver.query(uri, proj, null, null, null)
            assert(cursor != null)
            var columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(columnIndex)
        } finally {
            if (cursor != null) {
                cursor.close()
            } else {
                Log.d("TAG", "Cursor Null")
            }
        }
    }

    override fun newFile(): File? {
        val cal = Calendar.getInstance()
        val timeInMillis = cal.getTimeInMillis()
        val mFileName = timeInMillis.toString() + ".jpeg"
        val filePath = getFilePath()

        try {
            val newFile = File(filePath?.getAbsolutePath(), mFileName)
            newFile.createNewFile()
            return newFile
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    override fun getFilePath(): File? {
        return getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    }

    override fun isEmpty() {
        toast("gak boleh kosong")
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

}