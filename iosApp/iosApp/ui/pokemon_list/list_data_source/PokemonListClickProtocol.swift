//
//  PokemonListClickProtocol.swift
//  iosApp
//
//  Created by Ricardo Frango on 01/01/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import pokemon_domain

protocol PokemonListClickProtocol {
    func onPokemonClicked(pokemonClicked : PokemonItemModel)
}
