package com.ikimaka.mvidecompose.presentation

import android.os.Parcelable
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize

interface AddContactComponent {

    val model: StateFlow<AddContactStore.State>

    fun onUsernameChanged(username: String)

    fun onPhoneChanged(phone: String)

    fun onSaveContactClicked()

}