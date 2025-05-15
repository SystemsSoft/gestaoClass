package org.gestao.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun <T> dialogClassFilter(
    list: SnapshotStateList<T>,
    getItemName: (T) -> String,
    getItemCode: (T) -> String,
    selectedClassCode: MutableState<String?>
) {
    if (showDialogClassFilter.collectAsState().value) {
        Dialog(onDismissRequest = { showDialogClassFilter.value = false }) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Filtrar por:")

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(modifier = Modifier.heightIn(max = 300.dp)) {
                        items(list) { item ->
                            Text(
                                text = getItemName(item),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedClassCode.value = getItemCode(item)
                                        showDialogClassFilter.value = false
                                    }.padding(8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            selectedClassCode.value = null
                            showDialogClassFilter.value = false
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Limpar filtro")
                    }
                }
            }
        }
    }
}