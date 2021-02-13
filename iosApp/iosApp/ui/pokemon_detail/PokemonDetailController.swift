//
//  PokemonDetailController.swift
//  iosApp
//
//  Created by Ricardo Frango on 01/01/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import pokemon_domain
import UIKit

class PokemonDetailController: BaseController<PokemonDetailPresenter, PokemonDetailView>, PokemonDetailView {
    
    private var imageDownloader = ImageViewDownloader()
    
    @IBOutlet weak var headerImageView: UIImageView!
    
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
        
        loadHeader(pokemonDetailModel: pokemonDetailModel)
    }
    
    private func loadHeader(pokemonDetailModel: PokemonDetailModel) {
        guard let url = pokemonDetailModel.images.first?.getImageUrl() else {
            return
        }
        imageDownloader.downloadImage(uiImageView: headerImageView, from: URL(string: url))
    }
    
    func wrongPokemonId() {
        print("wrongPokemonId")
    }
    
    func getPokemonUrl() -> String? {
        return pokemonUrl
    }
    
    
}
