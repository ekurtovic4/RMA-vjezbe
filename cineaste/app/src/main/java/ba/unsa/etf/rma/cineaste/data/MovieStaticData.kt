package ba.unsa.etf.rma.cineaste.data

fun getFavoriteMovies(): List<Movie> {
    return listOf(
        Movie(1,"Pride and prejudice",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2005.","https://www.imdb.com/title/tt0414387/",
            "drama", "res/drawable/prideandprejudice.jpg", "test"
        ),
        Movie(2, "Mamma Mia!",
            "The story of a bride-to-be trying to find her real father told using hit songs by the popular 1970s group ABBA.",
            "10.7.2008.", "https://www.imdb.com/title/tt0795421/",
            "musical", "res/drawable/mammamia.jpg", "test"
        ),
        Movie(3, "Zootopia",
            "In a city of anthropomorphic animals, a rookie bunny cop and a cynical con artist fox must work together to uncover a conspiracy.",
            "04.3.2016.", "https://www.imdb.com/title/tt2948356/",
            "animation", "res/drawable/zootopia.jpg", "test"
        ),
        Movie(4, "How to Lose a Guy in 10 Days",
            "Benjamin Barry is an advertising executive and ladies' man who, to win a big campaign, bets that he can make a woman fall in love with him in 10 days.",
            "27.01.2003.", "https://www.imdb.com/title/tt0251127/",
            "romantic", "res/drawable/howtoloseaguyin10days.jpg", "test"
        ),
        Movie(5, "The Lost World: Jurassic Park",
            "A research team is sent to the Jurassic Park Site B island to study the dinosaurs there, while an InGen team approaches with another agenda.",
            "19.5.1997.", "https://www.imdb.com/title/tt0119567/",
            "action", "res/drawable/jurassicpark.jpg", "test"
        ),
        Movie(6, "Pulp Fiction",
            "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
            "14.10.1994.", "https://www.imdb.com/title/tt0110912/",
            "crime", "res/drawable/pulpfiction.jpg", "test"
        )
    )
}
fun getRecentMovies(): List<Movie> {
    return listOf(
        Movie(1,"Furiosa: A Mad Max Saga",
            "The origin story of renegade warrior Furiosa before her encounter and teamup with Mad Max.",
            "24.05.2024.","https://www.imdb.com/title/tt12037194",
            "action", "res/drawable/furiosa.jpg", "test"
        ),
        Movie(2, "Inside Out 2",
            "Joy, Sadness, Anger, Fear and Disgust have been running a successful operation by all accounts. However, when Anxiety shows up, they aren't sure how to feel.",
            "14.6.2024.", "https://www.imdb.com/title/tt22022452/",
            "animation", "res/drawable/insideout2.jpg", "test"
        ),
        Movie(3, "Back to Black",
            "Singer Amy Winehouse's tumultuous relationship with Blake Fielder-Civil inspires her to write and record the groundbreaking album \"Back to Black.\"",
            "12.4.2024.", "https://www.imdb.com/title/tt21261712/",
            "musical", "res/drawable/backtoblack.jpg", "test"
        ),
        Movie(4, "Deadpool & Wolverine",
            "Wolverine is recovering from his injuries when he crosses paths with the loudmouth, Deadpool. They team up to defeat a common enemy.",
            "26.7.2024.", "https://www.imdb.com/title/tt6263850/",
            "action", "res/drawable/deadpoolandwolverine.jpg", "test"
        ),
        Movie(5, "IF",
            "After discovering she can see everyone's imaginary friends, a girl embarks on a magical adventure to reconnect forgotten IFs with their kids.",
            "17.5.2024.", "https://www.imdb.com/title/tt11152168/",
            "comedy", "res/drawable/ifmovie.jpg", "test"
        )
    )
}
fun getActors(): Map<String,List<String>>{
    return mapOf<String,List<String>>("Pride and prejudice" to listOf("Keira Knightley", "Matthew Macfadyen", "Rosamund Pike"),
        "Mamma Mia!" to listOf("Meryl Streep", "Amanda Seyfried", "Pierce Brosnan", "Colin Firth", "Stellan Skarsgard"),
        "Zootopia" to listOf("Ginnifer Goodwin (voice)", "Nick Wilde (voice)", "Idris Elba (voice)"),
        "How to Lose a Guy in 10 Days" to listOf("Kate Hudson", "Matthew McConaughey", "Kathryn Hahn"),
        "The Lost World: Jurassic Park" to listOf("Jeff Goldblum", "Julianne Moore", "Vince Vaughn"),
        "Pulp Fiction" to listOf("Tim Roth", "Amanda Plummer", "Laura Lovelace", "John Travolta"),
        "Furiosa: A Mad Max Saga" to listOf("Anya Taylor-Joy", "Chris Hemsworth", "Tom Burke"),
        "Inside Out 2" to listOf("Amy Poehler (voice)", "Phyllis Smith (voice)", "Lewis Black (voice)"),
        "Back to Black" to listOf("Marisa Abela", "Eddie Marsan", "Jack O'Connell"),
        "Deadpool & Wolverine" to listOf("Ryan Reynolds", "Hugh Jackman", "Emma Corrin", "Morena Baccarin"),
        "IF" to listOf("Ryan Reynolds", "John Krasinski", "Steve Carell")
        )
}
fun getSimilarMovies(): Map<String,List<String>>{
    return mapOf<String,List<String>>("Pride and prejudice" to listOf("Little Women", "The Notebook", "Atonement"),
        "Mamma Mia!" to listOf("Mamma Mia! Here We Go Again", "Grease"),
        "Zootopia" to listOf("Big Hero 6", "Tangled", "Ratatouille"),
        "How to Lose a Guy in 10 Days" to listOf("10 Thing I Hate About You", "13 Going on 30", "Clueless"),
        "The Lost World: Jurassic Park" to listOf("Jurassic Park III", "Indiana Jones and the Kingdom of the Crystal Skull"),
        "Pulp Fiction" to listOf("Fight Club", "Forrest Gump", "Inception"),
        "Furiosa: A Mad Max Saga" to listOf("Mad Max: Fury Road", "The Fall Guy"),
        "Inside Out 2" to listOf("Inside Out", "Despicable Me", "Soul"),
        "Back to Black" to listOf("Amy", "Nowhere Boy"),
        "Deadpool & Wolverine" to listOf("Deadpool", "Wicked", "Madame Web"),
        "IF" to listOf("Despicable Me", "The Garfield Movie")
    )
}