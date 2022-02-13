package com.ra.resume_alternative.resume.service;

import java.util.Optional;

import com.ra.resume_alternative.resume.entity.DetailType;
import com.ra.resume_alternative.resume.entity.ResumeDetail;
import com.ra.resume_alternative.resume.error.RequestedEntityNotFoundException;
import com.ra.resume_alternative.resume.repository.DetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailService {

    @Autowired
    DetailRepository detailRepository;

    public ResumeDetail addDetail(Long userId, String value, DetailType detailType) {
        ResumeDetail detail = new ResumeDetail(value, detailType);
        detail.setUserId(userId);
        detail = detailRepository.save(detail);
        return detail;
    }
    
    public ResumeDetail updateDetail(Long userId, Long detailId, Optional<String> value) throws RequestedEntityNotFoundException {
        Optional<ResumeDetail> retrievedDetail = detailRepository.findByDetailIdAndUserId(detailId, userId);
        if(retrievedDetail.isEmpty()) {
            throw new RequestedEntityNotFoundException();
        }
        ResumeDetail detail = retrievedDetail.get();
        value.ifPresent(v -> detail.setValue(v));
        return detailRepository.save(detail);
    }

    public boolean deleteDetail(Long userId, Long detailId) {
        detailRepository.deleteBySkillIdAndUserId(detailId, userId);
        return true;
    }
}