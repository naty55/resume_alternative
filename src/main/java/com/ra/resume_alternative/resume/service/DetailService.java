package com.ra.resume_alternative.resume.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.ra.resume_alternative.resume.entity.DetailType;
import com.ra.resume_alternative.resume.entity.ResumeDetail;
import com.ra.resume_alternative.error.RequestedEntityNotFoundException;
import com.ra.resume_alternative.resume.repository.DetailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetailService {

    @Autowired
    DetailRepository detailRepository;

    public ResumeDetail addDetail(Long userId, String value, DetailType detailType) {
        ResumeDetail detail = new ResumeDetail(value, detailType);
        detail.setUserId(userId);
        return addDetail(detail);
    }

    public ResumeDetail addDetail(ResumeDetail detail) {
        Optional<ResumeDetail> det = detailRepository.findByDetailTypeAndValueAndUserId(detail.getDetailType(), detail.getValue(), detail.getUserId());
        if(det.isPresent()) {
            return det.get();
        }
        return detailRepository.save(detail);
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

    public Set<ResumeDetail> updateOrCreate(Set<ResumeDetail> details, Long userId) {
        if (details == null) {
            return null;
        }
        Set<ResumeDetail> persistedDetails = new HashSet<>();
        for (ResumeDetail detail : details) {
            detail.setUserId(userId);
            ResumeDetail r = null;
            if(detail.getDetailId() != null) {
                r = updateDetail(userId, detail.getDetailId(), Optional.of(detail.getValue()));
            } else {
                r = addDetail(detail);
            }
            persistedDetails.add(r);
        }
        return persistedDetails;
    }
    
    @Transactional
    public boolean deleteDetail(Long userId, Long detailId) {
        ResumeDetail detail = detailRepository.findById(detailId).orElseThrow(RequestedEntityNotFoundException::new);
        if(detail.getUserId().equals(userId)) {
            detailRepository.deleteAllDetailResumeConnections(detailId);
            detailRepository.deleteByDetailIdAndUserId(detailId, userId);   
        } else {
            throw new RequestedEntityNotFoundException();
        }
        return true;
    }
}
