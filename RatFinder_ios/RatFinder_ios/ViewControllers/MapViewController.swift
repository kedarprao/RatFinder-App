//
//  MapViewController.swift
//  RatFinder_ios
//
//  Created by Kedar Rao on 10/29/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//

import UIKit
import MapKit

class MapViewController: UIViewController
{
    
    @IBOutlet weak var mapView: MKMapView!
    
    var pins = [Pin]()
    let pinsKey = "pins"
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        mapView.delegate = self as? MKMapViewDelegate
        
        let longPressRecognizer = UILongPressGestureRecognizer(target: self, action: #selector(self.addPin))
        longPressRecognizer.minimumPressDuration = 0.5
        longPressRecognizer.delaysTouchesBegan = true
        mapView.addGestureRecognizer(longPressRecognizer)
        
        self.addSavedPins()
    }
    
    override func didReceiveMemoryWarning()
    {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func mapView(_ mapView: MKMapView, viewFor annotation: MKAnnotation) -> MKAnnotationView?
    {
        let identifier = "pin"
        if #available(iOS 11.0, *) {
            var annotationView = mapView.dequeueReusableAnnotationView(withIdentifier: identifier) as? MKMarkerAnnotationView
            if annotationView == nil {
                annotationView = MKMarkerAnnotationView(annotation: annotation, reuseIdentifier: identifier)
                annotationView?.canShowCallout = true
            } else {
                annotationView?.annotation = annotation
            }
            return annotationView
        } else {
            // Fallback on earlier versions
            return nil
        }
    }
    
    @objc func addPin(longGestureRecognizer: UILongPressGestureRecognizer)
    {
        if longGestureRecognizer.state != .ended {
            return
        }
        
        let location = longGestureRecognizer.location(in: self.mapView)
        let coordinate = self.mapView.convert(location, toCoordinateFrom: self.mapView)
        
        let pin = Pin(coordinate: coordinate)
        self.saveNewPin(pin)
        self.mapView.addAnnotation(pin)
    }
    
    func addSavedPins()
    {
        if let data = UserDefaults.standard.value(forKey: pinsKey) as? Data {
            if let savedPins = try? PropertyListDecoder().decode([Pin].self, from: data) {
                self.pins = savedPins
                self.mapView.addAnnotations(self.pins)
            }
        }
    }
    
    func saveNewPin(_ pin: Pin)
    {
        self.pins.append(pin)
        UserDefaults.standard.set(try? PropertyListEncoder().encode(self.pins), forKey: pinsKey)
    }
}


