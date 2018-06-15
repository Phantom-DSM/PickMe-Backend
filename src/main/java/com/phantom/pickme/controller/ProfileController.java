package com.phantom.pickme.controller;

import com.phantom.pickme.domain.user.User;
import com.phantom.pickme.dto.profile.*;
import com.phantom.pickme.security.JwtTokenUtil;
import com.phantom.pickme.service.user.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/me/profile/email/privacy")
    @ResponseStatus(HttpStatus.OK)
    public void toggleEmailPrivacy(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(4);
        profileService.toggleEmailPrivacy(jwtTokenUtil.getUserIdFromToken(token));
    }

    @PatchMapping("/me/profile/phone/privacy")
    @ResponseStatus(HttpStatus.OK)
    public void togglePhonePrivacy(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(4);
        profileService.togglePhonePrivacy(jwtTokenUtil.getUserIdFromToken(token));
    }

    @PatchMapping("/me/profile/birth")
    @ResponseStatus(HttpStatus.OK)
    public void patchBirth(HttpServletRequest request, @RequestBody PatchBirthRequestDto dto) {
        String token = request.getHeader(tokenHeader).substring(4);
        profileService.patchBirth(jwtTokenUtil.getUserIdFromToken(token), dto);
    }

    @PatchMapping("/me/profile/address")
    @ResponseStatus(HttpStatus.OK)
    public void patchAddress(HttpServletRequest request, @RequestBody PatchAddressRequestDto dto) {
        String token = request.getHeader(tokenHeader).substring(4);
        profileService.patchAddress(jwtTokenUtil.getUserIdFromToken(token), dto);
    }

    @PatchMapping("/me/profile/desiredSal")
    @ResponseStatus(HttpStatus.OK)
    public void patchDesiredSal(HttpServletRequest request, @RequestBody PatchDesiredSalRequestDto dto) {
        String token = request.getHeader(tokenHeader).substring(4);
        profileService.patchDesiredSal(jwtTokenUtil.getUserIdFromToken(token), dto);
    }

    @PatchMapping("/me/profile/phone")
    @ResponseStatus(HttpStatus.OK)
    public void patchPhoneNumber(HttpServletRequest request, @RequestBody PatchPhoneNumberRequestDto dto) {
        String token = request.getHeader(tokenHeader).substring(4);
        profileService.patchPhoneNumber(jwtTokenUtil.getUserIdFromToken(token), dto);
    }

    @GetMapping("/users/{userId}/profile")
    public ProfileResponseDto readProfile(@PathVariable("userId") String userId) {
        return profileService.getUserProfile(userId);
    }
}
