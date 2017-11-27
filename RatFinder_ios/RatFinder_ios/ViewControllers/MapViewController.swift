//
//  MapViewController.swift
//  RatFinder_ios
//
//  Created by Kedar Rao on 10/29/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//

import UIKit
import MapKit
import Firebase

@available(iOS 11.0, *)
class MapViewController: UIViewController
{
    
    @IBOutlet weak var mapView: MKMapView!
    var sightingsList = [[String : String]]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        mapView.delegate = self as? MKMapViewDelegate
        getRats()
        self.addPins()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    func addPins() {
        for index in 0...25
        {
            let sighting = sightingsList[index]
            let createdDate = sighting["createdDate"]!
            let incidentAddress = sighting["incidentAddress"]!
            let latitude = Double(sighting["latitude"]!)!
            let longitude = Double(sighting["longitude"]!)!
            let coordinate = CLLocationCoordinate2D(latitude: latitude, longitude: longitude)
            let pin = Pin(title: createdDate, subtitle: incidentAddress, coordinate: coordinate)
            self.mapView.addAnnotation(pin)
        }
    }
    
    func getRats()
    {
        //Fetch values from FireBase
        let refRats = Database.database().reference()
        
        //refRats.observeSingleEvent(of: DataEventType.value, with: { (snapshot) in
        refRats.observe(DataEventType.value, with: { (snapshot) in
            
            //if the reference have some values
            if snapshot.childrenCount > 0 {
                print(snapshot.childrenCount)
                //clearing the list
                self.sightingsList.removeAll()
                
                //iterating through all the values
                for ratSighting in snapshot.children.allObjects as! [DataSnapshot] {
                    //getting values
                    let ratObject = ratSighting.value as? [String: String]
                    let createdDate = ratObject?["createdDate"]
                    let createdDateInt  = ratObject?["Created Date Int"]
                    let incidentAddress  = ratObject?["incidentAddress"]
                    let incidentZip = ratObject?["incidentZip"]
                    let city  = ratObject?["bity"]
                    let borough = ratObject?["borough"]
                    let locationType  = ratObject?["locationType"]
                    let latitude = ratObject?["latitude"]
                    let longitude = ratObject?["longitude"]
                    
                    //creating sighting object with model and fetched valu
                    let sighting = Sighting(createdDate: createdDate, createdDateInt: createdDateInt, incidentAddress: incidentAddress, locationType: locationType, incidentZip: incidentZip, city: city, borough: borough, latitude: latitude, longitude: longitude).toDictionary()
                    //appending it to dict
                    self.sightingsList.append(sighting as! [String : String])
                }
            }
        })
    }
}
