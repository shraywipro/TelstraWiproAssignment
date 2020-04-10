package com.assignment.telstra.core.store.online.services

import com.assignment.telstra.core.store.online.models.FactData
import com.assignment.telstra.core.store.online.models.FactModel
import com.assignment.telstra.utils.APIConstants
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

/**
 * @desc interface to define all the API methods
 */
interface ApiService {

    /*Get static entity values*/
    @GET(APIConstants.FACT_URL)
    fun getEntity(): Single<Response<FactModel>>

}