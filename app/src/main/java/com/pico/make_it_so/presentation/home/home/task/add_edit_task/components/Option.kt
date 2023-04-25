package com.pico.make_it_so.presentation.home.home.task.add_edit_task.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pico.make_it_so.ui.theme.MakeItSoTheme

@Composable
fun IconButtonOption(
    label: String,
    supportingText: String,
    icon: ImageVector,
    onButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, modifier = Modifier.weight(1f))
        Text(
            text = supportingText,
            style = LocalTextStyle.current.merge(MaterialTheme.typography.labelMedium)
        )
        IconButton(onClick = onButtonClicked, modifier = Modifier.size(24.dp)) {
            Icon(imageVector = icon, contentDescription = null)
        }
    }
}

@Composable
fun TextFieldOption(
    label: String,
    value: String,
    placeholder: String,
    supportingText: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, modifier = Modifier.weight(1f))
        Text(
            text = supportingText,
            style = LocalTextStyle.current.merge(MaterialTheme.typography.labelMedium)
        )
        TextField(
            modifier = Modifier.wrapContentWidth(),
            value = value,
            placeholder = { Text(text = placeholder) },
            supportingText = { Text(text = supportingText) },
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun OptionPreview() {
    MakeItSoTheme() {
        IconButtonOption(
            label = "Date",
            supportingText = "2023/04/04",
            icon = Icons.Default.Star,
            onButtonClicked = { /*TODO*/ })
    }
}