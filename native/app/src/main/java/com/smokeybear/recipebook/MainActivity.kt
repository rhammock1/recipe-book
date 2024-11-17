package com.smokeybear.recipebook

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.smokeybear.recipebook.ui.theme.RecipeBookTheme

class MainActivity : ComponentActivity() {
    private lateinit var webView: WebView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.webView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        webView.webViewClient = WebViewClient()  // Ensures links open in WebView

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true  // Enable JavaScript if needed

        webView.addJavascriptInterface(WebAppInterface(this), "AndroidInterface")
        if(savedInstanceState == null) {
          webView.loadUrl("https://robert-gotab.ngrok.io") // Replace with your web app URL
        }

        // Set up the swipe refresh layout
        swipeRefreshLayout.setOnRefreshListener {
            webView.reload()  // Reload the WebView
            swipeRefreshLayout.isRefreshing = false  // Stop the refreshing spinner
        }

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                swipeRefreshLayout.isRefreshing = true  // Show spinner when page starts loading
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                swipeRefreshLayout.isRefreshing = false  // Hide spinner when page is done loading
            }
        }
    }

    inner class WebAppInterface(private val context: Context) {
        @JavascriptInterface
        fun showToast(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecipeBookTheme {
        Greeting("Android")
    }
}