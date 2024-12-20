package org.gspi.protrack.common.document

import platform.Foundation.*
import platform.UIKit.*

actual fun openOrDownloadPdf(url: String) {
    val nsUrl = NSURL(string = url)
    if (nsUrl != null && UIApplication.sharedApplication.canOpenURL(nsUrl)) {
        UIApplication.sharedApplication.openURL(nsUrl)
    }
}