package com.ltl.jetdb.domain.sources

import com.ltl.jetdb.domain.DataBlob
import com.ltl.jetdb.domain.query.impl.IsTrue
import com.ltl.jetdb.domain.query.Query

abstract class  DataBlobSource(
    var name: String,
) {

    abstract fun filter(query: Query, limit: Int? = Int.MAX_VALUE, skip: Int? = 0): DataBlobSource;

    abstract fun find(query: Query, limit: Int? = Int.MAX_VALUE, skip: Int? = 0): List<DataBlob<Any>>;

    fun findOne(query: Query, skip: Int= 0): DataBlob<Any>? {
        val find = this.find(query, 1, skip)
        if(find.isEmpty()) return null;
        return find[0];
    }

    fun list(limit: Int = Int.MAX_VALUE, skip: Int = 0): List<DataBlob<Any>> {
        return this.find(IsTrue(), limit, skip)
    }

    infix fun alias(name: String): DataBlobSource {
        this.name = name;
        return this;
    }

    infix fun find(query: Query): List<DataBlob<Any>> {
        return find(query, Int.MAX_VALUE, 0)
    }

    infix fun findOne(query: Query): DataBlob<Any>? {
        return findOne(query, 0)
    }

    infix fun where(query: Query): DataBlobSource {
        return this.filter(query);
    }
}