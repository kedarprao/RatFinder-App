//
//  SightingTableViewCell.swift
//  RatFinder_ios
//
//  Created by William Lim on 10/15/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//

import UIKit

class HomeTableViewCell: UITableViewCell {

    //MARK: Properties
    
    @IBOutlet weak var createdDate: UILabel!
    @IBOutlet weak var incidentAddress: UILabel!
    
    @IBOutlet weak var coordinates: UILabel!
    @IBOutlet weak var locationType: UILabel!
    @IBOutlet weak var borough: UILabel!
    @IBOutlet weak var city: UILabel!
    @IBOutlet weak var locationZip: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
