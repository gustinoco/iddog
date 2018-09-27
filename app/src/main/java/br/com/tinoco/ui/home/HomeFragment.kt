package br.com.tinoco.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tinoco.R
import br.com.tinoco.adapter.CategoryAdapter
import br.com.tinoco.ui.detail.DetailDogDialog
import br.com.tinoco.util.Constants
import br.com.tinoco.util.showSnack
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), HomeContract.View {

    override fun getCategory(): List<String> {
        return resources.getStringArray(R.array.list_category_default).toList()
    }

    override fun newCategory(category: String) {
        var menu = activity?.nav_view?.menu
        menu?.add(category)
    }


    override fun showSuccess(message: String, list: List<String>) {
        txtTypeCategory.text = message
        val listAdapter = CategoryAdapter(list as MutableList<String>, activity!!.applicationContext, { item: String -> partItemClicked(item) })
        rcvDogs.adapter = listAdapter
        list.let { listAdapter.addOpenSourcesToList(it) }
    }

    private fun partItemClicked(partItem: String) {
        val d = DetailDogDialog.newInstance(partItem)
        d.show(activity?.supportFragmentManager, Constants.FRAG_SUPPORT)
    }

    override fun showLoading(active: Boolean) {
        if (active)
            loading.visibility = View.VISIBLE
        else
            loading.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        view?.showSnack(message)
    }

    override lateinit var presenter: HomeContract.Presenter


    companion object {
        fun newInstance() = HomeFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val rcv = root.findViewById<RecyclerView>(R.id.rcvDogs)
        rcv.layoutManager = GridLayoutManager(context, 3)
        return root
    }

}