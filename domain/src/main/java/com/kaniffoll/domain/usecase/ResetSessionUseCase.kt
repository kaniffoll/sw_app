package com.kaniffoll.domain.usecase

import com.kaniffoll.domain.session.SessionManager

class ResetSessionUseCase(private val manager: SessionManager) {
    operator fun invoke() = manager.resetSession()
}