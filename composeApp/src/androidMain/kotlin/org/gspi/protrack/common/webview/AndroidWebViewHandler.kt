package org.gspi.protrack.common.webview

import android.annotation.SuppressLint
import android.app.Activity
import android.net.http.SslError
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
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
    fun WebView(url: String) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    this.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    settings.apply {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        allowFileAccess = true
                        mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW // Handle mixed content
                    }
                    webChromeClient = CustomWebChromeClient()
                    webViewClient = CustomWebViewClient() // Ensures all navigation stays in-app
                }
            },
            update = {
                it.loadUrl(url)
            }
        )
    }

    class CustomWebViewClient : WebViewClient() {
        // Ensures all navigation stays within the app
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view?.loadUrl(request?.url.toString())
            return true // Prevent external browser launch
        }

        @Deprecated("Deprecated in Java")
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            url?.let { view?.loadUrl(it) }
            return true // Prevent external browser launch
        }
    }

    class CustomWebChromeClient : WebChromeClient() {
        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
            consoleMessage?.message()?.let {
                Log.d("WebView", it)
            }
            return super.onConsoleMessage(consoleMessage)
        }

        override fun onCloseWindow(window: WebView?) {
            // Prevent the WebView from closing
            Log.d("WebView", "Attempted to close WebView window")
        }
    }
}