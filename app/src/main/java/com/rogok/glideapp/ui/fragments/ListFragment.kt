package com.rogok.glideapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rogok.glideapp.R
import com.rogok.glideapp.databinding.FragmentListBinding
import com.rogok.glideapp.models.User
import com.rogok.glideapp.ui.UserViewModel
import com.rogok.glideapp.ui.adapter.UserPagingDataAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class ListFragment : BaseFragment<FragmentListBinding>(
    R.layout.fragment_list,
    FragmentListBinding::inflate
) {

    val list = ArrayList<User>()
    private val userAdapter by lazy {
        UserPagingDataAdapter()
    }

    //private val repository = get<UserRepository>()
    private val viewModel by viewModel<UserViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        lifecycleScope.launchWhenStarted {
            viewModel.fetchUsers().distinctUntilChanged().collectLatest {
                userAdapter.submitData(it)
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = userAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                /*val deletedItem = */list.removeAt(viewHolder.absoluteAdapterPosition)
                userAdapter.notifyItemRemoved(viewHolder.absoluteAdapterPosition)
            }
        }).attachToRecyclerView(binding.recyclerView)
    }
}