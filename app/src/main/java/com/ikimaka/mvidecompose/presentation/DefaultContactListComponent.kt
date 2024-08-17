package com.ikimaka.mvidecompose.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackHandler
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.ikimaka.mvidecompose.core.componentScope
import com.ikimaka.mvidecompose.data.RepositoryImpl
import com.ikimaka.mvidecompose.domain.Contact
import com.ikimaka.mvidecompose.domain.GetContactsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DefaultContactListComponent(
    componentContext: ComponentContext,
    val onEditingContactRequested: (Contact) -> Unit,
    val onAddContactRequested: () -> Unit
): ContactListComponent, ComponentContext by componentContext {

    private val repository = RepositoryImpl
    private val getContactsUseCase = GetContactsUseCase(repository)
    private val coroutineScope = componentScope()


    override val model: StateFlow<ContactListComponent.Model> = getContactsUseCase()
        .map { ContactListComponent.Model(it) }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = ContactListComponent.Model(listOf())
        )


    override fun onContactClicked(contact: Contact) {
        onEditingContactRequested(contact)
    }

    override fun onAddContactClicked() {
        onAddContactRequested()
    }

}