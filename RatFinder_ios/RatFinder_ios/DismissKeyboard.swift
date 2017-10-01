//
//  DismissKeyboard.swift
//  RatFinder_ios
//
//  Created by Kedar Rao on 9/29/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//

import UIKit
import Foundation

extension UIViewController
{
    
    func hideKeyboard()
    {
        let tap = UITapGestureRecognizer(target: self, action: #selector(UIViewController.dismissKeyboard))
        self.view.addGestureRecognizer(tap)
    }
    
    @objc func dismissKeyboard()
    {
        self.view.endEditing(true)
    }

}
