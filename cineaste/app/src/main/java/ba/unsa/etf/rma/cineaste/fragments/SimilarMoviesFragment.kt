package ba.unsa.etf.rma.cineaste.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import ba.unsa.etf.rma.cineaste.R
import ba.unsa.etf.rma.cineaste.activities.MovieDetailActivity

class SimilarMoviesFragment : Fragment() {
    private lateinit var similarMovies: ListView
    private lateinit var myAdapterInstance: ArrayAdapter<String>
    private lateinit var similarMoviesList: List<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_similar_movies, container, false)
        similarMovies = view.findViewById(R.id.similarMovies)

        val activity = requireActivity() as? MovieDetailActivity
        val tmpSimilarMoviesList = activity?.getSimilarMoviesList()
        if (tmpSimilarMoviesList != null) {
            similarMoviesList = tmpSimilarMoviesList
        }
        else{
            similarMoviesList = listOf("")
        }

        myAdapterInstance = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, similarMoviesList.toMutableList())
        similarMovies.adapter = myAdapterInstance
        myAdapterInstance.notifyDataSetChanged()

        return view;
    }
}









