package com.pnp.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pnp.model.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Serializable>{
	
	/*@Query(value = "Select u from Meeting m join m.userMeetingSet um join um.user where m.id = :id")
	List<User> findUserFromMeeting(@Param("id") Long id);*/
	
}
