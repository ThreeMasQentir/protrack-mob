//
//  CustomSwiftUIView.swift
//  iosApp
//
//  Created by ARFDN on 06/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct CustomSwiftUIView: View {
    var body: some View {
        VStack {
            Text("Ini Custom View di iOS")
                .font(.title)
                .foregroundColor(.blue)
                .padding()

            Rectangle()
                .fill(Color.green)
                .frame(height: 100)
        }
    }
}

// Preview untuk CustomSwiftUIView
struct CustomSwiftUIView_Previews: PreviewProvider {
    static var previews: some View {
        // Preview utama
        CustomSwiftUIView()
            .previewLayout(.sizeThatFits) // Ukuran fleksibel berdasarkan isi
            .padding() // Tambahkan padding untuk preview
            .previewDisplayName("Default Preview")
    }
}
