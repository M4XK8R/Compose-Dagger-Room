package com.maxkor.compose_dagger_room

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {

    val list = mainViewModel.listOfSomething
        .collectAsState(initial = emptyList())

    ConstraintLayout(Modifier.background(Color.Yellow)) {

        val (
            lazyColumn,
            fab
        ) = createRefs()

        LazyColumn(
            Modifier
                .background(Color.LightGray)
                .constrainAs(lazyColumn) {
                    linkTo(
                        parent.start, parent.top, parent.end, parent.bottom,
                        8.dp, 8.dp, 8.dp, 8.dp,
                    )
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        ) {
            items(list.value) {
                ListItem(
                    headlineContent = {
                        Text(text = it.id.toString(), fontSize = 24.sp)
                        Text(text = it.title, fontSize = 24.sp)
                        Text(text = it.description, fontSize = 24.sp)
                    },
                    modifier = Modifier
                        .border(6.dp, Color.Gray)
                        .clickable { mainViewModel.delete(it) }
                )
            }

        }

        FloatingActionButton(
            onClick = {
                mainViewModel.insert()
            },
            modifier = Modifier.constrainAs(fab) {
                end.linkTo(parent.end, 32.dp)
                bottom.linkTo(parent.bottom, 32.dp)
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_plus_one_24),
                contentDescription = null
            )
        }
    }
}