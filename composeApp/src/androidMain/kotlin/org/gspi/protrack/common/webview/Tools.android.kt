package com.techieroid.webviewapplication

import org.gspi.protrack.common.webview.AndroidWebViewHandler

actual fun getWebViewHandler(): WebViewHandler {
    return AndroidWebViewHandler()
}