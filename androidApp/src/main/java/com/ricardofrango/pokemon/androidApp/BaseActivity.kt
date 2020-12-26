package com.ricardofrango.pokemon.androidApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ricardofrango.pokemon.pokemon_domain.ui.BasePresenter
import com.ricardofrango.pokemon.pokemon_domain.ui.BaseView

abstract class BaseActivity<PRESENTER, VIEW> :
    AppCompatActivity() where VIEW : BaseView, PRESENTER : BasePresenter<VIEW> {

    protected val presenter by lazy { createPresenter() }

    abstract fun createPresenter() : PRESENTER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.bindView(this as VIEW)
    }

    override fun onDestroy() {
        presenter.destroyView()
        super.onDestroy()
    }
}