package com.myjetpack.jetnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.myjetpack.jetnoteapp.screens.NoteScreen
import com.myjetpack.jetnoteapp.ui.theme.JetNoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                NoteScreen(notes = emptyList(),
                    onAddNote = {},
                    onRemoveNote = {})
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

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    JetNoteAppTheme {
//        NoteScreen()
//    }
//}