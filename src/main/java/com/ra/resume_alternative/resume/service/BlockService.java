package com.ra.resume_alternative.resume.service;

import com.ra.resume_alternative.resume.entity.ResumeBlock;
import com.ra.resume_alternative.resume.error.RequestedEntityNotFoundException;
import com.ra.resume_alternative.resume.repository.BlockRepository;
import com.ra.resume_alternative.resume.repository.ResumeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlockService {

    @Autowired
    BlockRepository blockRepository;

    @Autowired
    ResumeRepository resumeRepository;

    @Transactional
    public ResumeBlock addBlock(Long userId, Long resumeId, int blockOrder, String blockName) {
        if(resumeRepository.isExistByREsumeIdAndUserId(resumeId, userId) != 1) {
            return null;
        }
        ResumeBlock resumeBlock = new ResumeBlock();
        resumeBlock.setBlockName(blockName);
        resumeBlock.setBlockOrder(blockOrder);
        resumeBlock = blockRepository.save(resumeBlock);
        blockRepository.setResumeIdToBlock(resumeBlock.getBlockId(), resumeId);
        return resumeBlock;
    }

    public ResumeBlock getBlockById(Long userId, Long blockId) {
        return blockRepository.findBlockByIdAndResumeIdAndUserId(blockId, userId).orElseThrow(RequestedEntityNotFoundException::new);
    }

    public boolean deleteBlock(Long userId, Long blockId) {
        ResumeBlock b = getBlockById(userId, blockId);
        blockRepository.delete(b);
        return true;
    }
}
