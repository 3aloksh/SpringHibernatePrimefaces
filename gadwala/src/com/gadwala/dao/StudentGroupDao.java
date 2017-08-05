package com.gadwala.dao;

import com.gadwala.model.StudentGroup;

public interface StudentGroupDao {
	public int getGroupCapacity(int level, String group);
	public boolean create(StudentGroup studentGroup);
	public boolean delete(int studentId);
	public StudentGroup getByStudentId(int studentId);
}
