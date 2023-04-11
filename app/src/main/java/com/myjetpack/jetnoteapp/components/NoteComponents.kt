package com.myjetpack.jetnoteapp.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) { //Unit={} means don't require to pass an argument
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(value = text, onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
        maxLines = maxLine,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ), keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }), modifier = modifier
    )

}

@Composable
fun NoteButton(modifier: Modifier=Modifier,
text: String,
onClick: ()-> Unit,
enabled: Boolean=true){
    Button(onClick = { onClick.invoke()},
    shape = CircleShape,
    enabled = enabled,
    modifier = modifier) {
        //we can write onclick=onClick also or we can use it by .invoke function
        Text(text)
    }
}