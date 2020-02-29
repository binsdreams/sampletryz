package com.bins.tryz.server

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockServer {

    //  valid response dispatcher
    class ResponseDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(200).setBody(
                "[" +
                        "{ \"id\": 274562," +
                        "\"name\": \"yajl-objc\"," +
                        "\"full_name\": \"square/yajl-objc\"," +
                        "\"owner\": {" +
                        "\"id\": 82592," +
                        "\"avatar_url\": \"https://avatars0.githubusercontent.com/u/82592?v=4\"" +
                        "}," +
                        "\"description\": \"Objective-C bindings for YAJL (Yet Another JSON Library) C library\"" +
                        "}," +
                        "{" +
                        "\"id\": 298912," +
                        "\"name\": \"simplerrd\"," +
                        "\"full_name\": \"square/simplerrd\"," +
                        "\"owner\": {\n" +
                        "\"id\": 82592,\n" +
                        "\"avatar_url\": \"https://avatars0.githubusercontent.com/u/82592?v=4\"" +
                        "},\n" +
                        "\"description\": \"SimpleRRD provides a simple Ruby interface for creating graphs with RRD\"" +
                        "}" +
                        "]"
            )
        }
    }
    //  error dispatcher
    class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse = MockResponse().setResponseCode(400)
    }
}