package com.ikimaka.mvidecompose.presentation_legacy

import androidx.lifecycle.ViewModel
import com.ikimaka.mvidecompose.data.RepositoryImpl
import com.ikimaka.mvidecompose.domain.AddContactUseCase
import com.ikimaka.mvidecompose.domain.Contact
import com.ikimaka.mvidecompose.domain.EditContactUseCase

class ContactDetailViewModel: ViewModel() {

    private val repository = RepositoryImpl

    private val addContactUseCase = AddContactUseCase(repository)
    private val editContactUseCase = EditContactUseCase(repository)

    fun addContact(username: String, phone: String) {
        addContactUseCase(username, phone)
    }

    fun editContact(contact: Contact) {
        editContactUseCase(contact)
    }
}