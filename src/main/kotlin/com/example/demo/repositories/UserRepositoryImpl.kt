package com.example.demo.repositories

import com.example.demo.dto.GetUsersDTO
import com.example.demo.entities.User
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Order
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.stereotype.Component
import kotlin.reflect.full.memberProperties
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

inline fun <reified T> buildPredicate(
    builder: CriteriaBuilder,
    entity: Root<*>,
    propertyName: String,
    filterValue: T
): Predicate {
    val property = entity.get<T>(propertyName)
    return builder.equal(property, filterValue)
}

@Component
class UserRepositoryImpl: UserRepository {
    @PersistenceContext
    protected lateinit var entityManager: EntityManager

    override fun findUsers(pageable: Pageable, getUsersDTO: GetUsersDTO?): Page<User> {
        val builder: CriteriaBuilder = entityManager.criteriaBuilder
        val query = builder.createQuery(User::class.java)
        val entity = query.from(User::class.java)
        val conditions = mutableListOf<Predicate>()

        if (getUsersDTO !== null) {
            for (filter in getUsersDTO::class.memberProperties) {
                if (filter.getter.call(getUsersDTO) !== null) {
                    val predicate = buildPredicate(builder, entity, filter.name, filter.getter.call(getUsersDTO))
                    conditions.add(predicate)
                }
            }

            if (conditions.isNotEmpty()) {
                query.where(*conditions.toTypedArray())
            }
        }

        if (pageable.sort.isSorted) {
            val orderList = mutableListOf<Order>()
            pageable.sort.forEach {
                val order = if (it.isAscending) {
                    builder.asc(entity.get<String>(it.property))
                } else {
                    builder.desc(entity.get<String>(it.property))
                }
                orderList.add(order)
            }
            query.orderBy(orderList)
        }

        val resultList = entityManager.createQuery(query)
            .setFirstResult(pageable.offset.toInt())
            .setMaxResults(pageable.pageSize)
            .resultList

        return PageImpl(resultList, pageable, resultList.size.toLong())
    }
}