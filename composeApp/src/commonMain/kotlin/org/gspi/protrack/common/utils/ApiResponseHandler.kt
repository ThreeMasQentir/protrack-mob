package org.gspi.protrack.common.utils

import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import org.gspi.protrack.common.model.BaseResponse
import org.gspi.protrack.common.model.Meta
import org.gspi.protrack.common.networkevent.ApiResponseStatus
import org.gspi.protrack.common.networkevent.UiState

fun <T> handleApiResponse(
    result: Result<BaseResponse<T>>, stateFlow: MutableStateFlow<UiState<BaseResponse<T>>>
) {
    result.onSuccess { response ->
        val status = ApiResponseStatus.fromCode(response.meta.code)
        when {
            status is ApiResponseStatus.Success && response.data != null -> {
                stateFlow.value = UiState.Success(response)
            }

            status is ApiResponseStatus.Success && response.data == null -> {
                stateFlow.value = UiState.Error(response.meta.message)
            }

            else -> {
                stateFlow.value = UiState.Error(response.meta.message)
            }
        }
    }.onFailure { exception ->
        stateFlow.value = UiState.Error(exception.message ?: "Unknown error")
    }
}

suspend fun <T> handleApiResponse(
    apiCall: suspend () -> Result<BaseResponse<T>>,
    onSuccess: (T) -> Unit,
    onError: (String) -> Unit
) {
    try {
        val result = apiCall()
        result.onSuccess { baseResponse ->
            if (baseResponse.meta.code == 200) {
                baseResponse.data?.let {
                    onSuccess(it)
                } ?: run {
                    onError("Data is null")
                }
            } else {
                onError(baseResponse.meta.message.orEmpty())
            }
        }.onFailure {
            onError(it.message ?: "Unexpected error occurred")
        }
    } catch (e: SocketTimeoutException) {
        onError("Request timed out")
    } catch (e: IOException) {
        onError("Network error occurred")
    } catch (e: Exception) {
        onError(e.message ?: "Unexpected error")
    }
}

suspend fun <T> handleApiResponseMeta(
    apiCall: suspend () -> Result<BaseResponse<T>>, onSuccess: (T) -> Unit, onError: (Meta) -> Unit
) {
    try {
        val result = apiCall()
        result.onSuccess { baseResponse ->
            if (baseResponse.meta.code == 200) {
                baseResponse.data?.let {
                    onSuccess(it)
                } ?: run {
                    onError(baseResponse.meta)
                }
            } else {
                onError(baseResponse.meta)
            }
        }.onFailure {
            onError(Meta(message = it.message.orEmpty()))
        }
    } catch (e: Exception) {
        onError(Meta(message = e.message.orEmpty()))
    }
}
