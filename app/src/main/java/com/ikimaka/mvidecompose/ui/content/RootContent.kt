package com.ikimaka.mvidecompose.ui.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.ikimaka.mvidecompose.presentation.AddContactComponent
import com.ikimaka.mvidecompose.presentation.ContactListComponent
import com.ikimaka.mvidecompose.presentation.DefaultRootComponent
import com.ikimaka.mvidecompose.presentation.EditContactComponent
import com.ikimaka.mvidecompose.presentation.RootComponent
import com.ikimaka.mvidecompose.ui.theme.MviDecomposeTheme

@Composable
fun RootContent(
    component: RootComponent
) {
    MviDecomposeTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Children(
                stack = component.stack
            ) {
                when (val instance = it.instance) {

                    is RootComponent.Child.AddContact -> {
                        AddContact(component = instance.component)
                    }
                    is RootComponent.Child.ContactList -> {
                        Contacts(component = instance.component)
                    }
                    is RootComponent.Child.EditContact -> {
                        EditContact(component = instance.component)
                    }
                }
            }
        }
    }
}