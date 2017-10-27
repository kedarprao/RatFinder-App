//
//  RatViewController.swift
//  RatFinder_ios
//
//  Created by Kedar Rao on 10/17/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//

import UIKit

class RatViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        self.hideKeyboard()
    }

    override func didReceiveMemoryWarning() {
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
    
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
