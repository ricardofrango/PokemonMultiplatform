//
//  PokemonListController.swift
//  iosApp
//
//  Created by Ricardo Frango on 26/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import pokemon_domain

class PokemonListController : BaseController<PokemonListPresenter, PokemonListView>, PokemonListView {
    
    override func createPresenter() -> PokemonListPresenter? {
        return PokemonListPresenter(pokemonInteractor: PokemonInteractorImpl(pokemonRepository: PokemonRepositoryImpl.init(pokemonHttpClient: PokemonHttpClient())))
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
        print("showPokemonsList")
    }
    
}
