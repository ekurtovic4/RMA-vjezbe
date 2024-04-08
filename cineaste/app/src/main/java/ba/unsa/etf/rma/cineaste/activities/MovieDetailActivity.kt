package ba.unsa.etf.rma.cineaste.activities

import android.app.SearchManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ba.unsa.etf.rma.cineaste.R
import ba.unsa.etf.rma.cineaste.data.Movie
import ba.unsa.etf.rma.cineaste.data.getFavoriteMovies
import ba.unsa.etf.rma.cineaste.data.getRecentMovies
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var movie: Movie
    private lateinit var title : TextView
    private lateinit var overview : TextView
    private lateinit var releaseDate : TextView
    private lateinit var genre : TextView
    private lateinit var website : TextView
    private lateinit var poster : ImageView
    private lateinit var shareBtn : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        title = findViewById(R.id.movie_title)
        overview = findViewById(R.id.movie_overview)
        releaseDate = findViewById(R.id.movie_release_date)
        genre = findViewById(R.id.movie_genre)
        poster = findViewById(R.id.movie_poster)
        website = findViewById(R.id.movie_website)
        shareBtn = findViewById(R.id.shareButton)

        val extras = intent.extras
        if (extras != null) {
            movie = getMovieByTitle(extras.getString("movie_title",""))
            populateDetails()
        } else {
            finish()
        }
        website.setOnClickListener{
            showWebsite()
        }
        title.setOnClickListener {
            showTrailer()
        }
        shareBtn.setOnClickListener{
            sendText()
        }
    }
    private fun populateDetails() {
        title.text=movie.title
        releaseDate.text=movie.releaseDate
        genre.text=movie.genre
        website.text=movie.homepage
        overview.text=movie.overview
        val context: Context = poster.context
        var id: Int = context.resources
            .getIdentifier(movie.genre, "drawable", context.packageName)
        if (id==0) id=context.resources
            .getIdentifier("picture1", "drawable", context.packageName)
        poster.setImageResource(id)
    }
    private fun getMovieByTitle(name:String): Movie {
        val movies: ArrayList<Movie> = arrayListOf()
        movies.addAll(getRecentMovies())
        movies.addAll(getFavoriteMovies())
        val movie= movies.find { movie -> name == movie.title }
        return movie?: Movie(0,"Test","Test","Test","Test","Test")
    }
    private fun showWebsite(){
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.homepage))
        try {
            startActivity(webIntent)
        } catch (e: ActivityNotFoundException) {
            println("Ne postoji aplikacija za navedenu akciju")
        }
    }
    private fun showTrailer(){
        val webIntent = Intent(Intent.ACTION_WEB_SEARCH).apply{
            putExtra(SearchManager.QUERY, title.text.toString() + " trailer")
        }
        try{
            startActivity(webIntent)
        } catch (e: ActivityNotFoundException) {
            println("Ne postoji aplikacija za navedenu akciju")
        }
    }
    private fun sendText(){
        val txtIntent = Intent(Intent.ACTION_SEND).apply{
            putExtra(Intent.EXTRA_TEXT, overview.text.toString())
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(txtIntent, null)
        try{
            startActivity(shareIntent)
        } catch (e: ActivityNotFoundException) {
            println("Ne postoji aplikacija za navedenu akciju")
        }
    }
}