package com.sqa.g06.n03.WaterBilling.service;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
//    public String authenticate(HttpServletRequest request);

    public boolean checkRole(HttpServletRequest request, String role);

    public boolean checkClientHasRoleAccessResource(HttpServletRequest request, String resourceId);
}
