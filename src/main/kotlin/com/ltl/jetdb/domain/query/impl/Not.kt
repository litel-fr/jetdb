package com.ltl.jetdb.domain.query.impl

import com.ltl.jetdb.domain.DataBlob
import com.ltl.jetdb.domain.query.Query

class Not(val query: Query): Query(){
    override fun match(dataBlob: DataBlob<Any>): Boolean {
        return !this.query.match(dataBlob)
    }
    override fun toString(): String {
        return "not ($query)"
    }
}