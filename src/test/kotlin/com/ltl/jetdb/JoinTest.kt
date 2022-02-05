package com.ltl.jetdb

import org.junit.jupiter.api.Test
import com.ltl.jetdb.domain.sources.join
import com.ltl.jetdb.domain.dynamic.queries.*
import org.amshove.kluent.shouldBeEqualTo

class JoinTest {
    @Test
    fun join(){
        val enrichedEuropeanCities =
            countries() alias "country" where ("zone" equals "EU") join (cities() alias "city") on ("city.country" equals ("name" inBlob "country"))
        for(enrichedCity in enrichedEuropeanCities.list()){
            enrichedCity["city.country"] shouldBeEqualTo enrichedCity["country.name"]
            enrichedCity["country.zone"] shouldBeEqualTo "EU"
        }
    }

}