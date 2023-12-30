package com.example.githubrepoapp.constants

class Constant {

    object GenericConstant{
        const val ERROR_UNKNOWN = "Unknown error"
        const val INVALID_STATE_EVENT = "Invalid state event"
    }
    object DataConstant {
        const val TIME_OUT_CODE = 100
        const val NO_CONNECTION_CODE = 101
        const val DATABASE_NAME: String = "github_db"

    }

    object NetworkConstant {
        const val REPOSITORIES = "repositories"
        const val GET_REPO_DETAILS = "/repos/{owner}/{repo}"
        const val ISSUES = "$GET_REPO_DETAILS/issues"

    }

    object NetworkParamConstant {
        const val OWNER = "owner"
        const val REPO = "repo"
    }
}