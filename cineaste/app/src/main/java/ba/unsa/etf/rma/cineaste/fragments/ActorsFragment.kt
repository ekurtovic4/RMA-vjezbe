package ba.unsa.etf.rma.cineaste.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ba.unsa.etf.rma.cineaste.R
import ba.unsa.etf.rma.cineaste.activities.MovieDetailActivity
import ba.unsa.etf.rma.cineaste.data.Movie
import ba.unsa.etf.rma.cineaste.web.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ActorsFragment : Fragment() {
    private lateinit var actors: ListView
    private lateinit var myAdapterInstance: ArrayAdapter<String>
    private var actorsList: List<String> = listOf("")
    private lateinit var movie: Movie

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_actors, container, false)

        val activity = requireActivity() as? MovieDetailActivity
        val intent = activity?.intent
        val extras = intent?.extras

        if (extras != null) {
            if(extras.getString("fragment") == "FavoriteMoviesFragment"){
                context?.let { getActors(it, extras.getLong("movie_id")) }
            }
            /*else if(extras.getString("fragment", "") == "RecentMoviesFragment"){
                actorsList = activity.getActorsList()
            }*/
        }

        actors = view.findViewById(R.id.actors)
        myAdapterInstance = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, actorsList.toMutableList())
        actors.adapter = myAdapterInstance
        myAdapterInstance.notifyDataSetChanged()

        return view;
    }

    fun getActors(context: Context, movieId: Long){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        // Create a new coroutine on the UI thread
        scope.launch{

            // Make the network call and suspend execution until it finishes
            val result = MovieRepository.getActors(context, movieId)

            // Display result of the network request to the user
            when (result) {
                is List<String> -> onSuccessDb(result)
                else-> onErrorDb()
            }
        }
    }

    fun onSuccessDb(actors:List<String>){
        actorsList = actors
        myAdapterInstance.notifyDataSetChanged()
    }
    fun onErrorDb() {
        val toast = Toast.makeText(context, "Error", Toast.LENGTH_SHORT)
        toast.show()
    }
}









