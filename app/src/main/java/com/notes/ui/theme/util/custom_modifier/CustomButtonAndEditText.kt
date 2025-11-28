package com.notes.ui.theme.util.custom_modifier

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.notes.ui.theme.util.custom_typo_color_shape.NotesAppTheme

@Composable
fun CustomEditTextWithLeadAndTrailIcon(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color = NotesAppTheme.colorScheme.orange,
    shape: CornerBasedShape = NotesAppTheme.shapes.rounded_corner_24,
    leadingIcon: Int? = null,
    leadingIconClick: () -> Unit = {},
    trailingIcon: Int? = null,
    trailingIconClick: () -> Unit = {},
    textStyle: TextStyle = NotesAppTheme.typography.text_16_500,
    focusedLabelColor: Color = NotesAppTheme.colorScheme.black,
    unfocusedLabelColor: Color = NotesAppTheme.colorScheme.black,
    focusedTextColor: Color = NotesAppTheme.colorScheme.black,
    unfocusedTextColor: Color = NotesAppTheme.colorScheme.black,
    cursorColor: Color = NotesAppTheme.colorScheme.black,
    imeAction: ImeAction = ImeAction.Next,
    content: @Composable () -> Unit
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor = unfocusedLabelColor,
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            cursorColor = cursorColor
        ),
        textStyle = textStyle,
        placeholder = {
            content()
        },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        ),
        singleLine = true,
        leadingIcon = {
            if (leadingIcon != null) {
                Image(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = "Search",
                    modifier = Modifier.clickable { leadingIconClick() }
                )
            }
        },
        trailingIcon = {
            if (trailingIcon != null) {
                IconButton(onClick = { trailingIconClick() }) {
                    Image(
                        painter = painterResource(id = trailingIcon),
                        contentDescription = "Menu and grid",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = shape
            ),
    )
}

@Composable
fun CustomEditText(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color = NotesAppTheme.colorScheme.orange,
    shape: CornerBasedShape = NotesAppTheme.shapes.rounded_corner_24,
    textStyle: TextStyle = NotesAppTheme.typography.text_16_500,
    focusedLabelColor: Color = NotesAppTheme.colorScheme.black,
    unfocusedLabelColor: Color = NotesAppTheme.colorScheme.black,
    focusedTextColor: Color = NotesAppTheme.colorScheme.black,
    unfocusedTextColor: Color = NotesAppTheme.colorScheme.black,
    cursorColor: Color = NotesAppTheme.colorScheme.black,
    imeAction: ImeAction = ImeAction.Next,
    content: @Composable () -> Unit
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor = unfocusedLabelColor,
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            cursorColor = cursorColor
        ),
        textStyle = textStyle,
        placeholder = {
            content()
        },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        ),
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = shape
            ),
    )
}

@Composable
fun RoundedCustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonText: String? = "Continue",
    buttonColor: Color = NotesAppTheme.colorScheme.orange,
    disableButtonColor: Color = NotesAppTheme.colorScheme.white,
    paddingValues: PaddingValues = PaddingValues(vertical = 8.dp),
    textStyle: TextStyle = NotesAppTheme.typography.text_20_500,
    textColor: Color = NotesAppTheme.colorScheme.black,
    isEnable: Boolean = true
) {
    val containerColor = if (isEnable) buttonColor else disableButtonColor
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
        ),
        enabled = isEnable,
        onClick = { onClick() }
    ) {
        Text(
            modifier = Modifier
                .padding(paddingValues),
            text = buttonText ?: "",
            style = textStyle,
            color = textColor
        )
    }
}