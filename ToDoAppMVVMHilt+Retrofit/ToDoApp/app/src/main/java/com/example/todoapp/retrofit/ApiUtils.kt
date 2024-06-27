package com.example.todoapp.retrofit

class ApiUtils {
    companion object {
        val baseUrl="http://kasimadalan.pe.hu/"

        fun getToDosDao() : ToDosDao{
            return RetrofitClient.getClient(baseUrl).create(ToDosDao::class.java)

        }
    }
}