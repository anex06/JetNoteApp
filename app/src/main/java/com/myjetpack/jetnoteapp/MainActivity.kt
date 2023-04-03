package com.myjetpack.jetnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.myjetpack.jetnoteapp.data.NotesDataSource
import com.myjetpack.jetnoteapp.model.Note
import com.myjetpack.jetnoteapp.screens.NoteScreen
import com.myjetpack.jetnoteapp.ui.theme.JetNoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                val notes = remember {
                    mutableListOf<Note>()
                }

                NoteScreen(notes = notes,
                    onAddNote = {
                        notes.add(it)
                    },
                    onRemoveNote = {
                        notes.remove(it)
                    })
            }
        }
    }

}

@Composable
fun MyApp(content: @Composable ()->Unit){
    JetNoteAppTheme {
        content()
    }
}
