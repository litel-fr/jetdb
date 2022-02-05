package com.ltl.jetdb

import com.ltl.jetdb.domain.DataBlob
import com.ltl.jetdb.domain.dynamic.queries.*
import com.ltl.jetdb.domain.dynamic.queries.equals;
import com.ltl.jetdb.domain.query.impl.*
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class QueryTest {

    val dataBlob = DataBlob(mapOf<String, Any>(Pair("foo", "bar")))

    @Test
    fun equalsTest() {
        "foo" equals "bar" matches dataBlob shouldBeEqualTo true
        "foo" equals "baz" matches dataBlob shouldBeEqualTo false
        "baz" equals "bar" matches dataBlob shouldBeEqualTo false
    }

    @Test
    fun equalsWithRefTest(){
        val france: DataBlob<Any> = countries().find("name" equals "france", 1)[0]
        val frenchCities = cities().find("country" equals ("name" inBlob france))
        for(city in frenchCities){
            city.get("country") shouldBeEqualTo "france"
        }
    }

    @Test
    fun equalsWithRefOnLeftSideTest(){
        val fields = DataBlob(mapOf("name" to "country"))
        val fieldRef = FieldRef<String>("name", fields)
        fieldRef.get() shouldBeEqualTo "country"
        val frenchCities = cities().find( fieldRef equals "france")
        for(city in frenchCities){
            city.get("country") shouldBeEqualTo "france"
        }
    }

    @Test
    fun andTest() {
        assertEquals(true, And(IsTrue(), IsTrue()).match(dataBlob))
        assertEquals(false, And(IsFalse(), IsTrue()).match(dataBlob))
        assertEquals(false, And(IsTrue(), IsFalse()).match(dataBlob))
        assertEquals(false, And(IsFalse(), IsFalse()).match(dataBlob))

        assertEquals(true, IsTrue() and IsTrue() and IsTrue() matches dataBlob)
        assertEquals(false, IsFalse() and IsTrue() and IsTrue() matches dataBlob)
        assertEquals(false, IsTrue() and IsFalse() and IsTrue() matches dataBlob)
        assertEquals(false, IsTrue() and IsTrue() and IsFalse() matches dataBlob)
    }

    @Test
    fun orTest() {
        assertEquals(true, Or(IsTrue(), IsTrue()).match(dataBlob))
        assertEquals(true, Or(IsFalse(), IsTrue()).match(dataBlob))
        assertEquals(true, Or(IsTrue(), IsFalse()).match(dataBlob))
        assertEquals(false, Or(IsFalse(), IsFalse()).match(dataBlob))

        assertEquals(true, IsTrue() or IsTrue() or IsTrue() matches dataBlob)
        assertEquals(true, IsFalse() or IsTrue() or IsTrue() matches dataBlob)
        assertEquals(true, IsTrue() or IsFalse() or IsTrue() matches dataBlob)
        assertEquals(true, IsTrue() or IsTrue() or IsFalse() matches dataBlob)
        assertEquals(false, IsFalse() or IsFalse() or IsFalse() matches dataBlob)
    }

    @Test
    fun notTest() {
        assertEquals(false, Not(IsTrue()).match(dataBlob))
        assertEquals(true, Not(IsFalse()).match(dataBlob))
        assertEquals(false, not(IsTrue()) matches dataBlob)
    }

    @Test
    fun toStringTest() {
        assertEquals("foo = 8000", ("foo" equals 8000).toString())
        assertEquals("(foo = 8000 and bar = 42)", ("foo" equals 8000 and ("bar" equals 42)).toString())
        assertEquals("((foo = 8000 and bar = 42) or baz = 5)", ("foo" equals 8000 and ("bar" equals 42) or ("baz" equals 5)).toString())
        assertEquals("(foo = 8000 and (bar = 42 or baz = 5))", ("foo" equals 8000 and (("bar" equals 42) or ("baz" equals 5))).toString())
    }

}

