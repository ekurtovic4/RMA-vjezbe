package ba.unsa.etf.rma.cineaste.activities

import android.app.SearchManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ba.unsa.etf.rma.cineaste.R
import ba.unsa.etf.rma.cineaste.data.Movie
import ba.unsa.etf.rma.cineaste.web.ActorsSearch
import ba.unsa.etf.rma.cineaste.web.DetailsSearch
import ba.unsa.etf.rma.cineaste.web.MovieSearch
import ba.unsa.etf.rma.cineaste.web.Result
import ba.unsa.etf.rma.cineaste.web.SimilarMoviesSearch
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var thisMovie: Movie
    private lateinit var title : TextView
    private lateinit var overview : TextView
    private lateinit var releaseDate : TextView
    private lateinit var genre : TextView
    private lateinit var website : TextView
    private lateinit var poster : ImageView
    private lateinit var shareBtn : FloatingActionButton
    private val posterPath = "https://image.tmdb.org/t/p/w342"
    private var actorsList = listOf<String>()
    private var similarMoviesList = listOf<String>()

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

        thisMovie = Movie(0,"Test","Test","Test","Test","Test", "Test", "Test")

        val extras = intent.extras
        if (extras != null) {
            search(extras.getString("movie_title",""))
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

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigation)
        navView.setupWithNavController(navController)
    }

    fun search(query: String){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch{
            val result = MovieSearch.searchRequest(query)
            when (result) {
                is Result.Success<Movie> -> {
                    searchDetails(result.data)
                    searchActors(result.data)
                    searchSimilarMovies(result.data)
                }
                else-> onError()
            }
        }
    }

    fun searchDetails(movie: Movie){
        thisMovie = movie

        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch{
            val result = DetailsSearch.searchRequest(movie.id)
            when (result) {
                is Result.Success<Movie> -> searchDone(result.data)
                else-> onError()
            }
        }
    }

    fun searchActors(movie: Movie){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch{
            val result = ActorsSearch.searchRequest(movie.id)
            when (result) {
                is Result.Success<List<String>> -> actorsList = result.data
                else-> onError()
            }
        }
    }

    fun searchSimilarMovies(movie: Movie){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch{
            val result = SimilarMoviesSearch.searchRequest(movie.id)
            when (result) {
                is Result.Success<List<String>> -> similarMoviesList = result.data
                else-> onError()
            }
        }
    }

    fun searchDone(movie: Movie){
        thisMovie = Movie(thisMovie.id, thisMovie.title, thisMovie.overview, thisMovie.releaseDate, movie.homepage, movie.genre, thisMovie.posterPath, "test")
        populateDetails()
    }

    fun onError() {
        val toast = Toast.makeText(this, "Error", Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun populateDetails() {
        title.text=thisMovie.title
        releaseDate.text=thisMovie.releaseDate
        genre.text=thisMovie.genre
        website.text=thisMovie.homepage
        overview.text=thisMovie.overview

        val genreMatch: String? = thisMovie.genre
        val context: Context = poster.context
        var id: Int = 0;
        if (genreMatch!==null)
            id = context.getResources()
                .getIdentifier(genreMatch, "drawable", context.getPackageName())
        if (id===0) id=context.getResources()
            .getIdentifier("picture1", "drawable", context.getPackageName())
        Glide.with(context)
            .load(posterPath + thisMovie.posterPath)
            .centerCrop()
            .placeholder(R.drawable.picture1)
            .error(id)
            .fallback(id)
            .into(poster);
    }

    fun getActorsList(): List<String> {
        return actorsList
    }

    fun getSimilarMoviesList(): List<String> {
        return similarMoviesList
    }

    private fun showWebsite(){
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(thisMovie.homepage))
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