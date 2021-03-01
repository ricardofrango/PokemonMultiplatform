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
    
    @IBOutlet weak var ivHeader: UIImageView!
    @IBOutlet weak var tvPokemonName: UITextView!
    @IBOutlet weak var tvPokemonTypes: UITextView!
    @IBOutlet weak var cvEvolutionChain: UICollectionView!
    @IBOutlet weak var cvVarieties: UICollectionView!
    
    var pokemonNumber : Int32 = 0
    var pokemonUrl : String? = nil
    
    override func createPresenter() -> PokemonDetailPresenter? {
        return PokemonDetailPresenter(pokemonInteractor: PokemonInteractorImpl(pokemonRepository: PokemonRepositoryImpl.init(pokemonHttpClient: PokemonHttpClient())))
    }
    
    override func viewDidLoad() {
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
        loadHeader(pokemonDetailModel: pokemonDetailModel)
        tvPokemonName.text = "\(pokemonDetailModel.number) - \(pokemonDetailModel.generation)"
        title = pokemonDetailModel.name
        tvPokemonTypes.text = pokemonDetailModel.types
    }
    
    private func loadHeader(pokemonDetailModel: PokemonDetailModel) {
        guard let url = pokemonDetailModel.images.first?.getImageUrl() else {
            return
        }
        imageDownloader.downloadImage(uiImageView: ivHeader, from: URL(string: url))
    }
    
    func wrongPokemonId() {
        print("wrongPokemonId")
    }
    
    func getPokemonUrl() -> String? {
        return pokemonUrl
    }
    
    
}
