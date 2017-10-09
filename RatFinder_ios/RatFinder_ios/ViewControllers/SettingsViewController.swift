//
//  SettingsViewController.swift
//  RatFinder_ios
//
//  Created by Kedar Rao on 10/8/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//

import UIKit
import Firebase

class SettingsViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        self.hideKeyboard()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func logout(_ sender: Any) {
        try! Auth.auth().signOut()
        if let storyboard = self.storyboard {
            let vc = storyboard.instantiateViewController(withIdentifier: "Login")
            self.present(vc, animated: false, completion: nil)
        }
    }
}
