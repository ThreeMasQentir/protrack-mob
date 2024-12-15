package org.gspi.protrack.common.webview

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.UIKitView
import com.techieroid.webviewapplication.WebViewHandler
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration

class IOSWebViewHandler : WebViewHandler {

    // Create a WKWebView instance with a default configuration
    @OptIn(ExperimentalForeignApi::class)
    private val webView: WKWebView = WKWebView(
        frame = CGRectMake(0.0, 0.0, 0.0, 0.0),
        configuration = WKWebViewConfiguration()
    )

    @Composable
    override fun LoadUrl(url: String) {
        println("cekioswebview: LoadUrl called with url: $url")
        UIKitView(
            factory = {
                // Validate and create NSURL
                val nsUrl = NSURL(string = url)
                requireNotNull(nsUrl) { "Invalid URL: $url" }
                println("cekioswebview: NSURL created: $nsUrl")

                // Create NSURLRequest and load it in the WKWebView
                val request = NSURLRequest(nsUrl)
                webView.loadRequest(request)
                println("cekioswebview: NSURLRequest created and loaded: $request")

                // Return the WKWebView instance
                webView
            },
            update = {
                // Update the URL when the composable is recomposed
                val currentUrl = webView.URL?.absoluteString
                println("cekioswebview: Current URL: $currentUrl")
                if (currentUrl != url) {
                    val nsUrl = NSURL(string = url)
                    requireNotNull(nsUrl) { "Invalid URL: $url" }
                    val request = NSURLRequest(nsUrl)
                    it.loadRequest(request)
                    println("cekioswebview: URL updated and loaded: $url")
                }
            }
        )
    }
}