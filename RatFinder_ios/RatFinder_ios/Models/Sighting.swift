//
//  Sighting.swift
//  RatFinder_ios
//
//  Created by William Lim on 10/15/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//

import UIKit

class Sighting {
    var createdDate: String?
    var incidentAddress: String?
    var locationType: String?
    var incidentZip: String?
    var city: String?
    var borough: String?
    var latitude: String?
    var longitude: String?
    
    init(createdDate: String?, incidentAddress: String?)
    {
        self.createdDate = createdDate
        self.incidentAddress = incidentAddress
    }
    
    init(createdDate: String?, incidentAddress: String?, locationType: String?,
         incidentZip: String?, city: String?, borough: String?, latitude: String?,
         longitude: String?)
    {
        self.createdDate = createdDate
        self.incidentAddress = incidentAddress
        self.locationType = locationType
        self.incidentAddress = incidentAddress
        self.city = city
        self.borough = borough
        self.latitude = latitude
        self.longitude = longitude
    }

//    var children = [String: String]()
//
//    init (createdDate: String, incidentAddress: String, children: [String: String]) {
//        self.createdDate = createdDate
//        self.incidentAddress = incidentAddress
//        self.children = children
//    }
//
//    init(csvLine: String) {
//        var fields = csvLine.components(separatedBy: ",")
//        self.createdDate = fields[1]
//        self.incidentAddress = fields[9].isEmpty ? "Address not recorded" : fields[9]
//
//        self.children["uniqueKey"] = fields[0].isEmpty ? "Unqiue Key not recorded" : fields[0]
//        self.children["locationType"] = fields[7].isEmpty ? "Location Type not recorded" : fields[7]
//        self.children["incidentZip"] = fields[8].isEmpty ? "Incident Zip not recorded" : fields[8]
//        self.children["city"] = fields[16].isEmpty ? "City not recorded" : fields[16]
//        self.children["borough"] = fields[23].isEmpty ? "Borough not recorded" : fields[23]
//        self.children["latitude"] = fields[49].isEmpty ? "Latitude not recorded" : fields[49]
//        self.children["longitude"] = fields[50].isEmpty ? "Longitude not recorded" : fields[50]
//
//
//
//    }
    
}


