package com.phyo.myschool.service;


import com.phyo.myschool.config.EazySchoolProps;
import com.phyo.myschool.constants.PhyoSchoolConstants;
import com.phyo.myschool.model.Contact;
import com.phyo.myschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EazySchoolProps eazySchoolProps;

    public boolean saveMessageDetails(Contact contact){

        boolean isSaved = false;
        contact.setStatus(PhyoSchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if(null != savedContact && savedContact.getContactId()>0){
            isSaved = true;
        }
        return isSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){
        int pageSize = eazySchoolProps.getPageSize();
        if(null!=eazySchoolProps.getContact() && null!=eazySchoolProps.getContact().get("pageSize")){
            pageSize = Integer.parseInt(eazySchoolProps.getContact().get("pageSize").trim());
        }
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findOpenMsgs(
                PhyoSchoolConstants.OPEN,pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int id) {
        boolean isUpdated = false;
        int rows = contactRepository.updateMsgStatus(PhyoSchoolConstants.CLOSE, id);
        if(rows > 0) {
            isUpdated = true ;
        }
        return isUpdated;
    }
}
