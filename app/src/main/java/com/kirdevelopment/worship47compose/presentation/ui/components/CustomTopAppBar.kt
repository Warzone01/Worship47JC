package com.kirdevelopment.worship47compose.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar() {
    TopAppBar(
        title = { Text("App Title") },
        navigationIcon = {
            IconButton(onClick = { /* Handle menu click */ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            // Search Icon
            IconButton(onClick = { /* Handle search click */ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
            // Filter Icon
            IconButton(onClick = { /* Handle filter click */ }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "Filter")
            }
            // Dropdown Menu
            DropdownMenuExample()
        }
    )
}

@Composable
fun DropdownMenuExample() {
    var expanded by remember { mutableStateOf(false) }

    Box(Modifier.wrapContentSize(Alignment.TopStart)) {
        IconButton(onClick = { expanded = true }) {
            Text("Dropdown")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = { /* Handle Item Click */ },
                text = { Text("Item 1") }
            )
            DropdownMenuItem(
                onClick = { /* Handle Item Click */ },
                text = { Text("Item 1") }
            )
            // Add more items here
        }
    }
}