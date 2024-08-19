package com.ikimaka.mvidecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.defaultComponentContext
import com.ikimaka.mvidecompose.domain.Contact
import com.ikimaka.mvidecompose.presentation.DefaultRootComponent
import com.ikimaka.mvidecompose.ui.content.AddContact
import com.ikimaka.mvidecompose.ui.content.Contacts
import com.ikimaka.mvidecompose.ui.content.EditContact
import com.ikimaka.mvidecompose.ui.content.RootContent
import com.ikimaka.mvidecompose.ui.theme.MviDecomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = DefaultRootComponent(componentContext = defaultComponentContext())

        setContent {
            RootContent(component = root)
        }
    }
}