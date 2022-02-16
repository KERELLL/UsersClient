package com.example.usersclient

sealed class UIStateUsers<out S: Any?, out E: Any?> {
    data class Success<out S: Any?>(val data: S) : UIStateUsers<S, Nothing>()
    data class Error<out E: Any?>(val data: E) : UIStateUsers<Nothing, E>()
}