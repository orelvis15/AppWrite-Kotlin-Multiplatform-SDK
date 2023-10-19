package io.appwrite

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp

@Composable
fun output(value: MutableState<Any>) {
    val clipboardManager = LocalClipboardManager.current
    Spacer(Modifier.height(5.dp))
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp).fillMaxSize()) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(1.dp))
            Text("Output", color = Color.White)
            CustomIconButton("icons/copy.svg") {
                clipboardManager.setText(AnnotatedString(value.value.toString()))
            }
        }

        Spacer(Modifier.height(10.dp))
        Text(value.value.toString(), color = Color.White)
    }
}

@Composable
fun CustomButton(title: String, loading: MutableState<Boolean>, onClick: () -> Unit) {
    OutlinedButton(
        onClick = {
            loading.value = true
            onClick()
        },
    ) {
        if (loading.value) {
            CircularProgressIndicator(color = Color(0xffFFDE00), modifier = Modifier.size(15.dp))
        } else {
            Text(title, color = Color.White)
        }
    }
}

@Composable
fun CustomTextField(value: MutableState<String>, placeholder: String) {
    OutlinedTextField(
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.LightGray,
            disabledTextColor = Color.LightGray,
            focusedIndicatorColor = Color.LightGray,
            unfocusedIndicatorColor = Color.LightGray,
            cursorColor = Color.LightGray
        ),
        value = value.value,
        onValueChange = { value.value = it },
        label = { Text(placeholder, color = Color.LightGray) },
        singleLine = true,
        modifier = Modifier
            .padding(end = 5.dp)
    )
}