//
//  ImageViewDownloader.swift
//  iosApp
//
//  Created by Ricardo Frango on 12/02/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import UIKit


class ImageViewDownloader {
    
    func downloadImage(uiImageView : UIImageView?, from url: URL?) {
        if (uiImageView == nil || url == nil) { return
        }
        
        getData(from: url!) { data, response, error in
            guard let data = data, error == nil else { return }
            //print(response?.suggestedFilename ?? url.lastPathComponent)
            DispatchQueue.main.async() { [weak self] in
                uiImageView!.image = UIImage(data: data)
            }
        }
    }
    
    func getData(from url: URL, completion: @escaping (Data?, URLResponse?, Error?) -> ()) {
        URLSession.shared.dataTask(with: url, completionHandler: completion).resume()
    }
}
