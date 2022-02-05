package com.ltl.jetdb.domain.sources.impl

import com.ltl.jetdb.domain.DataBlob
import com.ltl.jetdb.domain.query.Query
import com.ltl.jetdb.domain.sources.DataBlobSource

class MemListSource(
    name: String,
    val data: List<DataBlob<Any>>? = null,
) : DataBlobSource(name) {

    override fun find(query: Query, limit: Int?, skip: Int?): List<DataBlob<Any>> {
        val result = mutableListOf<DataBlob<Any>>()
        var skipped = 0;
        for(blob in data ?: listOf()){
            if(query.match(blob)){
                if(skipped != skip){
                    skipped++;
                    continue;
                }
                result.add(blob)
                if(result.size == limit) break
            }
        }
        return result;
    }

    override fun filter(query: Query, limit: Int?, skip: Int?): DataBlobSource {
        return MemListSource(name, this.find(query, limit, skip))
    }

}