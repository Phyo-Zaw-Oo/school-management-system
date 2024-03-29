package com.phyo.myschool.repository;

import com.phyo.myschool.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    List<Contact> findContactsByStatus(String status);

    @Query("SELECT c FROM Contact c WHERE c.status = :status")
        //@Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status",nativeQuery = true)
    Page<Contact> findByStatus(String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("Update Contact c Set c.status = ?1 where c.contactId = ?2")
    int updateStatusById(String status, int id);

    Page<Contact> findOpenMsgs(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    int updateMsgStatus(String status, int id);

    @Query(nativeQuery = true)
    Page<Contact> findOpenMsgsNative(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true)
    int updateMsgStatusNative(String status, int id);

}
