package com.example.demo.repositories

import com.example.demo.dto.GetUsersDTO
import com.example.demo.entities.User
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.criteria.CriteriaBuilder
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

    override fun findUsers(getUsersDTO: GetUsersDTO): List<User> {
        val builder: CriteriaBuilder = entityManager.criteriaBuilder
        val query = builder.createQuery(User::class.java)
        val entity = query.from(User::class.java)
        val conditions = mutableListOf<Predicate>()

        for (filter in getUsersDTO::class.memberProperties) {
            if (filter.getter.call(getUsersDTO) !== null) {
                val predicate = buildPredicate(builder, entity, filter.name, filter.getter.call(getUsersDTO))
                conditions.add(predicate)
            }
        }
        query.where(*conditions.toTypedArray())
        return entityManager.createQuery(query).resultList
    }

//    override fun findUsers(getUsersDTO: GetUsersDTO, pageable: Pageable): Page<User> {
//        val builder: CriteriaBuilder = entityManager.criteriaBuilder
//        val query = builder.createQuery(User::class.java)
//        val entity = query.from(User::class.java)
//        val conditions = mutableListOf<Predicate>()
//
//        for (filter in getUsersDTO::class.memberProperties) {
//            if (filter.getter.call(getUsersDTO) !== null) {
//                val predicate = buildPredicate(builder, entity, filter.name, filter.getter.call(getUsersDTO))
//                conditions.add(predicate)
//            }
//        }
//
//        // Adding conditions to the query
//        if (conditions.isNotEmpty()) {
//            query.where(*conditions.toTypedArray())
//        }
//
//        // Using Spring Data JPA's pagination
////        val pageable = PageRequest.of(page - 1, pageSize)
//        val resultList = entityManager.createQuery(query)
//            .setFirstResult((page - 1) * pageSize)
//            .setMaxResults(pageSize)
//            .resultList
//
//        // Returning the paginated results as a Page<User>
//        return PageImpl(resultList, pageable, resultList.size.toLong())
//    }
}