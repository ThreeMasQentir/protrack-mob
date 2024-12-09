package org.gspi.protrack.common.networkevent

sealed class ApiResponseStatus(val code: Int, val message: String) {
    object Success : ApiResponseStatus(200, "Operation successful")
    object UserNotFound : ApiResponseStatus(404, "User not found")
    object InvalidToken : ApiResponseStatus(401, "Invalid token")
    object BadRequest : ApiResponseStatus(400, "Bad request")
    object Unauthorized : ApiResponseStatus(403, "Unauthorized")
    object UnknownError : ApiResponseStatus(-1, "Unknown error")

    companion object {
        fun fromCode(code: Int): ApiResponseStatus {
            return when (code) {
                200 -> Success
                404 -> UserNotFound
                401 -> InvalidToken
                400 -> BadRequest
                403 -> Unauthorized
                else -> UnknownError
            }
        }
    }
}
