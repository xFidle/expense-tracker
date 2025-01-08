package com.example.expenseapi.service;

import com.example.expenseapi.dto.MembershipCreateDTO;
import com.example.expenseapi.pojo.TemporaryMembership;

import java.util.List;

public interface TemporaryMembershipService extends GenericService<TemporaryMembership, Long> {
    List<TemporaryMembership> getByUserId(Long userId);
    TemporaryMembership save(MembershipCreateDTO temporaryMembershipCreateDTO);
}
