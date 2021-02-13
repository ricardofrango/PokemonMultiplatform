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
    
    override init() {
        imageDownloader = ImageViewDownloader()
    }
    
    private var imageDownloader : ImageViewDownloader?
    var onItemClicked : PokemonListClickProtocol?
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
        let pokemon = pokemonsList[indexPath.row]
        let cell = tableview.dequeueReusableCell(withIdentifier: "cellId", for: indexPath) as! PokemonRowCell
        cell.backgroundColor = UIColor.white
        cell.dayLabel.text = pokemon.name
        imageDownloader?.downloadImage(uiImageView: cell.imageUiView, from: URL.init(string: pokemon.imageUrl!)!)
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let pokemon = pokemonsList[indexPath.row]
        onItemClicked?.onPokemonClicked(pokemonClicked: pokemon)
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 75
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
    
    func addPokemonItems(pokemonList: PokemonsListModel) {
        self.pokemonsList.append(contentsOf: pokemonList.pokemons)
        self.tableview.reloadData()
    }
}
