package com.phyo.myschool.repository;

import com.phyo.myschool.model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface HolidaysRepository extends CrudRepository<Holiday,String> {

}
