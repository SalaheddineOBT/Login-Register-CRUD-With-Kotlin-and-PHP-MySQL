package com.example.myapplication.model

import com.google.common.truth.Truth.*
import org.junit.*

class RegistrationUtilTest{

    @Test
    fun `Asserting`(){
        val result=RegistrationUtil.ValidateRegistraionInputs(
            "sgenius01",
            "sgenius01",
            "sgenius01",
            "sgenius01"
        )
        assertThat(result).isTrue()

        //assertThat("Hello World !").isEqualTo("Hello World !")
        //assertThat("Hello World !").isEmpty()

    }









}