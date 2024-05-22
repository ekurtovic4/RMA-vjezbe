package ba.unsa.etf.rma.cineaste.web

import ba.unsa.etf.rma.cineaste.BuildConfig
import ba.unsa.etf.rma.cineaste.data.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @Headers("Authorization: Bearer=${BuildConfig.TMDB_API_KEY}")
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Response<GetMoviesResponse>

    @Headers("Authorization: Bearer=${BuildConfig.TMDB_API_KEY}")
    @GET("movie/top_rated")
    suspend fun getFavoriteMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Response<GetMoviesResponse>

    @Headers("Authorization: Bearer=${BuildConfig.TMDB_API_KEY}")
    @GET("search/movie")
    suspend fun getMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Response<GetMoviesResponse>

    @Headers("Authorization: Bearer=${BuildConfig.TMDB_API_KEY}")
    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Response<GetActorsResponse>

    @Headers("Authorization: Bearer=${BuildConfig.TMDB_API_KEY}")
    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Response<GetMoviesResponse>

    @Headers("Authorization: Bearer=${BuildConfig.TMDB_API_KEY}")
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Response<Movie>
}