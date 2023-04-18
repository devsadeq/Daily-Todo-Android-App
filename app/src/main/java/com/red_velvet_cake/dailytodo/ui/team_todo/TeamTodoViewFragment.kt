package com.red_velvet_cake.dailytodo.ui.team_todo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.databinding.FragmentTeamTodoBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment

class TeamTodoViewFragment : BaseFragment<FragmentTeamTodoBinding>(), TeamTodoView {

    private lateinit var teamToDoAdapter: TeamToDoAdapter
    private lateinit var teamTodoPresenter: TeamTodoPresenter
    private var selectedChip = -1

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoBinding =
        FragmentTeamTodoBinding::inflate

    override fun setUp() {
        initializePresenter()
        initializeAdapter()
        selectedChip = CHIP_TODO_VALUE
        teamToDoAdapter.setSelectedChip(selectedChip)
        binding.chipTodo.isChecked = true
        binding.chipDone.setChipBackgroundColorResource(R.color.white)
        binding.chipInProgress.setChipBackgroundColorResource(R.color.white)
    }

    override fun addCallBacks() {
        binding.chipTodo.setOnClickListener {
            selectedChip = CHIP_TODO_VALUE
            refreshTeamTodoList()
            teamToDoAdapter.setSelectedChip(selectedChip)
            binding.chipDone.isChecked = false
            binding.chipInProgress.isChecked = false
            binding.chipDone.setChipBackgroundColorResource(R.color.white)
            binding.chipInProgress.setChipBackgroundColorResource(R.color.white)
            binding.chipTodo.setChipBackgroundColorResource(R.color.chip_background_color)
        }
        binding.chipDone.setOnClickListener {
            selectedChip = CHIP_DONE_VALUE
            refreshTeamTodoList()
            teamToDoAdapter.setSelectedChip(selectedChip)
            binding.chipTodo.isChecked = false
            binding.chipInProgress.isChecked = false
            binding.chipTodo.setChipBackgroundColorResource(R.color.white)
            binding.chipInProgress.setChipBackgroundColorResource(R.color.white)
            binding.chipDone.setChipBackgroundColorResource(R.color.chip_background_color)

        }
        binding.chipInProgress.setOnClickListener {
            selectedChip = CHIP_IN_PROGRESS_VALUE
            refreshTeamTodoList()
            teamToDoAdapter.setSelectedChip(selectedChip)
            binding.chipTodo.isChecked = false
            binding.chipDone.isChecked = false
            binding.chipDone.setChipBackgroundColorResource(R.color.white)
            binding.chipTodo.setChipBackgroundColorResource(R.color.white)
            binding.chipInProgress.setChipBackgroundColorResource(R.color.chip_background_color)

        }
    }

    private fun initializePresenter() {
        teamTodoPresenter = TeamTodoPresenter(this)
        teamTodoPresenter.getAllTeamTodos()
    }


    private fun initializeAdapter() {
        teamToDoAdapter = TeamToDoAdapter(::onUpdateStatus)
    }

    private fun onUpdateStatus(todoId: String, status: Int) {
        teamTodoPresenter.updateTeamTodoStatus(todoId, status)
    }

    override fun showTodoList(todoList: List<TeamTodo>) {
        val itemTouchHelperCallback = ItemTeamTodoTouchHelperCallback(teamToDoAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        val filteredList =
            filterTodosList(selectedChip, todoList)
        requireActivity().runOnUiThread {
            teamToDoAdapter.submitList(filteredList)
            binding.teamTodoRecycler.adapter = teamToDoAdapter
            itemTouchHelper.attachToRecyclerView(binding.teamTodoRecycler)
        }
    }


    private fun refreshTeamTodoList() {
        teamTodoPresenter.getAllTeamTodos()
    }

    private fun filterTodosList(
        selectedChip: Int,
        teamTodoList: List<TeamTodo>
    ): List<TeamTodo> =
        teamTodoList.filter { it.status == selectedChip }


    override fun showLoadingTodoListFailed() {
    }

    override fun showToast(toastMessage: ToastMessage) {
        requireActivity().runOnUiThread {
            when (toastMessage) {
                ToastMessage.TODO_UPDATED_SUCCESSFULLY -> {
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.update_todo_success), Toast.LENGTH_SHORT
                    ).show()
                }

                ToastMessage.TODO_UPDATE_FAILED -> {
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.todo_update_failed), Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

    }

    override fun showupdateTodoStatusFailed(errorMessage: String) {

    }

    override fun showEmptyTodoList() {

    }

    companion object {
        private const val CHIP_TODO_VALUE = 0
        private const val CHIP_IN_PROGRESS_VALUE = 1
        private const val CHIP_DONE_VALUE = 2

    }
}






