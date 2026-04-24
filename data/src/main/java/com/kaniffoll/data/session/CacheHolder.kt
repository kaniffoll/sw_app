package com.kaniffoll.data.session

class CacheHolder {
    var cache: CacheState = CacheState.IDLE
        private set

    fun reset() {
        cache = CacheState.IDLE
    }

    fun setCache(url: String?) {
        cache = if (url == null) CacheState.END else CacheState.NEXT(url)
    }
}