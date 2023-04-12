package com.red_velvet_cake.dailytodo.data

import com.red_velvet_cake.dailytodo.data.model.allTeamTodos
import okio.IOException

interface TodoService {
    fun getAllTeamTodos(
        onGetAllTeamTodosSuccess: (allTeamTodos) -> Unit,
        onGetAllTeamTodosFailure: (IOException) -> Unit,
import com.red_velvet_cake.dailytodo.model.UpdatePersonalStatusResponse
import java.io.IOException

interface TodoService {
    fun updatePersonalTodoStatus(
        userId: String,
        newTodoStatus: Int,
        onUpdatePersonalTodoStatusSuccess: (updatePersonalStatusResponse: UpdatePersonalStatusResponse) -> Unit,
        onUpdatePersonalTodoStatusFailure: (e: IOException) -> Unit
    )
}