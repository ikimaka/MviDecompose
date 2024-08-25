package com.ikimaka.mvidecompose.presentation

import com.ikimaka.mvidecompose.domain.Contact
import kotlinx.coroutines.flow.StateFlow

interface ContactListComponent {

    val model: StateFlow<ContactListStore.State>

    fun onContactClicked(contact: Contact)

    fun onAddContactClicked()


}