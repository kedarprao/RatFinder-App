//
//  Sighting.swift
//  RatFinder_ios
//
//  Created by William Lim on 10/15/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//

import UIKit

class Sighting {
    var createdDate: String
    var incidentAddress: String

    var children = [String: String]()
    
    
    init(csvLine: String) {
        var fields = csvLine.components(separatedBy: ",")
        self.createdDate = fields[1]
        self.incidentAddress = fields[9].isEmpty ? "Address not recorded" : fields[9]

        self.children["uniqueKey"] = fields[0]
        self.children["locationType"] = fields[7]
        self.children["incidentZip"] = fields[8]
        self.children["city"] = fields[16]
        self.children["borough"] = fields[23]
        self.children["latitude"] = fields[49]
        self.children["longitude"] = fields[50]
        
        
        
    }
    
}


