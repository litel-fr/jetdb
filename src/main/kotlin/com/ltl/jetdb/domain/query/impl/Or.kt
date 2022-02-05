package com.ltl.jetdb.domain.query.impl

import com.ltl.jetdb.domain.DataBlob
import com.ltl.jetdb.domain.query.Query

class Or(vararg val queries: Query): Query() {
    override fun match(dataBlob: DataBlob<Any>): Boolean {
        return queries.asList().parallelStream().filter { it.match(dataBlob) }.findAny().isPresent
    }
    override fun toString(): String {
        val sb = StringBuilder("("+queries[0].toString())
        for(i in 1..queries.size-1){
            sb.append(" or ").append(queries[i].toString())
        }
        return sb.append(')').toString()
    }
}