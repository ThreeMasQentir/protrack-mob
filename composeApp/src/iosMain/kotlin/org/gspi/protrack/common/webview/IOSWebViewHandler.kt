package org.gspi.protrack.common.webview

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.UIKitView
import com.techieroid.webviewapplication.WebViewHandler
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration

class IOSWebViewHandler : WebViewHandler {

    @OptIn(ExperimentalForeignApi::class)
    private val webView: WKWebView = WKWebView(frame = CGRectZero.readValue(), configuration = WKWebViewConfiguration())

    @OptIn(ExperimentalForeignApi::class)
    @Composable
    override fun LoadUrl(url: String) {
        UIKitView(
            factory = {
                val nsUrl = NSURL(string = url)
                requireNotNull(nsUrl) { "Invalid URL: $url" }
                val request = NSURLRequest(nsUrl)

                // Use the class-level WKWebView instance
                webView.loadRequest(request)
                webView
            }
        )
    }

}