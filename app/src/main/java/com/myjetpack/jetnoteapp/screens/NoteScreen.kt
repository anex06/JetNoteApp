package com.myjetpack.jetnoteapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myjetpack.jetnoteapp.R
import com.myjetpack.jetnoteapp.components.NoteButton
import com.myjetpack.jetnoteapp.components.NoteInputText
import com.myjetpack.jetnoteapp.data.NotesDataSource
import com.myjetpack.jetnoteapp.model.Note

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title = remember {
        mutableStateOf("")
    }
    var description = remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.background,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
        }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "App bar icon")
        }, backgroundColor = Color(0xFFDADFE3))

        //Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp
            ), text = title.value,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title.value = it
                })

            NoteInputText(modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp
            ), text = description.value,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description.value = it
                })

            NoteButton(text = "Save", onClick = {
                if (title.value.isNotEmpty() && description.value.isNotEmpty()) {

                }
            })

        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn(){
            items(notes){note->
                Text(text = note.title)

            }
        }
    }
}

@Preview
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}