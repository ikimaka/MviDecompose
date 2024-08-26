package com.ikimaka.mvidecompose.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.ikimaka.mvidecompose.presentation.AddContactStore.Intent


class AddContactStoreFactory(
    private val storeFactory: StoreFactory
) {

    private val store: Store<AddContactStore.Intent, AddContactStore.State, AddContactStore.Label> =
        storeFactory.create(
            name = "AddContactStore",
            initialState = AddContactStore.State(username = "", phone = ""),

        )

    private sealed interface Action

    private sealed interface Msg {

        data class ChangeUsername(val username: String): Msg

        data class ChangePhone(val phone: String): Msg
    }

    private object ReducerImpl: Reducer<AddContactStore.State, Msg> {

        override fun AddContactStore.State.reduce(msg: Msg): AddContactStore.State {

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