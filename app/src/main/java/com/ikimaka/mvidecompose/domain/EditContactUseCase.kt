package com.ikimaka.mvidecompose.domain

class EditContactUseCase(private val repository: Repository) {

    operator fun invoke(contact: Contact) {
        repository.saveContact(contact)
    }

}