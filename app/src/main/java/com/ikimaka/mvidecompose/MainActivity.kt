package com.ikimaka.mvidecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ikimaka.mvidecompose.domain.Contact
import com.ikimaka.mvidecompose.ui.content.AddContact
import com.ikimaka.mvidecompose.ui.content.Contacts
import com.ikimaka.mvidecompose.ui.content.EditContact
import com.ikimaka.mvidecompose.ui.theme.MviDecomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var screen by remember {
                mutableStateOf<Screen>(Screen.ContactList)
            }

            MviDecomposeTheme {
                when (val currentScreen = screen) {
                    is Screen.AddContact -> {
                        AddContact(
                            onContactSaved = {
                                screen = Screen.ContactList
                            }
                        )
                    }

                    is Screen.ContactList -> {
                        Contacts(
                            onAddContactClick = {
                                screen = Screen.AddContact
                            },
                            onContactClick = {
                                screen = Screen.EditContact(it)
                            }
                        )
                    }

                    is Screen.EditContact -> {
                        EditContact(
                            contact = currentScreen.contact,
                            onContactChanged = {
                                screen = Screen.ContactList
                            }
                        )
                    }
                }
            }
        }
    }
}

sealed class Screen {

    object ContactList : Screen()

    object AddContact : Screen()

    data class EditContact(val contact: Contact) : Screen()
}