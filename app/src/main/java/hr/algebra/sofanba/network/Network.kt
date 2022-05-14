package hr.algebra.sofanba.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL_NBA = "https://www.balldontlie.io/api/v1/"
const val BASE_URL_SOFA = "http://academy-2022.dev.sofascore.com/api/v1/academy/"

class Network {

    private val nbaService: NbaService
    private val sofaService: SofaService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor)

        val retrofitNba =
            Retrofit.Builder().baseUrl(BASE_URL_NBA).addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()).build()

        val retrofitSofa =
            Retrofit.Builder().baseUrl(BASE_URL_SOFA).addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()).build()

        nbaService = retrofitNba.create(NbaService::class.java)
        sofaService = retrofitSofa.create(SofaService::class.java)
    }

    fun getNbaService(): NbaService = nbaService

    fun getSofaService(): SofaService = sofaService
}