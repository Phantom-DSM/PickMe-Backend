package com.phantom.pickme.controller;

import com.phantom.pickme.domain.user.User;
import com.phantom.pickme.dto.profile.ProfileResponseDto;
import com.phantom.pickme.security.JwtTokenUtil;
import com.phantom.pickme.service.user.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ProfileController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ProfileService profileService;

    @GetMapping("/me/profile")
    public User myProfile(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(4);
        return profileService.getMyProfile(jwtTokenUtil.getUserIdFromToken(token));
    }

    @GetMapping("/profile/{userId}")
    public ProfileResponseDto readProfile(@PathVariable("userId") String userId) {
        return profileService.getUserProfile(userId);
    }
}
