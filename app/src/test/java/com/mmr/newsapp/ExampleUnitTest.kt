package com.mmr.newsapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun testDateUtil() {
        assertEquals("Sat, 4 Apr 2020 10:30", DateUtil.prettifyDate("2020-04-04T10:30:11Z"))
    }

}
