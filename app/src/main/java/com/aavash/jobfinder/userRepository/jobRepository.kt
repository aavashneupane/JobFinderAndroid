package com.aavash.jobfinder.userRepository

import com.aavash.jobfinder.api.MyApiRequest

class jobRepository : MyApiRequest() {
    private val studentApi = ServiceBuilder.buildService(StudentApi::class.java)
    suspend fun addStudent(student:Student):AddStudentResponse{
        return  apiRequest {
            studentApi.addStudent(ServiceBuilder.token!!,student)
        }
    }
    suspend fun getStudents(): StudentResponse {
        return apiRequest {
            studentApi.getAllStudents(ServiceBuilder.token!!)
        }
    }
    suspend fun deleteStudents(id: String) : DeleteStudentResponse{
        return apiRequest {
            studentApi.deleteStudent(ServiceBuilder.token!!,id)
        }
    }
    suspend fun updateStudents(id: String,student: Student) : UpdateStudentResponse{
        return apiRequest {
            studentApi.updateStudent(ServiceBuilder.token!!,id,student)
        }
    }
    suspend fun uploadImage(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            studentApi.uploadImage(ServiceBuilder.token!!, id, body)
        }
    }
}