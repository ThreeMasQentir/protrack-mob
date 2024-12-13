package org.gspi.protrack.common.media

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readBytes
import org.gspi.protrack.common.picker.AllowedFileType
import org.gspi.protrack.common.picker.FilePickResult
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIDocumentPickerDelegateProtocol
import platform.UIKit.UIDocumentPickerMode
import platform.UIKit.UIDocumentPickerViewController
import platform.darwin.NSObject
import platform.Foundation.NSData
import platform.Foundation.dataWithContentsOfURL



@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun LaunchFilePicker(
    allowedType: AllowedFileType,
    onResult: (FilePickResult) -> Unit,
    content: @Composable ((() -> Unit) -> Unit)
) {

    val allowedUTIs = when (allowedType) {
        AllowedFileType.ANY -> listOf("public.item")
        AllowedFileType.ZIP -> listOf("public.zip-archive")
        AllowedFileType.PDF -> listOf("com.adobe.pdf")
    }
    val context = remember { UIApplication.sharedApplication.keyWindow?.rootViewController }

    content {
        val documentPicker = UIDocumentPickerViewController(
            documentTypes = allowedUTIs,
            UIDocumentPickerMode.UIDocumentPickerModeImport
        )

        documentPicker.delegate = object : NSObject(), UIDocumentPickerDelegateProtocol {
            override fun documentPicker(
                controller: UIDocumentPickerViewController,
                didPickDocumentAtURL: NSURL
            ) {
                try {
                    val fileData: NSData? = NSData.dataWithContentsOfURL(didPickDocumentAtURL)
                    val byteArray = fileData?.let { it.bytes?.readBytes(it.length.toInt()) }
                    onResult(FilePickResult.Success(data = byteArray))
                } catch (e: Exception) {
                    // Pass error result
                    onResult(FilePickResult.Error(message = "Error reading file: ${e.message}"))
                }
            }
            override fun documentPickerWasCancelled(controller: UIDocumentPickerViewController) {
                // Handle cancellation
                onResult(FilePickResult.Cancelled)
            }
        }

        context?.presentViewController(documentPicker, animated = true, completion = null)
    }
}





