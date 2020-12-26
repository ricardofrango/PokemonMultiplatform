package com.ricardofrango.pokemon.pokemon_domain.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<VIEW : BaseView> : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main
    
    protected var view : VIEW? = null
    
    open fun bindView(view : VIEW) {
        this.view = view
    }

    open fun destroyView() {
        view = null
        job.cancel()
    }
}