import SwiftUI
import WebKit
import SwiftUI
import WebKit

// WebView: A UIViewRepresentable wrapper for WKWebView
struct WebView: UIViewRepresentable {
    let urlString: String // Dynamic URL property
    
    func makeUIView(context: Context) -> WKWebView {
        WKWebView()
    }
    
    func updateUIView(_ uiView: WKWebView, context: Context) {
        if let url = URL(string: urlString) {
            let request = URLRequest(url: url)
            uiView.load(request)
        }
    }
}

// MapView: Main View to display the WebView
struct MapView: View {
    let urlString: String // Dynamic URL property
    
    var body: some View {
        ZStack {
            WebView(urlString: urlString) // Use dynamic URL here
                .edgesIgnoringSafeArea(.all) // Extend WebView to cover the entire screen
        }
    }
}

// Preview with a sample dynamic URL
struct MapView_Previews: PreviewProvider {
    static var previews: some View {
        MapView(urlString: "https://gspi-protrack.my.id/api/maps?id=7")
    }
}
