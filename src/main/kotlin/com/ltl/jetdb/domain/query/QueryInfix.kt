package com.ltl.jetdb.domain.dynamic.queries

import com.ltl.jetdb.domain.DataBlob
import com.ltl.jetdb.domain.query.Query
import com.ltl.jetdb.domain.query.impl.*

infix fun  String.inBlob(data: DataBlob<Any>): FieldRef<Any> {
    return FieldRef<Any>( this, data);
}

infix fun  String.inBlob(blobName:String): FieldRef<Any> {
    return FieldRef("$blobName.$this");
}


infix fun <E> String.equals(value: E): Query {
    if(value is FieldRef<*>){
        return Equals(this, value)
    }
    return Equals(this, Value.of(value))
}

infix fun <E> FieldRef<String>.equals(value: E): Query {
    if(value is FieldRef<*>){
        return Equals(this, value)
    }
    return Equals(this, Value.of(value))
}

infix fun <E> String.notEquals(value: E): Query {
    return Not(Equals(this, Value.of(value)))
}

infix fun Query.and(value: Query): Query {
    return And(this, value)
}

infix fun Query.or(value: Query): Query {
    return Or(this, value)
}

infix fun Query.matches(value: DataBlob<Any>): Boolean {
    return this.match(value)
}

fun not(value: Query): Query {
    return Not(value);
}