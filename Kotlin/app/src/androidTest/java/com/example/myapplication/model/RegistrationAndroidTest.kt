package com.example.myapplication.model

import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.*

class RegistrationAndroidTest {

    @Test
    fun Testing(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        var result=RegistrationUtil.toBackend(
            "",
            "",
            "",
            appContext
        )

        assertThat(result).isTrue()

    }

}