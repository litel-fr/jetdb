package com.ltl.jetdb.domain.query

import com.ltl.jetdb.domain.DataBlob

abstract class Query {
    abstract fun match(dataBlob: DataBlob<Any>): Boolean
}