package com.wigroup.composition2.domain.usecase

import com.wigroup.composition2.domain.entity.Question
import com.wigroup.composition2.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(maxSumValue: Int): Question =
        repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)


    companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}