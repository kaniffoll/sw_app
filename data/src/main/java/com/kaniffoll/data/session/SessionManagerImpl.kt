package com.kaniffoll.data.session

import com.kaniffoll.domain.session.SessionManager

class SessionManagerImpl(private val cacheHolder: CacheHolder) : SessionManager {
    override fun resetSession() {
        cacheHolder.reset()
    }
}