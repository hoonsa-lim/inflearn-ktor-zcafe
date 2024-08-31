package com.example.domain

import com.example.shared.CafeMenuCategory
import com.example.shared.CafeOrderStatus
import com.example.shared.CafeUserRole
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

/**
 * 테이블 명 앞에 프리픽스 추가한 이유
 * - h2 db에서 먼저 선점하고 있는 테이블명과 내가 만드려는 테이블 명이 동일할 때 잘 안되는 현상이 있다고 함.
 */

object CafeMenuTable : LongIdTable(name = "cafe_menu") {        //long 타입 id pk 로 사용하겠다.
    val name = varchar("menu_name", length = 50)
    val price = integer("price")
    val category = enumerationByName("category", 10, CafeMenuCategory::class)       //byName으로 사용하지 않으면 기본 값은 숫자로 쓰지만, 변경 가능성이 다분하기 때문에 네임으로 사용
    val image = text("image")
}

object CafeUserTable : LongIdTable(name = "cafe_user") {
    val nickname = varchar("nickname", length = 50)
    val password = varchar("password", length = 100)
    val roles = enumList("roles", CafeUserRole::class.java, 20)         //enum 타입이 두개 저장하고 싶을 때를 위해서 converter 사용하여 custom
}

object CafeOrderTable : LongIdTable(name = "cafe_order") {
    val orderCode = varchar("order_code", length = 50)
    val cafeUserId = reference("cafe_user_id", CafeUserTable)                       //reference 외래키 참조
    val cafeMenuId = reference("cafe_menu_id", CafeMenuTable)
    val price = integer("price")
    val status = enumerationByName("status", 10, CafeOrderStatus::class)
    val orderedAt = datetime("ordered_at")      //디펜던시 java-time exposed 사용
}