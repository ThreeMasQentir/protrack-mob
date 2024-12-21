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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
        // Use remember to force recomposition with a unique key
        var reloadKey by remember { mutableStateOf(0) }

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
                        mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                    }
                    webChromeClient = CustomWebChromeClient()
                    webViewClient = CustomWebViewClient()

                    // Clear cache and reload the initial URL
                    clearCache(true)
                    clearHistory()
                    loadUrl(url)
                }
            },
            update = { webView ->
                // On recomposition, clear the cache and reload
                webView.clearCache(true)
                webView.clearHistory()
                webView.loadUrl(url)
            },
            modifier = Modifier.clickable {
                // Optionally add a trigger to force reload manually
                reloadKey++
            }
        )
    }

    class CustomWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }

        @Deprecated("Deprecated in Java")
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            url?.let { view?.loadUrl(it) }
            return true
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
            Log.d("WebView", "Attempted to close WebView window")
        }
    }
}