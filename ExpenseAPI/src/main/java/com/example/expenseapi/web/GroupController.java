package com.example.expenseapi.web;

import com.example.expenseapi.pojo.BaseGroup;
import com.example.expenseapi.pojo.Group;
import com.example.expenseapi.service.MembershipService;
import com.example.expenseapi.service.UserGroupService;
import com.example.expenseapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController extends GenericController<Group, Long>{
    private final MembershipService membershipService;

    public GroupController(MembershipService membershipService, UserGroupService userGroupService) {
        super(userGroupService);
        this.membershipService = membershipService;
    }

    @GetMapping("/members/{group}")
    public ResponseEntity<List<com.example.expenseapi.pojo.User>> getMembers(@PathVariable String group){
        return new ResponseEntity<>(membershipService.findUsers(group), HttpStatus.OK);
    }

    @GetMapping("/admins/{group}")
    public ResponseEntity<List<com.example.expenseapi.pojo.User>> getAdmins(@PathVariable String group){
        return new ResponseEntity<>(membershipService.findAdmins(group), HttpStatus.OK);
    }
}
