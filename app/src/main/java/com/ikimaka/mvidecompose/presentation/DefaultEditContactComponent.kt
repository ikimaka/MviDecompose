package com.ikimaka.mvidecompose.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.statekeeper.consume
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.ikimaka.mvidecompose.core.componentScope
import com.ikimaka.mvidecompose.data.RepositoryImpl
import com.ikimaka.mvidecompose.domain.Contact
import com.ikimaka.mvidecompose.domain.EditContactUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DefaultEditContactComponent(
    componentContext: ComponentContext,
    private val contact: Contact,
    private val onContactSaved: () -> Unit
): EditContactComponent, ComponentContext by componentContext {

    private lateinit var store: EditContactStore

    init {
        componentScope().launch {
            store.labels.collect {
                when (it) {
                    EditContactStore.Label.ContactSaved -> {
                        onContactSaved()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<EditContactStore.State>
        get() = store.stateFlow

    override fun onUsernameChanged(username: String) {
        store.accept(EditContactStore.Intent.ChangeUsername(username))
    }

    override fun onPhoneChanged(phone: String) {
        store.accept(EditContactStore.Intent.ChangePhone(phone))
    }

    override fun onSaveContactClicked() {
        store.accept(EditContactStore.Intent.SaveContact)
    }
}