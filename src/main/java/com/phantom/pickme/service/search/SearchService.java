package com.phantom.pickme.service.search;

import com.phantom.pickme.domain.major.MajorRepository;
import com.phantom.pickme.domain.skill.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private MajorRepository majorRepository;

    @Transactional(readOnly = true)
    public List<String> searchSkills(String keyword) {
        return skillRepository.searchSkillNameByKeyword(keyword);
    }

    @Transactional(readOnly = true)
    public List<String> searchMajors(String keyword) {
        return majorRepository.searchMajorNameByKeyword(keyword);
    }
}
