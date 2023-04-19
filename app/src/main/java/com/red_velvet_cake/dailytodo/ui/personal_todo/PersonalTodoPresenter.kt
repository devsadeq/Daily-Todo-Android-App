package com.red_velvet_cake.dailytodo.ui.personal_todo

import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl

class PersonalTodoPresenter(private val view: PersonalTodoView) {

    private val todoServiceImpl = TodoServiceImpl()

    fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
    ) {
        todoServiceImpl.updatePersonalTodoStatus(
            todoId,
            newTodoStatus,
            ::onUpdatePersonalTodoStatusFailure
        )
    }

    fun getAllTeamTodos() {
        todoServiceImpl.getAllPersonalTodos(
            ::onGetAllPersonalTodosSuccess,
            ::onGetAllTeamTodosFailure
        )
    }

    private fun onUpdatePersonalTodoStatusFailure(errorMessage: String) {
        view.showTodoUpdateFailMessage(errorMessage)
    }


    private fun onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse: GetAllPersonalTodosResponse) {
        if (getAllPersonalTodosResponse.value.isEmpty()) {
            view.showEmptyTodoListState()
        }
        view.showTodoList(getAllPersonalTodosResponse.value)
    }

    private fun onGetAllTeamTodosFailure(errorMessage: String) {
        view.showLoadTodosFailed()
    }
}