import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
//    println(md5("persons/5436?"))
    // https://mirapolis.domadengi.ru/mira/service/v2/persons/5123?appid=system&sign=F29DD1950905116CCAA593A51B010EB6

    println(md5("persons/5123?"))

    var retrofit= Retrofit.Builder()
            .baseUrl("https://mirapolis.domadengi.ru/mira/service/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    var aaa=retrofit.create(APIService::class.java)
//    Observable.interval(1, TimeUnit.SECONDS)
//            .filter { it>100 }
//            .doOnNext { println(it) }
//            .concatMap { Observable.just(aaa.getUser(it.toString(), md5("persons/$it?")).execute().body()!!) }
//    Observable
//            .create<PostModel> {
//        for (int in 10..111){
//            it.onNext(aaa.getUser(int.toString(), md5("persons/$int?")).execute().body()!!)
//        }
//    }.subscribe{ println(it.plastname)}
////    retrofit.create(APIService::class.java).getUser()

    for (int in 1..1000){
        if(aaa.getUser("persons/5123?",::md5).execute().body()==null) continue
        println(aaa.getUser("persons/5123?",::md5).execute().body())
        Thread.sleep(100)
    }


}

fun md5(str: String): String {
    val baseUrl="https://mirapolis.domadengi.ru/mira/service/v2/"
    val token="appid=system&secretkey=sogKiPx6"
    val sign= MessageDigest.getInstance("MD5")
            .digest("$baseUrl$str$token".toByteArray())
            .map { "%02x".format(it).toUpperCase() }
            .reduce { s1, s2 -> s1 + s2 }
    return "$baseUrl${str}appid=system&sign=$sign"
}
