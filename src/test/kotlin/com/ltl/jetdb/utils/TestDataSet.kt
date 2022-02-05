package com.ltl.jetdb

import com.ltl.jetdb.domain.DataBlob
import com.ltl.jetdb.domain.sources.impl.MemListSource

fun cities(): MemListSource {
    return MemListSource(
        "city",
        listOf(
            DataBlob(mapOf("name" to "berlin", "country" to "germany")),
            DataBlob(mapOf("name" to "madrid", "country" to "spain")),
            DataBlob(mapOf("name" to "paris", "country" to "france")),
            DataBlob(mapOf("name" to "toulouse", "country" to "france")),
            DataBlob(mapOf("name" to "montargis", "country" to "france")),
        )
    )
}

fun countries(): MemListSource {
    return MemListSource(
        "country",
        listOf(
            DataBlob(mapOf("name" to "france", "zone" to "EU")),
            DataBlob(mapOf("name" to "germany", "zone" to "EU")),
            DataBlob(mapOf("name" to "spain", "zone" to "EU")),
            DataBlob(mapOf("name" to "usa", "zone" to "North america")),
        )
    )
}
