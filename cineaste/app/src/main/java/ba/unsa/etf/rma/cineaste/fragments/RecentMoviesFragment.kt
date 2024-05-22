package ba.unsa.etf.rma.cineaste.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.unsa.etf.rma.cineaste.activities.MovieDetailActivity
import ba.unsa.etf.rma.cineaste.R
import ba.unsa.etf.rma.cineaste.adapters_etc.MovieListAdapter
import ba.unsa.etf.rma.cineaste.data.Movie
import ba.unsa.etf.rma.cineaste.data.getRecentMovies
import ba.unsa.etf.rma.cineaste.web.GetMoviesResponse
import ba.unsa.etf.rma.cineaste.web.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RecentMoviesFragment : Fragment() {
    private lateinit var recentMovies: RecyclerView
    private var recentMoviesAdapter = MovieListAdapter(arrayListOf()) { movie -> showMovieDetails(movie) }
    //private var recentMoviesList =  getRecentMovies()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_recent_movies, container, false)
        recentMovies = view.findViewById(R.id.recentMovies)
        recentMovies.layoutManager = GridLayoutManager(activity, 2)
        recentMovies.adapter = recentMoviesAdapter
        //recentMoviesAdapter.updateMovies(recentMoviesList)

        return view;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getUpcoming()
    }

    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(activity, MovieDetailActivity::class.java).apply {
            putExtra("movie_title", movie.title)
            putExtra("fragment", "RecentMoviesFragment")
            putExtra("movie_id", movie.id)
        }
        startActivity(intent)
    }

    fun getUpcoming(){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        // Create a new coroutine on the UI thread
        scope.launch{
            // Opcija 1
            val result = MovieRepository.getUpcomingMovies()
            // Display result of the network request to the user
            when (result) {
                is GetMoviesResponse -> onSuccess(result.movies)
                else-> onError()
            }
        }
    }

    fun onSuccess(movies:List<Movie>){
        val toast = Toast.makeText(context, "Upcoming movies found", Toast.LENGTH_SHORT)
        toast.show()
        recentMoviesAdapter.updateMovies(movies)
    }
    fun onError() {
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }
}
