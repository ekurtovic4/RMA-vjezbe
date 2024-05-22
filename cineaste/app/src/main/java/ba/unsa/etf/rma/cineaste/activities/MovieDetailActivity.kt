package ba.unsa.etf.rma.cineaste.activities

import android.app.SearchManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ba.unsa.etf.rma.cineaste.R
import ba.unsa.etf.rma.cineaste.data.Actor
import ba.unsa.etf.rma.cineaste.data.ActorsMovies
import ba.unsa.etf.rma.cineaste.data.Movie
import ba.unsa.etf.rma.cineaste.web.GetActorsResponse
import ba.unsa.etf.rma.cineaste.web.GetMoviesResponse
import ba.unsa.etf.rma.cineaste.web.MovieRepository
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
    private lateinit var website : TextView
    private lateinit var poster : ImageView
    private lateinit var shareBtn : FloatingActionButton
    private lateinit var  addFavorite: Button
    private lateinit var deleteFavorite: Button
    private val posterPath = "https://image.tmdb.org/t/p/w342"
    private var actorsList = listOf<String>()
    private var similarMoviesList = listOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        title = findViewById(R.id.movie_title)
        overview = findViewById(R.id.movie_overview)
        releaseDate = findViewById(R.id.movie_release_date)
        poster = findViewById(R.id.movie_poster)
        website = findViewById(R.id.movie_website)
        shareBtn = findViewById(R.id.shareButton)
        addFavorite = findViewById(R.id.addFavorite)
        deleteFavorite = findViewById(R.id.deleteFavorite)

        thisMovie = Movie(0,"Test","Test","Test","Test", "Test", "Test")

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

        if (extras != null) {
            if(extras.getString("fragment", "") == "FavoriteMoviesFragment"){
                addFavorite.visibility = View.GONE
            }
            else if(extras.getString("fragment", "") == "RecentMoviesFragment"){
                deleteFavorite.visibility = View.GONE
            }
        }

        addFavorite.setOnClickListener{
            writeDB(this, thisMovie)
        }
        deleteFavorite.setOnClickListener {
            deleteDB(this, thisMovie)
        }
    }

    fun search(query: String){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        // Create a new coroutine on the UI thread
        scope.launch{
            // Opcija 1
            val result = MovieRepository.searchMovie(query)
            // Display result of the network request to the user
            when (result) {
                is GetMoviesResponse -> {
                    searchDetails(result.movies[0])
                    searchActors(result.movies[0])
                    searchSimilarMovies(result.movies[0])
                }
                else-> onError()
            }
        }
    }

    fun searchDetails(movie: Movie){
        thisMovie = movie

        val scope = CoroutineScope(Job() + Dispatchers.Main)
        // Create a new coroutine on the UI thread
        scope.launch{
            // Opcija 1
            val result = MovieRepository.getMovieDetails(movie.id)
            // Display result of the network request to the user
            when (result) {
                is Movie -> searchDone(result)
                else-> onError()
            }
        }
    }

    fun searchActors(movie: Movie){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        // Create a new coroutine on the UI thread
        scope.launch{
            // Opcija 1
            val result = MovieRepository.getActors(movie.id)
            // Display result of the network request to the user
            when (result) {
                is GetActorsResponse -> onSuccessActors(result.actors)
                else-> onError()
            }
        }
    }

    fun searchSimilarMovies(movie: Movie){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        // Create a new coroutine on the UI thread
        scope.launch{
            // Opcija 1
            val result = MovieRepository.getSimilarMovies(movie.id)
            // Display result of the network request to the user
            when (result) {
                is GetMoviesResponse -> onSuccessSimilarMovies(result.movies)
                else-> onError()
            }
        }
    }

    fun searchDone(movie: Movie){
        thisMovie = Movie(thisMovie.id, thisMovie.title, thisMovie.overview, thisMovie.releaseDate, movie.homepage, thisMovie.posterPath, "test")
        populateDetails()
    }

    fun onSuccessActors(actors:List<Actor>){
        actorsList = actors.map { it.name }
    }

    fun onSuccessSimilarMovies(similarMovies: List<Movie>){
        similarMoviesList = similarMovies.map { it.title }
    }

    fun onError() {
        val toast = Toast.makeText(this, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun populateDetails() {
        title.text=thisMovie.title
        releaseDate.text=thisMovie.releaseDate
        website.text=thisMovie.homepage
        overview.text=thisMovie.overview

        val context: Context = poster.context
        var id: Int = 0;
        id=context.getResources()
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

    fun writeDB(context: Context, movie:Movie){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch{
            val result = MovieRepository.writeFavorite(context,movie)
            when (result) {
                is String -> {
                    writeActorsDB(context, actorsList)
                    onSuccess1(result)
                }
                else-> onError1()
            }
        }
    }

    fun writeActorsDB(context: Context, actors:List<String>){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch{
            for(i in 0..3){
                val result = MovieRepository.writeActors(context, ActorsMovies(0, actors[i], thisMovie.id))
                when(result){
                    is String -> null
                    else -> onError1()
                }
            }
        }
    }

    fun deleteDB(context: Context, movie:Movie){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch{
            val result = MovieRepository.deleteFavorite(context,movie)
            when (result) {
                is String -> {
                    deleteActorsDB(context, actorsList)
                    onSuccess2(result)
                }
                else-> onError1()
            }
        }
    }

    fun deleteActorsDB(context: Context, actors:List<String>){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch{
            for(i in 0..3){
                val result = MovieRepository.deleteActors(context, ActorsMovies(0, actors[i], thisMovie.id))
                when(result){
                    is String -> null
                    else -> onError1()
                }
            }
        }
    }

    fun onSuccess1(message:String){
        val toast = Toast.makeText(applicationContext, "Spaseno", Toast.LENGTH_SHORT)
        toast.show()
        addFavorite.visibility = View.GONE
    }
    fun onSuccess2(message:String){
        val toast = Toast.makeText(applicationContext, "Spaseno", Toast.LENGTH_SHORT)
        toast.show()
        deleteFavorite.visibility = View.GONE
    }

    fun onError1() {
        val toast = Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT)
        toast.show()
    }
}