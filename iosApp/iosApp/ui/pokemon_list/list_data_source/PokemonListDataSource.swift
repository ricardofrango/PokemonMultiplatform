//
//  PokemonListDataSource.swift
//  iosApp
//
//  Created by Ricardo Frango on 27/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import pokemon_domain
import UIKit

class PokemonListDataSource : NSObject, UITableViewDelegate, UITableViewDataSource {
    
    private var pokemonsList : [PokemonItemModel] = []
    
    let tableview: UITableView = {
        let tv = UITableView()
        tv.backgroundColor = UIColor.white
        tv.translatesAutoresizingMaskIntoConstraints = false
        //tv.separatorColor = UIColor.white
        return tv
    }()
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return pokemonsList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableview.dequeueReusableCell(withIdentifier: "cellId", for: indexPath) as! PokemonRowCell
        cell.backgroundColor = UIColor.white
        cell.dayLabel.text = pokemonsList[indexPath.row].name
        downloadImage(uiImageView: cell.imageUiView, from: URL.init(string: pokemonsList[indexPath.row].imageUrl)!)
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 75
    }
    
    func downloadImage(uiImageView : UIImageView, from url: URL) {
        getData(from: url) { data, response, error in
            guard let data = data, error == nil else { return }
            //print(response?.suggestedFilename ?? url.lastPathComponent)
            DispatchQueue.main.async() { [weak self] in
                uiImageView.image = UIImage(data: data)
            }
        }
    }
    
    func setupTableView(view : UIView) {
        tableview.delegate = self
        tableview.dataSource = self
        
        tableview.register(PokemonRowCell.self, forCellReuseIdentifier: "cellId")
        
        view.addSubview(tableview)
        
        NSLayoutConstraint.activate([
            tableview.topAnchor.constraint(equalTo: view.topAnchor),
            tableview.bottomAnchor.constraint(equalTo: view.bottomAnchor),
            tableview.rightAnchor.constraint(equalTo: view.rightAnchor),
            tableview.leftAnchor.constraint(equalTo: view.leftAnchor)
        ])
    }
    
    func getData(from url: URL, completion: @escaping (Data?, URLResponse?, Error?) -> ()) {
        URLSession.shared.dataTask(with: url, completionHandler: completion).resume()
    }
    
    func addPokemonItems(pokemonList: PokemonsListModel) {
        self.pokemonsList.append(contentsOf: pokemonList.pokemons)
        self.tableview.reloadData()
    }
}