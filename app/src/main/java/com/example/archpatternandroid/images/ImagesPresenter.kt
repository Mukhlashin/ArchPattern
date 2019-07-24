package com.example.archpatternandroid.images

import java.io.File

class ImagesPresenter(var photosView: ImagesContract.View) : ImagesContract.Presenter {

    var selectedFile: String? = null

    override fun takePhotos() {
        if (!photosView.checkSelfPermission()) {
            photosView.onShowPermissionDialog(false)
            return
        }

        val file = photosView.newFile()

        if (file == null) {
            photosView.onShowErrorDialog()
            return
        }
        photosView.takePhotosView(file)
    }

    override fun selectPhotos() {
        if (!photosView.checkSelfPermission()) {
            photosView.onShowPermissionDialog(true)
            return
        }
        photosView.selectPhotosView()
    }

    override fun showPreview(file: File) {
        photosView.onShowPhotosPreview(file)
    }

    override fun savePhotos(filePath: String) {
        selectedFile = filePath
    }

    override fun getPhotos(): String {
        return selectedFile!!
    }


}