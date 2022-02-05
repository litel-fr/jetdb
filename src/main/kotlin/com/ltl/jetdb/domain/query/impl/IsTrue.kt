package com.ltl.jetdb.domain.query.impl

import com.ltl.jetdb.domain.DataBlob
import com.ltl.jetdb.domain.query.Query

class IsTrue(): Query() {
    override fun match(dataBlob: DataBlob<Any>): Boolean {
        return true
    }
    override fun toString(): String {
        return "true"
    }
}