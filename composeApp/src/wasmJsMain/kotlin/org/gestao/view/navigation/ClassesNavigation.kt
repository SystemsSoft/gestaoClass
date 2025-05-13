package org.gestao.view.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow
import org.gestao.view.classes.classRegistrationScreen
import org.gestao.view.classes.classScreen
import org.gestao.view.classes.editSelectedClass
import org.gestao.view.classes.editarClassesScreen
import org.gestao.view.menu.classesScreen

var openClassRegistration = MutableStateFlow(false)
var openEditClass = MutableStateFlow(false)
var openEditClassItem = MutableStateFlow(false)

@Composable
fun classNavigation() {
    AnimatedVisibility (
        visible = classesScreen.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if (classesScreen.value){
           closeAccessScreen()
        }
        openClassRegistration.value = false
        openEditClass.value = false
        classScreen()
    }
}

@Composable
fun classRegistrationNavigation() {
    AnimatedVisibility (
        visible = openClassRegistration.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(openClassRegistration.value) {
            openEditClassItem.value = false
            openEditClass.value = false
        }
        classRegistrationScreen()
    }
}

@Composable
fun editClassNavigation() {
    AnimatedVisibility (
        visible = openEditClass.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(openEditClass.value) {
            openClassRegistration.value = false
            openEditClassItem.value = false
        }

        editarClassesScreen()
    }
}


@Composable
fun editClassItemNavigation() {
    AnimatedVisibility (
        visible = openEditClassItem.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(openEditClassItem.value) {
            openEditClass.value = false
            openClassRegistration.value = false
        }
        editSelectedClass()
    }
}