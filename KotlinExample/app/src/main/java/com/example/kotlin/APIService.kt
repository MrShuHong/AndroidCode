package com.example.kotlin

import com.example.kotlin.entity.Person
import io.reactivex.Observable

/**
 * File description.
 *
 * @author dsh
 * @date 2019-08-23
 */
interface APIService {

    fun getUserInfo(): Observable<Person>
}