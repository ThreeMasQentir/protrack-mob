package org.gspi.protrack.common.webview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import com.techieroid.webviewapplication.WebViewHandler
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView
import platform.Foundation.*
import platform.WebKit.*
import platform.UIKit.*
import kotlinx.cinterop.ObjCAction
import platform.UIKit.UIView
import platform.WebKit.WKWebViewConfiguration
import platform.CoreGraphics.CGRectZero
import platform.UIKit.UIViewController
import platform.Foundation.*
import org.gspi.protrack.hello.HelloWorld
import org.gspi.protrack.webview.MapViewController


class IOSWebViewHandler : WebViewHandler {
    @OptIn(ExperimentalForeignApi::class)
    @Composable
    override fun LoadUrl(url: String) {

        UIKitView(
            factory = {
                val containerView = UIView()
                val mapViewController = MapViewController(url)

                containerView.addSubview(mapViewController.view)
                mapViewController.view.translatesAutoresizingMaskIntoConstraints = false

                NSLayoutConstraint.activateConstraints(
                    listOf(
                        mapViewController.view.leadingAnchor.constraintEqualToAnchor(containerView.leadingAnchor),
                        mapViewController.view.trailingAnchor.constraintEqualToAnchor(containerView.trailingAnchor),
                        mapViewController.view.topAnchor.constraintEqualToAnchor(containerView.topAnchor),
                        mapViewController.view.bottomAnchor.constraintEqualToAnchor(containerView.bottomAnchor)
                    )
                )
                containerView
            },
            modifier = Modifier.fillMaxSize()
        )

    }
}

