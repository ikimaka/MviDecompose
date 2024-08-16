package com.ikimaka.mvidecompose.data

import com.ikimaka.mvidecompose.domain.Contact
import com.ikimaka.mvidecompose.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

object RepositoryImpl: Repository {

    private val _contactList = mutableMapOf(
        0 to Contact(id = 0, username = "Anisimova Alisa", phone = "+7 (000) 000-00-00"),
        1 to Contact(id = 1, username = "Anisimov Igor", phone = "+7 (903) 406-58-42"),
        2 to Contact(id = 2, username = "Vlasova Olga", phone = "+7 (928) 778-87-50")
    )
    private val contactList: List<Contact>
        get() = _contactList.values.toList()

    private val contactListChangeEvents = MutableSharedFlow<Unit>(replay = 1).apply {
        tryEmit(Unit)
    }

    override val contacts: Flow<List<Contact>> = flow {
        contactListChangeEvents.collect {
            emit(contactList)
        }
    }

    override fun saveContact(contact: Contact) {
        val id = if (contact.id < 0) contactList.size else contact.id
        _contactList[id] = contact.copy(id = id)
        contactListChangeEvents.tryEmit(Unit)
    }

}