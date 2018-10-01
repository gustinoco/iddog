package br.com.tinoco.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import br.com.tinoco.R
import br.com.tinoco.util.Constants
import com.bumptech.glide.Glide

class DetailDogDialog : DialogFragment() {
    var ivDog: ImageView? = null
    var url: String? = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_detail_dog, container, false)
        initInstance(view)
        return view
    }

    private fun initInstance(view: View) {
        ivDog = view.findViewById(R.id.ivDog) as ImageView
        url = arguments!!.getString(Constants.BUNDLE_TEXT)
        Glide.with(activity)
                .load(url)
                .into(ivDog)
        val btnClose = view.findViewById<Button>(R.id.btnClose)
        btnClose.setOnClickListener { dialog.dismiss() }
    }

    companion object {
        fun newInstance(text: String): DetailDogDialog {
            val f = DetailDogDialog()
            val args = Bundle()
            args.putString(Constants.BUNDLE_TEXT, text)
            f.arguments = args
            return f
        }
    }
}