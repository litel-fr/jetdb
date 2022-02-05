package com.ltl.jetdb

import com.ltl.jetdb.domain.DataBlob
import com.ltl.jetdb.domain.sources.impl.MemListSource
import com.ltl.jetdb.domain.query.impl.Equals
import com.ltl.jetdb.domain.query.impl.Value
import com.ltl.jetdb.domain.dynamic.queries.equals
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MemListSourceTest {


    @Test
    fun findTest(){
        val source1 = MemListSource(
            "src1",
            listOf(
                DataBlob(mapOf("foo" to "bar")),
                DataBlob(mapOf("foo" to "bar")),
                DataBlob(mapOf("foo" to "bar")),
            )
        )
        assertEquals(3, source1.find(Equals("foo", Value.of("bar"))).size)
        assertEquals(2, source1.find(Equals("foo", Value.of("bar")), 2).size)
        assertEquals(1, source1.find(Equals("foo", Value.of("bar")),  999, 2).size)
        assertEquals(0, source1.find(Equals("foo", Value.of("baz"))).size)
        assertEquals(0, source1.find(Equals("bar", Value.of("baz"))).size)
    }

    @Test
    fun filterTest(){
        countries().filter("name" equals "france").list().size shouldBeEqualTo 1
        countries().filter("name" equals "france").list()[0].get("name") shouldBeEqualTo "france"
        (countries() where ("name" equals "france")).list().size shouldBeEqualTo 1
    }

}