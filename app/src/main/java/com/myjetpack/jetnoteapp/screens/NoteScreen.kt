package com.myjetpack.jetnoteapp.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    val title = remember {
        mutableStateOf("")
    }
    val description = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.background,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
        }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications,
                contentDescription = "App bar icon")
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
                    onAddNote(
                        Note(
                            title = title.value,
                            description = description.value
                        )
                    )
                    title.value = ""
                    description.value = ""
                    Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                }
            })

        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes) { note ->
                NoteRow(note = note
                ) {
                    onRemoveNote(note)
                }

            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClick: (Note) -> Unit
) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 0.dp, bottomStart = 20.dp))
            .fillMaxWidth(),
        color = Color(color = 0xFFDFE6EB),
        elevation = 6.dp,
    ) {
        Column(
            modifier
                .clickable {
                    onNoteClick(note)
                }
                .padding(
                    horizontal = 14.dp,
                    vertical = 6.dp
                ), horizontalAlignment = Alignment.Start) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Preview
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}