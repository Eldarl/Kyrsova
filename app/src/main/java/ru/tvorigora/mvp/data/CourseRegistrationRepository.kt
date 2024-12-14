package ru.tvorigora.mvp.data

class CourseRegistrationRepository(private val courseRegistrationDao: CourseRegistrationDao) {
    suspend fun registerUser(
        courseId: Long,
        courseName: String,
        userId: Int,
        groupName: String,
        childFIO: String
    ) {
        val registration = CourseRegistrationEntity(userId = userId, courseId = courseId,
            groupName = groupName, courseName = courseName, childFIO = childFIO)
        courseRegistrationDao.insert(registration)
    }

    suspend fun getRegistrationsForUser(userId: Int): List<CourseRegistrationEntity> {
        return courseRegistrationDao.getRegistrationsForUser(userId)
    }
}