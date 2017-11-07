//
//  SightingTableViewController.swift
//  RatFinder_ios
//
//  Created by William Lim on 10/15/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//
import UIKit
import Firebase



class SightingTableViewController: UITableViewController
{
    
    //MARK: Properties
    var sightingsList = [[String : String]]()

    //Mark: Private Methods
    override func viewDidLoad()
    {
        super.viewDidLoad()
        
        getRats() // goes to firebase and gets Rats
    }

    func getRats()
    {
        //Fetch values from FireBase
        let refRats = Database.database().reference()
        
        //refRats.observeSingleEvent(of: DataEventType.value, with: { (snapshot) in
        refRats.observe(DataEventType.value, with: { (snapshot) in
            
            //if the reference have some values
            if snapshot.childrenCount > 0 {
                print(snapshot.childrenCount)
                //clearing the list
                self.sightingsList.removeAll()
                
                //iterating through all the values
                for ratSighting in snapshot.children.allObjects as! [DataSnapshot] {
                    //getting values
                    let ratObject = ratSighting.value as? [String: String]
                    let createdDate = ratObject?["createdDate"]
                    let createdDateInt  = ratObject?["Created Date Int"]
                    let incidentAddress  = ratObject?["incidentAddress"]
                    let incidentZip = ratObject?["incidentZip"]
                    let city  = ratObject?["bity"]
                    let borough = ratObject?["borough"]
                    let locationType  = ratObject?["locationType"]
                    let latitude = ratObject?["latitude"]
                    let longitude = ratObject?["longitude"]
                    
                    //creating sighting object with model and fetched valu
                    let sighting = Sighting(createdDate: createdDate, createdDateInt: createdDateInt, incidentAddress: incidentAddress, locationType: locationType, incidentZip: incidentZip, city: city, borough: borough, latitude: latitude, longitude: longitude).toDictionary()
                    //appending it to dict
                    self.sightingsList.append(sighting as! [String : String])
                }
                //reloading the tableview
                self.tableView.reloadData()
            }
        })
    }
    
    override func didReceiveMemoryWarning()
    {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Table view data source
    
    override func scrollViewDidEndDragging(_ scrollView: UIScrollView, willDecelerate decelerate: Bool)
    {
        let currentOffset = scrollView.contentOffset.y
        let maxOffset = scrollView.contentSize.height - scrollView.frame.size.height
        
        if maxOffset - currentOffset < 40 {
            getRats()
        }
    }

    override func numberOfSections(in tableView: UITableView) -> Int
    {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return sightingsList.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cellIdentifier = "SightingTableViewCell"

        guard let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as? SightingTableViewCell  else {
            fatalError("The dequeued cell is not an instance of SightingTableViewCell.")
        }
        
        //getting the sighting of selected position
        let sighting = sightingsList[indexPath.row]
        //adding values to labels
        cell.createdDate.text = (sighting["createdDate"])
        cell.incidentAddress.text = (sighting["incidentAddress"])
        return cell
    }

}
