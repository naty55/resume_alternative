package com.ra.resume_alternative.resume.service;

import java.util.Optional;

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
    public ResumeBlock addBlock(Long userId, Long resumeId, Optional<Integer> blockOrder, Optional<String> blockName) {
        if(resumeRepository.isExistByREsumeIdAndUserId(resumeId, userId) != 1) {
            throw new RequestedEntityNotFoundException();
        }
        ResumeBlock resumeBlock = new ResumeBlock();
        blockOrder.ifPresent(resumeBlock::setBlockOrder);
        blockName.ifPresent(resumeBlock::setBlockName);
        resumeBlock = blockRepository.save(resumeBlock);
        blockRepository.setResumeIdToBlock(resumeBlock.getBlockId(), resumeId);
        return resumeBlock;
    }

    public ResumeBlock getBlockById(Long userId, Long blockId) {
        return blockRepository.findBlockByIdAndResumeIdAndUserId(blockId, userId).
        orElseThrow(RequestedEntityNotFoundException::new);
    }

    @Transactional
    public boolean deleteBlock(Long userId, Long blockId) {
        checkForBlockByBlockIdAndUserId(userId, blockId);
        blockRepository.deleteById(blockId);
        return true;
    }

    @Transactional
    public ResumeBlock updateBlock(Long blockId, Long userId, Optional<String> blockName, Optional<Integer> blockOrder) {
        checkForBlockByBlockIdAndUserId(userId, blockId);
        blockName.ifPresent(n -> blockRepository.updateBlockNameByBlockId(blockId, n));
        blockOrder.ifPresent(o -> blockRepository.updateBlockOrderByBlockId(blockId, o));
        return getBlockById(userId, blockId);
    }

    public boolean isExistByBlockIdAndUserId(Long userId, Long blockId) {
        return blockRepository.isExistByREsumeIdAndUserId(blockId, userId) == 1;
    }

    public void checkForBlockByBlockIdAndUserId(Long userId, Long blockId) throws RequestedEntityNotFoundException {
        if(!isExistByBlockIdAndUserId(userId, blockId)) {
            throw new RequestedEntityNotFoundException();
        }
    }

}
