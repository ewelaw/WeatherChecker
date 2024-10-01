package com.ewela.feature.searchlocation.domain.usecase

import junit.framework.TestCase.assertEquals
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import junitparams.naming.TestCaseName
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class IsSearchQueryValidUseCaseTest {

    private val useCase = IsSearchQueryValidUseCase()
    private fun testParameters() = arrayOf(
        arrayOf("Warszawa", true),
        arrayOf("Starogard Gdański", true),
        arrayOf("Warszawa1", false),
        arrayOf("Warszawa&", false),
        arrayOf("$#", false),
        arrayOf("", false),
    )

    @Test
    @Parameters(method = "testParameters")
    @TestCaseName("Given query = {0}, when IsSearchQueryValidUseCase, then should return = {1}")
    fun `Given query = {0}, when IsSearchQueryValidUseCase, then should return = {1}`(
        query: String,
        expectedResult: Boolean
    ) {
        //given
        //when
        val result = useCase(query)
        //then
        assertEquals(expectedResult, result)
    }

}