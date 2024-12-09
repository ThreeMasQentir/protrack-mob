package org.gspi.protrack

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform