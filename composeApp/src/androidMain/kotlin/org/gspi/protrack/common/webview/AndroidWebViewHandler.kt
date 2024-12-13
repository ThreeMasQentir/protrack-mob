package org.gspi.protrack.common.webview

import android.annotation.SuppressLint
import android.net.http.SslError
import android.provider.SyncStateContract.Helpers.update
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
import androidx.compose.ui.viewinterop.AndroidView
import com.techieroid.webviewapplication.WebViewHandler

class AndroidWebViewHandler : WebViewHandler {
    @SuppressLint("SetJavaScriptEnabled")
    @Composable
    override fun LoadUrl(url: String) {
        println("loadurlcek: Initializing WebView for URL: $url")

        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue), // Add a background to ensure WebView is visible
            factory = { context ->
                println("loadurlcek: Creating WebView instance")
                WebView(context).apply {
                    settings.apply {
                        javaScriptEnabled = true
                        domStorageEnabled = true // Enable DOM storage
                        builtInZoomControls = true
                        displayZoomControls = true
                        useWideViewPort = true
                        loadWithOverviewMode = true
                        mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
                        settings.userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"                    }

                    println("loadurlcek: Setting WebViewClient and WebChromeClient")
                    webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                            println("loadurlcek: Intercepted URL: ${request?.url}")
                            return false // Ensure the WebView handles the URL
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            println("loadurlcek: Page finished loading: $url")
                            // Evaluate the page content for debugging
                            view?.evaluateJavascript("(function() { return document.body.innerHTML; })();") { html ->
                                println("loadurlcek: Page HTML content: $html")
                            }
                        }

                        override fun onReceivedSslError(
                            view: WebView?,
                            handler: SslErrorHandler?,
                            error: SslError?
                        ) {
                            println("loadurlcek: SSL error encountered: ${error?.toString()}")
                            handler?.proceed() // Ignore SSL errors for debugging purposes
                        }
                    }

                    webChromeClient = object : WebChromeClient() {
                        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                            println("loadurlcek: JS Console: ${consoleMessage?.message()}")
                            return super.onConsoleMessage(consoleMessage)
                        }
                    }

                    println("loadurlcek: Loading URL: $url")
                    loadUrl(url)
                }
            },
            update = { webView ->
                println("loadurlcek: Updating WebView with URL: $url")
                webView.loadUrl(url)
            }
        )
    }
}