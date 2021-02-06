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
    
    override func viewDidLoad() {
        print(pokemonNumber)
    }
    
    func errorLoadingPokemonDetails() {
        
    }
    
    func getPokemonId() -> Int32 {
        return pokemonNumber
    }
    
    func loadingPokemonDetails() {
        
    }
    
    func showPokemonDetails(pokemonDetailModel: PokemonDetailModel) {
        
    }
    
    func wrongPokemonId() {
        
    }
    
    
    
}
