package com.techieroid.webviewapplication

import org.gspi.protrack.common.webview.IOSWebViewHandler

actual fun getWebViewHandler(): WebViewHandler {
    return IOSWebViewHandler()
}