package com.red_velvet_cake.dailytodo.presenter.get_all_team_todo

import com.red_velvet_cake.dailytodo.data.model.UpdatePersonalStatusResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import com.red_velvet_cake.dailytodo.presenter.update_personal_status.UpdatePersonalStatusView
import java.io.IOException

class GetAllTeamTodoPresenter(private val view: UpdatePersonalStatusView) {

    private val todoServiceImpl = TodoServiceImpl()

    fun updatePersonalTodoStatus(
        todoId: String,
        newTodoStatus: Int,
    ) {
        todoServiceImpl.updatePersonalTodoStatus(
            todoId,
            newTodoStatus,
            ::onUpdatePersonalTodoStatusSuccess,
            ::onUpdatePersonalTodoStatusFailure
        )
    }

    private fun onUpdatePersonalTodoStatusSuccess(updatePersonalStatusResponse: UpdatePersonalStatusResponse) {
        view.onUpdatePersonalTodoStatusSuccess(updatePersonalStatusResponse)
    }

    private fun onUpdatePersonalTodoStatusFailure(exception: IOException) {
        view.onUpdatePersonalTodoStatusFailure(exception)
    }
}

