package com.bins.tryz.entity


/**
 * A generic wrapper class around data request
 */
sealed class Data<RequestData>{
    class ERROR<RequestData>(var error: String,var data: RequestData? = null): Data<RequestData>()

    class SUCCESS<RequestData>(var data: RequestData? = null): Data<RequestData>()
}