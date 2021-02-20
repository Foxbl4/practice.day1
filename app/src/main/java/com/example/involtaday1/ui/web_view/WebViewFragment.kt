package com.example.involtaday1.ui.web_view

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.webkit.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.webkit.WebSettingsCompat
import com.example.involtaday1.R
import kotlinx.android.synthetic.main.fragment_image.*
import kotlinx.android.synthetic.main.fragment_web_view.*



class WebViewFragment : Fragment() {

    private lateinit var webSiteEditText: EditText
    private lateinit var webSiteShow: WebView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_web_view, container, false)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            veb_view_show.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            veb_view_show.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        WebSettingsCompat.setForceDark(veb_view_show.settings, WebSettingsCompat.FORCE_DARK_ON)
        veb_view_show.loadUrl("https://yandex.ru")
        veb_view_show.webViewClient = MyWebViewClient()

        view.apply {
            webSiteEditText = findViewById(R.id.web_view_research)
            webSiteShow = findViewById(R.id.veb_view_show)

            webSiteEditText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    openUrl()
                    return@setOnEditorActionListener true
                } else {
                    return@setOnEditorActionListener false
                }
            }

            veb_view_show?.settings?.apply {
                javaScriptEnabled = true
                setRenderPriority(WebSettings.RenderPriority.HIGH)
                domStorageEnabled = true
                loadsImagesAutomatically = true
                blockNetworkImage = false
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                javaScriptCanOpenWindowsAutomatically = true
            }
        }
    }

    private fun openUrl() {
        val inputText = webSiteEditText.text.toString()
        inputText.let {
            var urlString = it
            if (!URLUtil.isHttpUrl(it) && !URLUtil.isHttpsUrl(it)) {
                urlString = "https://$urlString"
            }
            if (URLUtil.isValidUrl(urlString)) {
                webSiteShow.loadUrl(urlString)
                webSiteEditText.clearFocus()
            }
        }
    }
}

private class MyWebViewClient: WebViewClient() {
    @TargetApi(Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view:WebView, request: WebResourceRequest):Boolean {
        view.loadUrl(request.url.toString())
        return true
    }
}