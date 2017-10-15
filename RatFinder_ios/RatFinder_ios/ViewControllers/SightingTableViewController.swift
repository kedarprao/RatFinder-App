//
//  SightingTableViewController.swift
//  RatFinder_ios
//
//  Created by William Lim on 10/15/17.
//  Copyright © 2017 Kedar Rao. All rights reserved.
//
import UIKit

class SightingTableViewController: UITableViewController {
    
    //MARK: Properties
    var sightings = [Sighting]()
    let csvFileName = "Rat_Sightings_SHORTENED"
    let maxSightingsDisplayed = 15
    
    //Mark: Private Methods
    
    
    private func loadCsvSightings() {

        if let path = Bundle.main.path(forResource: csvFileName, ofType: "csv") {
            print(path)
            do {
                let csvFileContents = try String(contentsOfFile: path)
                let lines = csvFileContents.components(separatedBy: "\n")
                
                for (index, line) in lines.enumerated() {
                    if index != 0 && index <= maxSightingsDisplayed {
                        sightings.append(Sighting(csvLine: line))
                        
                    }
                }
                
            }
            catch {/* error handling here */}
            
            
            
            
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        
        loadCsvSightings()
        

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return sightings.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cellIdentifier = "SightingTableViewCell"

        guard let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as? SightingTableViewCell  else {
            fatalError("The dequeued cell is not an instance of SightingTableViewCell.")
        }

        let sighting = sightings[indexPath.row]

        cell.createdDate.text = sighting.createdDate
        cell.incidentAddress.text = sighting.incidentAddress

        return cell
    }
    

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
