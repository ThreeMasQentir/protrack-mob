package org.gspi.protrack.common.document

import android.content.Context
import android.content.Intent
import android.net.Uri
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

actual fun openOrDownloadPdf(url: String) {
    val pdfDownloader: PdfDownloader = PdfDownloadImpl()
    pdfDownloader.downloadPdf(url)
}

interface PdfDownloader {
    fun downloadPdf(url: String)
}

class PdfDownloadImpl : PdfDownloader, KoinComponent {
    private val context: Context by inject()

    override fun downloadPdf(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.setDataAndType(Uri.parse(url), "application/pdf")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}
