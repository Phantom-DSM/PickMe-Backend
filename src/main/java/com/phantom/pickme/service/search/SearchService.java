package com.phantom.pickme.service.search;

import com.phantom.pickme.domain.skill.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SkillRepository skillRepository;

    @Transactional(readOnly = true)
    public List<String> searchSkills(String keyword) {
        return skillRepository.searchSkillNameByKeyword(keyword);
    }
}
