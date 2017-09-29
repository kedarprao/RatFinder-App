//
//  LoginViewController.swift
//  RatFinder_ios
//
//  Created by Kedar Rao on 9/18/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    @IBOutlet weak var usernameText: UITextField!
    
    @IBOutlet weak var passwordText: UITextField!
    
    @IBAction func loginButton(_ sender: UIButton) {
        if (usernameText.text != "admin" && passwordText.text != "password") {
            let alertView = UIAlertController(title: "Cannot Login" , message: "Incorrect Username or Password", preferredStyle: .alert)
            self.present(alertView, animated: true, completion: nil)
        }
    }
    
    @IBAction func cancelButton(_ sender: Any) {
    }
}

