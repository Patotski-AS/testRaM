package com.example.android.testram.domain.utill

interface Mapper<in E, out T> {
    fun map(from: E): T
    fun mapList(from: List<E>): List<T> = from.mapTo(mutableListOf(), ::map)
}
