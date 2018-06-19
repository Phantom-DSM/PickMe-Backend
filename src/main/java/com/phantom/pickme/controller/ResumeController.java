package com.phantom.pickme.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.phantom.pickme.domain.user.User;
import com.phantom.pickme.dto.resume.PostCertificateRequestDto;
import com.phantom.pickme.dto.resume.PostMajorRequestDto;
import com.phantom.pickme.dto.resume.PostSkillRequestDto;
import com.phantom.pickme.dto.view.View;
import com.phantom.pickme.security.JwtTokenUtil;
import com.phantom.pickme.service.resume.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public void addSkill(HttpServletRequest request, @RequestBody PostSkillRequestDto dto) {
        String token = request.getHeader(tokenHeader).substring(4);
        resumeService.addSkill(jwtTokenUtil.getUserIdFromToken(token), dto);
    }

    @DeleteMapping("/me/resume/skills/{skillName}")
    public void deleteSkill(HttpServletRequest request, @PathVariable("skillName") String majorName) {
        String token = request.getHeader(tokenHeader).substring(4);
        resumeService.deleteSkill(jwtTokenUtil.getUserIdFromToken(token), majorName);
    }

    @PostMapping("/me/resume/major")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMajor(HttpServletRequest request, @RequestBody PostMajorRequestDto dto) {
        String token = request.getHeader(tokenHeader).substring(4);
        resumeService.addMajor(jwtTokenUtil.getUserIdFromToken(token), dto);
    }

    @DeleteMapping("/me/resume/majors/{majorName}")
    public void deleteMajor(HttpServletRequest request, @PathVariable("majorName") String majorName) {
        String token = request.getHeader(tokenHeader).substring(4);
        resumeService.deleteMajor(jwtTokenUtil.getUserIdFromToken(token), majorName);
    }

    @PostMapping("/me/resume/certificate")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCertificate(HttpServletRequest request, @RequestBody PostCertificateRequestDto dto) {
        String token = request.getHeader(tokenHeader).substring(4);
        resumeService.addCertificate(jwtTokenUtil.getUserIdFromToken(token), dto);
    }

    @DeleteMapping("/me/resume/certificates/{certificateId}")
    public void deleteMajor(HttpServletRequest request, @PathVariable("certificateId") Long certificateId) {
        String token = request.getHeader(tokenHeader).substring(4);
        resumeService.deleteCertificate(jwtTokenUtil.getUserIdFromToken(token), certificateId);
    }
}
