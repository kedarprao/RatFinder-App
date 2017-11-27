//
//  Pin.swift
//  RatFinder_ios
//
//  Created by Kedar Rao on 10/29/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//

import UIKit
import MapKit

class Pin: NSObject, MKAnnotation, Codable {
    var date: String
    var address: String
    var latitude: Double
    var longitude: Double

    init(title: String, subtitle: String, coordinate: CLLocationCoordinate2D) {
        self.date = title
        self.address = subtitle
        self.longitude = coordinate.longitude
        self.latitude = coordinate.latitude
    }

    // MKAnnotation

    public var coordinate: CLLocationCoordinate2D {
        return CLLocationCoordinate2D(latitude: self.latitude, longitude: self.longitude)
    }

    public var title: String? {
        return self.date
    }

    public var subtitle: String? {
        return self.address
    }
}


