package com.ewela.feature.searchlocation.domain.usecase

class IsSearchQueryValidUseCase {

    private val regex = "^[a-zA-ZZąćęłńóśźżĄĆĘŁŃÓŚŹŻ ]+$".toRegex()

    operator fun invoke(query: String) = regex.matches(query)
}