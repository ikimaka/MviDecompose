package com.ikimaka.mvidecompose.presentation

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.ikimaka.mvidecompose.data.RepositoryImpl
import com.ikimaka.mvidecompose.domain.Contact
import com.ikimaka.mvidecompose.domain.EditContactUseCase


class EditContactStoreFactory {

    private val storeFactory: StoreFactory = LoggingStoreFactory(DefaultStoreFactory())
    private val repository = RepositoryImpl
    private val editContactUseCase: EditContactUseCase = EditContactUseCase(repository)

    fun create(contact: Contact): EditContactStore = object : EditContactStore, Store<EditContactStore.Intent,
            EditContactStore.State, EditContactStore.Label> by storeFactory.create(
        name = "EditContactStore",
        initialState = EditContactStore.State(
            id = contact.id,
            username = contact.username,
            phone = contact.phone),
        reducer = ReducerImpl,
        executorFactory = { ExecutorImpl() }
    ) {}.apply {
        Log.d("STORE_FACTORY", "CREATED EditContactStore")
    }

    private sealed interface Action

    private sealed interface Msg {

        data class ChangeUsername(val username: String): Msg

        data class ChangePhone(val phone: String): Msg
    }

    private inner class ExecutorImpl: CoroutineExecutor<EditContactStore.Intent, Action,
            EditContactStore.State, Msg, EditContactStore.Label>() {

        override fun executeIntent(
            intent: EditContactStore.Intent,
            getState: () -> EditContactStore.State
        ) {
            when (intent) {
                is EditContactStore.Intent.ChangePhone -> {
                    dispatch(Msg.ChangePhone(phone = intent.phone))
                }
                is EditContactStore.Intent.ChangeUsername -> {
                    dispatch(Msg.ChangeUsername(username = intent.username))
                }
                EditContactStore.Intent.SaveContact -> {
                    val state = getState()
                    val contact = Contact(
                        id = state.id,
                        username = state.username,
                        phone = state.phone
                    )
                    editContactUseCase(contact)
                    publish(EditContactStore.Label.ContactSaved)
                }
            }
        }
    }

    private object ReducerImpl: Reducer<EditContactStore.State, Msg> {

        override fun EditContactStore.State.reduce(msg: Msg): EditContactStore.State {

            return when (msg) {
                is Msg.ChangePhone -> {
                    copy(phone = msg.phone)
                }
                is Msg.ChangeUsername -> {
                    copy(username = msg.username)
                }
            }
        }

    }
}