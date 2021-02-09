//
//  PokemonDetailController.swift
//  iosApp
//
//  Created by Ricardo Frango on 01/01/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import pokemon_domain

class PokemonDetailController: BaseController<PokemonDetailPresenter, PokemonDetailView>, PokemonDetailView {
    
    var pokemonNumber : Int32 = 0
    var pokemonUrl : String? = nil
    
    override func createPresenter() -> PokemonDetailPresenter? {
        return PokemonDetailPresenter(pokemonInteractor: PokemonInteractorImpl(pokemonRepository: PokemonRepositoryImpl.init(pokemonHttpClient: PokemonHttpClient())))
    }
    
    func errorLoadingPokemonDetails() {
        print("errorLoadingPokemonDetails")
    }
    
    func getPokemonId() -> Int32 {
        return pokemonNumber
    }
    
    func loadingPokemonDetails() {
        print("loadingPokemonDetails")
    }
    
    func showPokemonDetails(pokemonDetailModel: PokemonDetailModel) {
        print("showPokemonDetails")
    }
    
    func wrongPokemonId() {
        print("wrongPokemonId")
    }
    
    func getPokemonUrl() -> String? {
        return pokemonUrl
    }
    
}
