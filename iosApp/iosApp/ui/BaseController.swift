//
//  BaseController.swift
//  iosApp
//
//  Created by Ricardo Frango on 26/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import UIKit
import pokemon_domain

class BaseController<PRESENTER, VIEW> : UIViewController where VIEW : BaseView, PRESENTER : BasePresenter<VIEW> {
    
    var presenter : PRESENTER?
        
    open func createPresenter() -> PRESENTER? { return nil }
        
    override func viewWillAppear(_ animated: Bool) {
        presenter = createPresenter()
        presenter?.bindView(view: self as! VIEW)
    }
        
    override func viewWillDisappear(_ animated: Bool) {
        presenter?.destroyView()
        presenter = nil
    }
}
