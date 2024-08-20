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
import com.ikimaka.mvidecompose.ui.theme.MviDecomposeTheme

@Composable
fun RootContent(
    component: DefaultRootComponent
) {
    MviDecomposeTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Children(
                stack = component.stack
            ) {
                when (val instance = it.instance) {

                    is DefaultRootComponent.Child.AddContact -> {
                        AddContact(component = instance.component)
                    }
                    is DefaultRootComponent.Child.ContactList -> {
                        Contacts(component = instance.component)
                    }
                    is DefaultRootComponent.Child.EditContact -> {
                        EditContact(component = instance.component)
                    }
                }
            }
        }
    }
}