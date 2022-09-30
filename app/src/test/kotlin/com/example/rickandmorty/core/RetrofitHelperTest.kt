package com.example.rickandmorty.core

import com.example.rickandmorty.core.RetrofitHelper.getRetrofit
import junit.framework.Assert.assertNotNull
import org.junit.Test

class RetrofitTest {

    @Test
    fun getRetrofitShouldReturnNotNull() {
        val retrofit = getRetrofit()
        assertNotNull(retrofit)
    }

}