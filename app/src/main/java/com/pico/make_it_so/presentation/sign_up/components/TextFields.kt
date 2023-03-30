package com.pico.make_it_so.presentation.sign_up.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.pico.make_it_so.R
import com.pico.make_it_so.ui.theme.MakeItSoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = ""
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
        placeholder = { Text(text = placeholder) },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
) {
    var isPasswordVisible by remember {
        mutableStateOf(true)
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = null)
        },
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = isPasswordVisible.not() }) {
                Icon(
                    painter = painterResource(id = if (isPasswordVisible) R.drawable.visibility_off_fill0_wght400_grad0_opsz24 else R.drawable.visibility_fill0_wght400_grad0_opsz24),
                    contentDescription = null
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = modifier
    )
}

@Preview(showSystemUi = true)
@Composable
fun TextFieldsPreview() {
    MakeItSoTheme() {
        Column() {
            PasswordTextField(
                value = "Password",
                onValueChange = {},
                placeholder = "Type new Password"
            )
            EmailTextField(value = "Email", onValueChange = {}, placeholder = "Email")
        }
    }
}