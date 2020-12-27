//
//  PokemonListController.swift
//  iosApp
//
//  Created by Ricardo Frango on 26/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import pokemon_domain
import UIKit

class PokemonListController : BaseController<PokemonListPresenter, PokemonListView>, PokemonListView {
        
    private var pokemonListDataSource : PokemonListDataSource = PokemonListDataSource()
    
    override func createPresenter() -> PokemonListPresenter? {
        return PokemonListPresenter(pokemonInteractor: PokemonInteractorImpl(pokemonRepository: PokemonRepositoryImpl.init(pokemonHttpClient: PokemonHttpClient())))
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pokemonListDataSource.setupTableView(view : self.view)
    }
    
    func errorLoadingMorePokemons() {
        print("errorLoadingMorePokemons")
    }
    
    func errorLoadingPokemonsList() {
        print("errorLoadingPokemonsList")
    }
    
    func loadingMorePokemons() {
        print("loadingMorePokemons")
    }
    
    func loadingPokemonsList() {
        print("loadingPokemonsList")
    }
    
    func showMorePokemonsList(pokemons: PokemonsListModel) {
        print("showMorePokemonsList")
    }
    
    func showPokemonsList(pokemonListModel: PokemonsListModel) {
        pokemonListDataSource.addPokemonItems(pokemonList: pokemonListModel)
    }
    
}
