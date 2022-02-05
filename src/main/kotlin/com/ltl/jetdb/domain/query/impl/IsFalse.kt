package com.ltl.jetdb.domain.query.impl

import com.ltl.jetdb.domain.DataBlob
import com.ltl.jetdb.domain.query.Query

class IsFalse: Query() {
    override fun match(dataBlob: DataBlob<Any>): Boolean {
        return false;
    }
    override fun toString(): String {
        return "false"
    }
}