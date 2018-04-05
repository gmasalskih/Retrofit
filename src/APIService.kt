import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {
    @GET
    fun getUser(@Url url: String, fn: (String) -> String): Call<PostModel>
}