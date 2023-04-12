package com.red_velvet_cake.dailytodo.presenter

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.data.model.AllTeamTodos
import okio.IOException

class MainPresenter{
    private val todoServiceImpl = TodoServiceImpl()
    lateinit var iMainView: IMainView
    fun responseTeamTodo() {
        todoServiceImpl.getAllTeamTodos(
            ::onGetAllTeamTodosSuccess,
            ::onGetAllTeamTodosFailure
        )
    }
    private fun onGetAllTeamTodosSuccess(allTeamTodos: AllTeamTodos)
    {
        iMainView.showTeamToDoDesc(allTeamTodos)
    }
    private fun onGetAllTeamTodosFailure(exception: IOException)
    {
        iMainView.showException(exception)
    }
}
