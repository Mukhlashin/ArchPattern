package com.example.archpatternandroid.utils

import okhttp3.MultipartBody
import okhttp3.RequestBody
import android.R
import android.content.Context
//import sun.security.krb5.internal.KDCOptions.with
import android.content.Intent
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File

 class MyFunction {

    fun toast(ctx: Context, message: String) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
    }

    fun longToast(ctx: Context, message: String) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
    }

    fun intent(ctx: Context, intent: Class<*>) {
        ctx.startActivity(Intent(ctx, intent))
    }

    fun displayPhotosPreview(ctx: Context, iv: ImageView, file: File) {
        Glide.with(ctx).load(file)
            .apply(
                RequestOptions().centerCrop().circleCrop()
                    .placeholder(R.mipmap.sym_def_app_icon)
            ).into(iv)
    }

    fun displayImagesOriginal(ctx: Context, iv: ImageView, url: String) {

    }

    fun displayImagesThumbnail(ctx: Context, iv: ImageView, url: String, thumbnail: Float) {

    }

    private val MULTIPART_FORM_DATA = "multipart/form-data"

    /**
     * Create request body for image resource
     *
     * @param file
     * @return
     */
    fun createRequestForImage(file: File): RequestBody {
        return RequestBody.create("image/*".toMediaTypeOrNull(), file)
    }

    /**
     * Create request body for file pdf resource
     *
     * @param file
     * @return
     */
    fun createRequestForFile(file: File): RequestBody {
        return RequestBody.create("application/pdf".toMediaTypeOrNull(), file)
    }

    /**
     * Create request body for string
     *
     * @param string
     * @return
     */
    fun createPartFromString(string: String): RequestBody {
        return RequestBody.create(MULTIPART_FORM_DATA.toMediaTypeOrNull(), string)
    }

    /**
     * return multipart part request body
     *
     * @param filePath
     * @return
     */
    fun createMultipartBody(filePath: String): MultipartBody.Part {
        val file = File(filePath)
        val requestBody = createRequestForImage(file)
        return MultipartBody.Part.createFormData("vsgambar", file.getName(), requestBody)
    }

}