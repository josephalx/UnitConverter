package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter(modifier = Modifier)
                }
            }
        }
    }
}

fun Convert(input:String,
            conversionFactor: Float,
            outputFactor:Float,
            outputUnit: String):String{
    var output:Float = input.toFloatOrNull() ?: 0.0F
    output *= 100.0F * conversionFactor * outputFactor
    output /= 100.0F
    return "$output $outputUnit"
}
val customStyle = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 32.sp,
    color = Color.Red
)


@Composable
fun UnitConverter(modifier: Modifier) {
    var inputValue by remember {
        mutableStateOf("")
    }
    var outputValue by remember {
        mutableStateOf("")
    }
    var dropDown1Expanded by remember { mutableStateOf(false) }
    var dropDown2Expanded by remember {
        mutableStateOf(false)
    }
    var inputUnit by remember {
        mutableStateOf("")
    }
    var outputUnit by remember {
        mutableStateOf("")
    }
    var conversionFactor by remember {
        mutableFloatStateOf(0.0F)
    }
    var outputFactor by remember {
        mutableFloatStateOf(0.0F)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = "Unit Converter",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                outputValue = Convert(inputValue, conversionFactor, outputFactor, outputUnit)
            },
            label = { Text(text = "Enter a value") },
            modifier = Modifier
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box {
                Button(onClick = { dropDown1Expanded = !dropDown1Expanded }) {
                    Text(inputUnit.ifBlank { "Select" })
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "ArrowDown"
                    )
                }
                DropdownMenu(expanded = dropDown1Expanded,
                    onDismissRequest = { dropDown1Expanded = !dropDown1Expanded }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeter") },
                        onClick = {
                            inputUnit = "cm"
                            conversionFactor = 0.01F
                            dropDown1Expanded = !dropDown1Expanded
                            outputValue = Convert(inputValue, conversionFactor, outputFactor, outputUnit)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            inputUnit = "m"
                            conversionFactor = 1.0F
                            dropDown1Expanded = !dropDown1Expanded
                            outputValue = Convert(inputValue, conversionFactor, outputFactor, outputUnit)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            inputUnit = "Feet"
                            conversionFactor = 0.3048F
                            dropDown1Expanded = !dropDown1Expanded
                            outputValue = Convert(inputValue, conversionFactor, outputFactor, outputUnit)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            inputUnit = "mm"
                            conversionFactor = 0.001F
                            dropDown1Expanded = !dropDown1Expanded
                            outputValue = Convert(inputValue, conversionFactor, outputFactor, outputUnit)
                        }
                    )
                }
            }
            Box {
                Button(onClick = { dropDown2Expanded = !dropDown2Expanded }) {
                    Text(outputUnit.ifBlank { "Select" })
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "ArrowDown"
                    )
                }
                DropdownMenu(expanded = dropDown2Expanded,
                    onDismissRequest = { dropDown2Expanded = !dropDown2Expanded }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeter") },
                        onClick = {
                            dropDown2Expanded = !dropDown2Expanded
                            outputUnit = "cm"
                            outputFactor = 100.0F
                            outputValue = Convert(inputValue, conversionFactor, outputFactor, outputUnit)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            dropDown2Expanded = !dropDown2Expanded
                            outputUnit ="m"
                            outputFactor = 1.0F
                            outputValue = Convert(inputValue, conversionFactor, outputFactor, outputUnit)

                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "feet") },
                        onClick = {
                            dropDown2Expanded = !dropDown2Expanded
                            outputUnit = "feet"
                            outputFactor = 3.28F
                            outputValue = Convert(inputValue, conversionFactor, outputFactor, outputUnit)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            dropDown2Expanded = !dropDown2Expanded
                            outputUnit = "mm"
                            outputFactor = 1000.0F
                            outputValue = Convert(inputValue, conversionFactor, outputFactor, outputUnit)
                        }
                    )
                }
            }
        }
        Text(
            text = "Result : $outputValue", textAlign = TextAlign.Start,
            style = customStyle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 10.dp
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverter(modifier = Modifier)
    }
}