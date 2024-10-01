package com.ewela.feature.searchlocation.domain.usecase

class IsSearchQueryValidUseCase {

    private val regex = "^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ ]+$".toRegex()

    operator fun invoke(query: String) = regex.matches(query)
}