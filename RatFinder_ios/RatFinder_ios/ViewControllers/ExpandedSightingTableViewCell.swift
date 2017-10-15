//
//  ExpandedSightingTableViewCell.swift
//  RatFinder_ios
//
//  Created by William Lim on 10/15/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//

import UIKit

class ExpandedSightingTableViewCell: UITableViewCell {

    @IBOutlet weak var coordinates: UILabel!
    @IBOutlet weak var borough: UILabel!
    @IBOutlet weak var city: UILabel!
    @IBOutlet weak var incidentZip: UILabel!
    @IBOutlet weak var locationType: UILabel!
    @IBOutlet weak var uniqueKey: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
