package com.ltl.jetdb.domain

import java.util.function.Function

data class DataBlob<E>(
    val payload: Map<String, E>
) {

    infix fun mergeWith(highOPriorityBlob: DataBlob<Any>): DataBlob<Any> {
        val result:MutableMap<String,Any> = mutableMapOf<String, Any>()
        result.putAll(this.payload as Map<String, Any>)
        result.putAll(highOPriorityBlob.payload)
        return DataBlob<Any>(result);
    }

    operator fun get(fieldName: String): E? {
        return this.payload[fieldName]
    }

    infix fun <F> transform(func: Function<Pair<String, E>, Pair<String, F>?>): DataBlob<F> {
        TODO()
    }

    infix fun <F> List<DataBlob<E>>.transform(func: Function<Pair<String, E>, Pair<String, F>?>): DataBlob<F> {
        TODO()
    }

    infix fun group(func: Any): DataBlob<List<E>> {
        TODO()
    }

    fun copyWithPrefix(prefix: String): DataBlob<E> {
        val prefixedFields = mutableMapOf<String, E>()
        for(e in payload.entries){
            prefixedFields[prefix+e.key] = e.value
        }
        return DataBlob(prefixedFields);
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DataBlob<*>) return false

        if (payload != other.payload) return false

        return true
    }

    override fun hashCode(): Int {
        return payload.hashCode()
    }


}
