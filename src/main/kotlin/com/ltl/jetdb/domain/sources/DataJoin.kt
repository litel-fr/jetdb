package com.ltl.jetdb.domain.sources

import com.ltl.jetdb.domain.DataBlob
import com.ltl.jetdb.domain.query.Query
import com.ltl.jetdb.domain.sources.impl.MemListSource

class DataJoin(
    val left: DataBlobSource,
    val right: DataBlobSource,
    val query: Query? = null
) {

    infix fun on(query: Query): DataBlobSource {
        val blobs = mutableListOf<DataBlob<Any>>();
        for(leftBlob in left.list()){
            for(rightBlob in right.list()){
                val highOPriorityBlob: DataBlob<Any> = rightBlob.copyWithPrefix(right.name + ".") as DataBlob<Any>
                val element = leftBlob.copyWithPrefix(left.name+".").mergeWith(highOPriorityBlob);
                if(query!= null && query.match(element)){
                    blobs.add(element)
                }
            }
        }
        return MemListSource("${left.name.decapitalize()}+${right.name.capitalize()}", blobs)
    }

}

infix fun DataBlobSource.join(otherSource: DataBlobSource): DataJoin {
    return DataJoin(this, otherSource)
}

