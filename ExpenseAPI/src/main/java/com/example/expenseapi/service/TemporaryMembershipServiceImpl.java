package com.example.expenseapi.service;

import com.example.expenseapi.pojo.TemporaryMembership;
import com.example.expenseapi.repository.TemporaryMembershipRepository;
import org.springframework.stereotype.Service;

@Service
public class TemporaryMembershipServiceImpl extends GenericServiceImpl<TemporaryMembership, Long> implements TemporaryMembershipService {
    TemporaryMembershipRepository temporaryMembershipRepository;
    public TemporaryMembershipServiceImpl(TemporaryMembershipRepository repository) {
        super(repository);
        this.temporaryMembershipRepository = repository;
    }
}
