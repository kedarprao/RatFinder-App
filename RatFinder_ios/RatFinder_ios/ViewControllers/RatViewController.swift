//
//  RatViewController.swift
//  RatFinder_ios
//
//  Created by Kedar Rao on 10/17/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//

import UIKit
import Firebase

class RatViewController: UIViewController {

    @IBAction func cancelPressed(_ sender: Any)
    {
        dateTextField.text!  = ""
    }
    
    @IBAction func savePressed(_ sender: Any)
    {
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
    
    @IBOutlet weak var dateTextField: UITextField!
    
    @IBOutlet weak var boroughTextField: UITextField!
    
    @IBOutlet weak var locationTypeTextField: UITextField!
    
    @IBOutlet weak var addressTextField: UITextField!
    
    @IBOutlet weak var cityTextField: UITextField!

    @IBOutlet weak var zipTextField: UITextField!
    
    @IBOutlet weak var latitudeTextField: UITextField!
    
    @IBOutlet weak var longitudeTextField: UITextField!
    
    func writeToFirebase()
    {
        let refRats = Database.database().reference()
        let key = refRats.childByAutoId().key
        
        //creating artist with the given values
        let rat = ["Created date": dateTextField.text! as String,
                   "Borough": boroughTextField.text! as String,
                   "Location Type": locationTypeTextField.text! as String,
                   "Incident Address": addressTextField.text! as String,
                   "City": cityTextField.text! as String,
                   "Incident Zip": zipTextField.text! as String,
                   "Latitude": latitudeTextField.text! as String,
                   "Longitude": longitudeTextField.text! as String,
                   ]
        
        //adding the rat inside the generated unique key
        refRats.child(key).setValue(rat)
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
