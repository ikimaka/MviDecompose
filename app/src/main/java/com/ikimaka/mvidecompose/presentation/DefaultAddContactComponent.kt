package com.ikimaka.mvidecompose.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.statekeeper.consume
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.ikimaka.mvidecompose.core.componentScope
import com.ikimaka.mvidecompose.data.RepositoryImpl
import com.ikimaka.mvidecompose.domain.AddContactUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DefaultAddContactComponent(
    componentContext: ComponentContext,
    val onContactSaved: () -> Unit
): AddContactComponent, ComponentContext by componentContext {

    private lateinit var store: AddContactStore

    init {
        componentScope().launch {
            store.labels.collect {
                when (it) {
                    is AddContactStore.Label.ContactSaved -> {
                        onContactSaved()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<AddContactStore.State>
        get() = store.stateFlow

    override fun onUsernameChanged(username: String) {
        store.accept(AddContactStore.Intent.ChangeUsername(username))
    }

    override fun onPhoneChanged(phone: String) {
        store.accept(AddContactStore.Intent.ChangePhone(phone))
    }

    override fun onSaveContactClicked() {
        store.accept(AddContactStore.Intent.SaveContact)
    }
}