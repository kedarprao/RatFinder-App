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
        self.hideKeyboard()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBOutlet weak var usernameText: UITextField!
    
    @IBOutlet weak var passwordText: UITextField!
    
    @IBAction func loginButton(_ sender: UIButton) {
        if (usernameText.text == "admin" && passwordText.text == "password") {
            self.performSegue(withIdentifier: "LoginToHomeSegue", sender: self)
        } else {
            let alertLogin = UIAlertController(title: "Cannot Login" , message: "Incorrect Username or Password", preferredStyle: .alert)
            let actionLogin = UIAlertAction(title: "Try Again", style: .default, handler: nil)
            alertLogin.addAction(actionLogin)
            self.present(alertLogin, animated: true, completion: nil)
        }
    }
    
}

