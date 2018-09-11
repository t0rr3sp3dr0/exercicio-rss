package br.ufpe.cin.if710.rss

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {
            try {
                // make request to specified URL using OkHttp
                val request = Request.Builder().url(getString(R.string.rssfeed)).build()
                OkHttpClient().newCall(request).execute().body()?.let {
                    // parse XML
                    val rss = ParserRSS.parse(it.string())

                    uiThread {
                        conteudoRSS.apply {
                            // use a linear layout manager
                            layoutManager = LinearLayoutManager(it)

                            // specify an viewAdapter (see also next example)
                            adapter = MyAdapter(rss)

                            // add divider item decoration to recycler view
                            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
                        }
                    }
                }
            } catch (e: Exception) {
                uiThread {
                    // show snackbar if there was an error
                    Snackbar.make(window.decorView.rootView, e.message ?: e.toString(), Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}
