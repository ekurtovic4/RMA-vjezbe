package ba.unsa.etf.rma.cineaste.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.unsa.etf.rma.cineaste.R
import ba.unsa.etf.rma.cineaste.adapters_etc.SearchMoviesAdapter
import ba.unsa.etf.rma.cineaste.data.Movie
import ba.unsa.etf.rma.cineaste.web.GetMoviesResponse
import ba.unsa.etf.rma.cineaste.web.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var searchText: EditText
    private lateinit var searchButton: AppCompatImageButton
    private lateinit var searchMoviesAdapter: SearchMoviesAdapter
    private lateinit var movieResultsRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_search, container, false)
        searchText = view.findViewById(R.id.searchText)
        arguments?.getString("search")?.let {
            searchText.setText(it)
        }

        searchButton = view.findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            onClick()
        }

        movieResultsRV = view.findViewById(R.id.movieResultsRV)
        movieResultsRV.layoutManager = GridLayoutManager(activity, 2)
        searchMoviesAdapter = SearchMoviesAdapter(arrayListOf())
        movieResultsRV.adapter = searchMoviesAdapter
        searchMoviesAdapter.updateMovies(arrayListOf())

        return view;
    }

    //On Click handler
    private fun onClick() {
        val toast = Toast.makeText(context, "Search start", Toast.LENGTH_SHORT)
        toast.show()
        search(searchText.text.toString())
    }
    fun search(query: String){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        // Create a new coroutine on the UI thread
        scope.launch{
            // Opcija 1
            val result = MovieRepository.searchMovie(query)
            // Display result of the network request to the user
            when (result) {
                is GetMoviesResponse -> searchDone(result.movies)
                else-> onError()
            }
        }
    }
    fun searchDone(movies:List<Movie>){
        val toast = Toast.makeText(context, "Search done", Toast.LENGTH_SHORT)
        toast.show()
        searchMoviesAdapter.updateMovies(movies)
    }
    fun onError() {
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }
}