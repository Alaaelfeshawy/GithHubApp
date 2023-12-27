package com.example.githubrepoapp.constants

class Constant {

    object DataConstant {

        const val BASE_URL = "BASE_URL"
        const val BUILD_FLAVOR = "BUILD_FLAVOR"
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