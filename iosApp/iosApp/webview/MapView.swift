import SwiftUI
import WebKit

struct WebView: UIViewRepresentable {
    let urlString: String
    
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

struct MapView: View {
    var body: some View {
        ZStack {
            WebView(urlString: "https://gspi-protrack.my.id/api/maps?id=7") // Replace with your desired URL
                .edgesIgnoringSafeArea(.all) // Extend WebView to cover the entire screen
        }
    }
}

struct MapView_Previews: PreviewProvider {
    static var previews: some View {
        MapView()
    }
}
