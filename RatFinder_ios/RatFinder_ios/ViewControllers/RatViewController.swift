//
//  RatViewController.swift
//  RatFinder_ios
//
//  Created by Kedar Rao on 10/17/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//

import UIKit
import Firebase
import CoreLocation

class RatViewController: UIViewController, CLLocationManagerDelegate {
    @IBOutlet weak var dateTextField: UITextField!
    
    @IBOutlet weak var boroughTextField: UITextField!
    
    @IBOutlet weak var locationTypeTextField: UITextField!
    
    @IBOutlet weak var addressTextField: UITextField!
    
    @IBOutlet weak var cityTextField: UITextField!

    @IBOutlet weak var zipTextField: UITextField!
    
    @IBOutlet weak var latitudeTextField: UITextField!
    
    @IBOutlet weak var longitudeTextField: UITextField!
    
    var locationManager: CLLocationManager!
    var dateString: String!
    
    func updateTextFields(location: CLLocation)
    {
        latitudeTextField.text! = "\(location.coordinate.latitude)"
        longitudeTextField.text! = "\(location.coordinate.longitude)"
        let formatter = DateFormatter()
        let currentDate = location.timestamp
        formatter.dateFormat = "mm/dd/yyyy"
        formatter.timeStyle = .none
        formatter.dateStyle = .short
        dateString = formatter.string(from: currentDate)
        dateTextField.text = dateString
    }
    
    func createdDateInt() -> String!
    {
        var createdDateInteger = "20"
        let dateStringReversed = dateString.split(separator: "/").reversed()
        var date = ""
        for str in dateStringReversed
        {
            date += str
            if date.count == 1
            {
                date = "0" + date
            }
            createdDateInteger += date
            date = ""
        }
        return createdDateInteger
    }
    
    @IBAction func cancelPressed(_ sender: Any)
    {
        dateTextField.text!  = ""
    }
    
    @IBAction func savePressed(_ sender: Any)
    {
        return
    }
    
    @IBAction func button(_ sender: Any) {
        writeToFirebase()
    }
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        self.hideKeyboard()
    }
    
    override func didReceiveMemoryWarning()
    {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        locationManager = CLLocationManager()
        locationManager.delegate = self
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        checkCoreLocationPermission()
    }
    
    func checkCoreLocationPermission()
    {
        if CLLocationManager.authorizationStatus() == .authorizedWhenInUse
        {
            locationManager.startUpdatingLocation()
        } else if CLLocationManager.authorizationStatus() == .notDetermined
        {
            locationManager.requestWhenInUseAuthorization()
        } else if CLLocationManager.authorizationStatus() == .restricted
        {
            print ("Unauthorized")
        }
    }
    
    // Mark: - CLLocationManagerDelegate
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        let location = (locations).last
        updateTextFields(location: location!)
        locationManager.stopUpdatingLocation()
    }
    
    func writeToFirebase()
    {
        let refRats = Database.database().reference()
        let key = refRats.childByAutoId().key
        guard let dateText = dateTextField.text, let boroughText = boroughTextField.text, let locationTypeText = locationTypeTextField.text, let addressText = addressTextField.text, let cityText = cityTextField.text, let zipText = zipTextField.text, let latitudeText = latitudeTextField.text, let longitudeText = longitudeTextField.text, let createdDateIntText = createdDateInt() else { return }
        //creating rat with the given values
        let rat: [String: Any] = Sighting.init(createdDate: dateText, createdDateInt: createdDateIntText, incidentAddress: addressText, locationType: locationTypeText, incidentZip: zipText, city: cityText, borough: boroughText, latitude: latitudeText, longitude: longitudeText).toDictionary()
    
        refRats.child(key).setValuesForKeys(rat)
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
