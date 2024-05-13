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

class ActorsFragment : Fragment() {
    private lateinit var actors: ListView
    private lateinit var myAdapterInstance: ArrayAdapter<String>
    private lateinit var actorsList: List<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_actors, container, false)
        actors = view.findViewById(R.id.actors)

        val activity = requireActivity() as? MovieDetailActivity
        val tmpActorsList = activity?.getActorsList()
        if (tmpActorsList != null) {
            actorsList = tmpActorsList
        }
        else{
            actorsList = listOf("")
        }

        myAdapterInstance = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, actorsList.toMutableList())
        actors.adapter = myAdapterInstance
        myAdapterInstance.notifyDataSetChanged()

        return view;
    }
}









