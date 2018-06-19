package com.phantom.pickme.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.phantom.pickme.domain.user.User;
import com.phantom.pickme.dto.resume.PostSkillRequestDto;
import com.phantom.pickme.dto.view.View;
import com.phantom.pickme.security.JwtTokenUtil;
import com.phantom.pickme.service.resume.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/me/resume")
    @JsonView(View.Resume.class)
    public User myResume(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(4);
        return resumeService.getMyProfile(jwtTokenUtil.getUserIdFromToken(token));
    }

    @PostMapping("/me/resume/skill")
    public void addSkill(HttpServletRequest request, @RequestBody PostSkillRequestDto dto) {
        String token = request.getHeader(tokenHeader).substring(4);
        resumeService.addSkill(jwtTokenUtil.getUserIdFromToken(token), dto);
    }
}
