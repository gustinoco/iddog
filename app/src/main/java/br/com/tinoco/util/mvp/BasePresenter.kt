package br.com.tinoco.util.mvp

interface BasePresenter<V>{
    fun start()
    fun subscribe(view: V)
    fun unSubscribe()
    var view : V?
}