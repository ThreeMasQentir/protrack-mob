import SwiftUI
import WebKit

struct WebViewM: UIViewRepresentable {
    let urlString: String
    
    func makeUIView(context: Context) -> WKWebView {
        return WKWebView()
    }
    
    func updateUIView(_ uiView: WKWebView, context: Context) {
        if let url = URL(string: urlString) {
            let request = URLRequest(url: url)
            uiView.load(request)
        }
    }
}

struct WebViewContent: View {
    var body: some View {
        VStack {
            Text("WebView Example")
                .font(.headline)
                .padding()
            
            WebViewM(urlString: "https://www.google.com")
                .frame(height: 300) // Restrict WebView height to 300
                .cornerRadius(10) // Optional: Adds rounded corners
                .shadow(radius: 5) // Optional: Adds a shadow effect
            
            Spacer()
        }
        .padding()
    }
}

#Preview {
    WebViewContent()
}
