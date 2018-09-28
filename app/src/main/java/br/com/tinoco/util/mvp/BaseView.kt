package br.com.tinoco.util.mvp

interface BaseView<out T : BasePresenter<*>> {
    val presenter: T
}