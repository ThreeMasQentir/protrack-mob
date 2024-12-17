//
//  MapViewController.swift
//  iosApp
//
//  Created by ARFDN on 16/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit
import SwiftUI

@objc public class MapViewController: UIViewController {

    @objc public override func viewDidLoad() {
        super.viewDidLoad()

        // Create the MapView with the message
        let mapView = MapView()

        // Embed MapView in a hosting controller
        let hostingController = UIHostingController(rootView: mapView)

        // Add the hosting controller as a child
        addChild(hostingController)
        view.addSubview(hostingController.view)
        hostingController.didMove(toParent: self)

        // Set constraints for the hosting controller
        hostingController.view.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            hostingController.view.topAnchor.constraint(equalTo: view.topAnchor),
            hostingController.view.bottomAnchor.constraint(equalTo: view.bottomAnchor),
            hostingController.view.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            hostingController.view.trailingAnchor.constraint(equalTo: view.trailingAnchor)
        ])
    }
}
