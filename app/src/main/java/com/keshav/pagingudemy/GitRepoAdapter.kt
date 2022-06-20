package com.keshav.pagingudemy

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class GitRepoAdapter : PagedListAdapter<GitRepo, GitRepoAdapter.RepoViewHolder>(REPO_COMPARATOR) {

    class RepoViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        fun setData(getRepo : GitRepo) {
            view.repoName.text = getRepo.fullName
            view.repoDescription.text = getRepo.description
            view.repoLanguage.text = getRepo.language
            view.repo_forks.text = getRepo.forks.toString()
            view.repo_stars.text = getRepo.stars.toString()
        }
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): GitRepoAdapter.RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: GitRepoAdapter.RepoViewHolder, position: Int) {
        val repo = getItem(position)
        repo?.let {
            holder.setData(repo)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<GitRepo>() {
            override fun areItemsTheSame(oldGitRepo: GitRepo, newGitRepo: GitRepo): Boolean =
                oldGitRepo.fullName == newGitRepo.fullName

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldGitRepo: GitRepo, newGitRepo: GitRepo): Boolean =
                oldGitRepo == newGitRepo
        }
    }
}