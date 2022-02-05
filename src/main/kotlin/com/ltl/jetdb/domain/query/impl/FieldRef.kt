package com.ltl.jetdb.domain.query.impl

import com.ltl.jetdb.domain.DataBlob

class FieldRef<E>(
    private val fieldName: String,
    var data: DataBlob<E>? = null,
) : Value<E>(
    null
) {

    override fun get(blob: DataBlob<Any>?): E? {
        if (this.data != null)
            return this.data?.get(fieldName) ;
        else
            return blob?.get(fieldName) as E?
    }

    override fun toString(): String {
        return "@$fieldName"
    }

}