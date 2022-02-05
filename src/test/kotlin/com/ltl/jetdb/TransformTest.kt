package com.ltl.jetdb

import org.junit.jupiter.api.Test
import com.ltl.jetdb.domain.dynamic.queries.*
import com.ltl.jetdb.domain.*
import com.ltl.jetdb.domain.DataBlob.*

class TransformTest {
    @Test
    fun mapTest() {
        val blobs: List<DataBlob<Any>> = countries() alias "country" find ("name" equals "paris")

//        blobs transform {null}
    }
}