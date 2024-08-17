package com.ikimaka.mvidecompose.presentation

import com.ikimaka.mvidecompose.data.RepositoryImpl
import com.ikimaka.mvidecompose.domain.Contact
import com.ikimaka.mvidecompose.domain.EditContactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultEditContactComponent(
    private val contact: Contact
): EditContactComponent {

    private val repository = RepositoryImpl
    private val editContactUseCase = EditContactUseCase(repository)


    private val _model = MutableStateFlow(
        EditContactComponent.Model(username = contact.username, phone = contact.phone)
    )

    override val model: StateFlow<EditContactComponent.Model>
        get() = _model.asStateFlow()

    override fun onUsernameChanged(username: String) {
        _model.value = model.value.copy(username = username)
    }

    override fun onPhoneChanged(phone: String) {
        _model.value = model.value.copy(phone = phone)
    }

    override fun onSaveContactClicked() {
        val (username, phone) = model.value
        editContactUseCase(
            contact.copy(
                username = username,
                phone = phone
            )
        )
    }
}