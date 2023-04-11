package com.myjetpack.jetnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myjetpack.jetnoteapp.data.NotesDataSource
import com.myjetpack.jetnoteapp.model.Note
import com.myjetpack.jetnoteapp.model.NoteViewModel
import com.myjetpack.jetnoteapp.screens.NoteScreen
import com.myjetpack.jetnoteapp.ui.theme.JetNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint//For Hilt dependency
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNoteAppTheme {
                val noteViewModel: NoteViewModel by viewModels()
                NoteApp(noteViewModel)
            }
        }
    }

}

@Composable
fun NoteApp(noteViewModel: NoteViewModel = viewModel()){
    val noteList = noteViewModel.getAllNotes()
    NoteScreen(notes = noteList,
        onAddNote = {
            noteViewModel.addNote(it)
        },
        onRemoveNote = {
            noteViewModel.removeNote(it)
        })
}


