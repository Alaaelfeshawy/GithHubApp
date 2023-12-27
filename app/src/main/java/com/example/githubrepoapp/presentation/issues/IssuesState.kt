package com.example.githubrepoapp.presentation.issues
import com.example.githubrepoapp.data.network.models.IssueModel


data class IssuesState  (
     val isLoading :Boolean?=null,
     val errorMessage :String?=null,
     val data : List<IssueModel>?=null
)