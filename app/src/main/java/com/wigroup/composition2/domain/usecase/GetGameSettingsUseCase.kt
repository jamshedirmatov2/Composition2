package com.wigroup.composition2.domain.usecase

import com.wigroup.composition2.domain.entity.GameSettings
import com.wigroup.composition2.domain.entity.Level
import com.wigroup.composition2.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(level: Level): GameSettings =
        repository.getGameSettings(level)
}