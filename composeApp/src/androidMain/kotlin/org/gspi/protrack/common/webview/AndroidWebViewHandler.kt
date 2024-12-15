package org.gspi.protrack.common.webview

import android.annotation.SuppressLint
import android.app.Activity
import android.net.http.SslError
import android.provider.SyncStateContract.Helpers.update
import android.view.View
import android.view.ViewGroup
import android.webkit.ConsoleMessage
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.techieroid.webviewapplication.WebViewHandler
import org.gspi.protrack.R

class AndroidWebViewHandler : WebViewHandler {
    @SuppressLint("SetJavaScriptEnabled")
    @Composable
    override fun LoadUrl(url: String) {
        WebView(url)
    }

    @Composable
    fun WebView(url: String){
        AndroidView(factory = {
            WebView(it).apply {
                this.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.javaScriptEnabled = true
                this.webChromeClient = CustomWebChromeClient()
            }
        }, update = {
            it.loadUrl(url)
        })
    }

    class CustomWebChromeClient : WebChromeClient() {
        override fun onCloseWindow(window: WebView?) {}

        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
            return super.onConsoleMessage(consoleMessage)
        }
    }
}