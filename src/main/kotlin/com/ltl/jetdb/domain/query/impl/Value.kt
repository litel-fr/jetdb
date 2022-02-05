package com.ltl.jetdb.domain.query.impl

import com.ltl.jetdb.domain.DataBlob

open class Value<E>(
    val v: E?
) {

    companion object {
        fun <E> of(obj: E): Value<E> {
            return Value(obj);
        }
    }

    open fun get(blob: DataBlob<Any>? = null): E? {
        return v;
    }

    override fun toString(): String {
        return v.toString()
    }
}