package com.donkeytronkilla.myapplication.model

data class Info(val pages: Int)

data class ApiResponse(val info: Info, val results: List<Character>)
