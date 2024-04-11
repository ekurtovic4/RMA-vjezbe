package ba.unsa.etf.rma.cineaste.data

fun getFavoriteMovies(): List<Movie> {
    return listOf(
        Movie(1,"Pride and prejudice",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2005.","https://www.imdb.com/title/tt0414387/",
            "drama", listOf("Keira Knightley", "Matthew Macfadyen", "Rosamund Pike"),
            listOf("Little Women", "The Notebook", "Atonement")
        ),
        Movie(2, "Mamma Mia!",
            "The story of a bride-to-be trying to find her real father told using hit songs by the popular 1970s group ABBA.",
            "10.7.2008.", "https://www.imdb.com/title/tt0795421/",
            "musical", listOf("Meryl Streep", "Amanda Seyfried", "Pierce Brosnan", "Colin Firth", "Stellan Skarsgård"),
            listOf("Mamma Mia! Here We Go Again", "Grease")
        ),
        Movie(3, "Zootopia",
            "In a city of anthropomorphic animals, a rookie bunny cop and a cynical con artist fox must work together to uncover a conspiracy.",
            "04.3.2016.", "https://www.imdb.com/title/tt2948356/",
            "animation", listOf("Ginnifer Goodwin (voice)", "Nick Wilde (voice)", "Idris Elba (voice)"),
            listOf("Big Hero 6", "Tangled", "Ratatouille")
        ),
        Movie(4, "How to Lose a Guy in 10 Days",
            "Benjamin Barry is an advertising executive and ladies' man who, to win a big campaign, bets that he can make a woman fall in love with him in 10 days.",
            "27.01.2003.", "https://www.imdb.com/title/tt0251127/",
            "romantic", listOf("Kate Hudson", "Matthew McConaughey", "Kathryn Hahn"),
            listOf("10 Thing I Hate About You", "13 Going on 30", "Clueless")
        ),
        Movie(5, "The Lost World: Jurassic Park",
            "A research team is sent to the Jurassic Park Site B island to study the dinosaurs there, while an InGen team approaches with another agenda.",
            "19.5.1997.", "https://www.imdb.com/title/tt0119567/",
            "action", listOf("Jeff Goldblum", "Julianne Moore", "Vince Vaughn"),
            listOf("Jurassic Park III", "Indiana Jones and the Kingdom of the Crystal Skull")),
        Movie(6, "Pulp Fiction",
            "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
            "14.10.1994.", "https://www.imdb.com/title/tt0110912/",
            "crime", listOf("Tim Roth", "Amanda Plummer", "Laura Lovelace", "John Travolta"),
            listOf("Fight Club", "Forrest Gump", "Inception")
        )
    )
}
fun getRecentMovies(): List<Movie> {
    return listOf(
        Movie(1,"Furiosa: A Mad Max Saga",
            "The origin story of renegade warrior Furiosa before her encounter and teamup with Mad Max.",
            "24.05.2024.","https://www.imdb.com/title/tt12037194",
            "action", listOf("Anya Taylor-Joy", "Chris Hemsworth", "Tom Burke"),
            listOf("Mad Max: Fury Road", "The Fall Guy")
        ),
        Movie(2, "Inside Out 2",
            "Joy, Sadness, Anger, Fear and Disgust have been running a successful operation by all accounts. However, when Anxiety shows up, they aren't sure how to feel.",
            "14.6.2024.", "https://www.imdb.com/title/tt22022452/",
            "animation", listOf("Amy Poehler (voice)", "Phyllis Smith (voice)", "Lewis Black (voice)"),
            listOf("Inside Out", "Despicable Me", "Soul")
        ),
        Movie(3, "Back to Black",
            "Singer Amy Winehouse's tumultuous relationship with Blake Fielder-Civil inspires her to write and record the groundbreaking album \"Back to Black.\"",
            "12.4.2024.", "https://www.imdb.com/title/tt21261712/",
            "musical", listOf("Marisa Abela", "Eddie Marsan", "Jack O'Connell"),
            listOf("Amy", "Nowhere Boy")
        ),
        Movie(4, "Deadpool & Wolverine",
            "Wolverine is recovering from his injuries when he crosses paths with the loudmouth, Deadpool. They team up to defeat a common enemy.",
            "26.7.2024.", "https://www.imdb.com/title/tt6263850/",
            "action", listOf("Ryan Reynolds", "Hugh Jackman", "Emma Corrin", "Morena Baccarin"),
            listOf("Deadpool", "Wicked", "Madame Web")
        ),
        Movie(5, "IF",
            "After discovering she can see everyone's imaginary friends, a girl embarks on a magical adventure to reconnect forgotten IFs with their kids.",
            "17.5.2024.", "https://www.imdb.com/title/tt11152168/",
            "comedy", listOf("Ryan Reynolds", "John Krasinski", "Steve Carell"),
            listOf("Despicable Me", "The Garfield Movie")
        )
    )
}