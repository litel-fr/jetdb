package com.ltl.jetdb.domain.query.impl

import com.ltl.jetdb.domain.DataBlob
import com.ltl.jetdb.domain.query.Query

class Equals<E>(val field: Value<String>, val value: Value<E>) : Query() {

    constructor(field: String, value: Value<E>): this(Value.of(field), value)
    constructor(field: String, value: E): this(Value.of(field), Value.of(value))

    override fun match(dataBlob: DataBlob<Any>): Boolean {
        val f = dataBlob.payload[field.get(dataBlob)]
        return f?.equals(this.value.get(dataBlob)) ?: false
    }

    override fun toString(): String {
        return "$field = ${value.toString()}"
    }
}