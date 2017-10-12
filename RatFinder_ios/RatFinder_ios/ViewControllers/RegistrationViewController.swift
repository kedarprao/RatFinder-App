//
//  RegistrationViewController.swift
//  RatFinder_ios
//
//  Created by Kedar Rao on 9/25/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//

import UIKit
import Firebase

class RegistrationViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        self.hideKeyboard()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBOutlet weak var emailText: UITextField!
    @IBOutlet var passwordText: UITextField!
    @IBOutlet var confirmPasswordText: UITextField!
    
    func isPasswordValid(_ password : String) -> Bool{
        let passwordTest = NSPredicate(format: "SELF MATCHES %@", "^(?=.*[a-z])(?=.*[$@$#!%*?&])[A-Za-z\\d$@$#!%*?&]{8,}")
        return passwordTest.evaluate(with: password)
    }
    
    @IBAction func createANewUser(_ sender: UIButton) {
        guard let email = emailText.text, let password = passwordText.text, let confirmPassword = confirmPasswordText.text else { return }
        if isPasswordValid(password) && password == confirmPassword {
            Auth.auth().createUser(withEmail: email, password: password) { (user, error) in
                if let error = error {
                    print(error.localizedDescription)
                }
            }
            self.performSegue(withIdentifier: "RegisterToHomeSegue", sender: self)
        }
        else {
            let alertLogin = UIAlertController(title: "Your passwod must contain a character from the list $@$#!%?& and be longer than 8 characters" , message: "Missing characters", preferredStyle: .alert)
            let actionLogin = UIAlertAction(title: "Try Again", style: .default, handler: nil)
            alertLogin.addAction(actionLogin)
            self.present(alertLogin, animated: true, completion: nil)
        }
    }
}



