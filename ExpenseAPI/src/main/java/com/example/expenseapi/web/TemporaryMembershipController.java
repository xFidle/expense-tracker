package com.example.expenseapi.web;

import com.example.expenseapi.dto.InvitationDTO;
import com.example.expenseapi.pojo.Membership;
import com.example.expenseapi.pojo.TemporaryMembership;
import com.example.expenseapi.pojo.User;
import com.example.expenseapi.service.MembershipService;
import com.example.expenseapi.service.TemporaryMembershipService;
import com.example.expenseapi.utils.AuthHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tempMembership")
@Tag(name="Temporary Membership Controller", description = "Controller to accept or decline invitations")
public class TemporaryMembershipController extends GenericController<TemporaryMembership, Long> {
    private final MembershipService membershipService;
    private final TemporaryMembershipService temporaryMembershipService;
    public TemporaryMembershipController(TemporaryMembershipService service, MembershipService membershipService) {
        super(service);
        this.membershipService = membershipService;
        this.temporaryMembershipService = service;
    }

    @PostMapping("/accept/{id}")
    @Operation(summary = "Accept a membership invitation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invitation successfully accepted.",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request. Invalid membership ID or unauthorized action.",
                    content = @Content)
    })
    public ResponseEntity<HttpStatus> acceptInvitation(@PathVariable Long id) {
        User user = AuthHelper.getUser();
        TemporaryMembership temporaryMembership = temporaryMembershipService.get(id);

        if (temporaryMembership.getUser().getId().equals(user.getId())) {
            Membership newMembership = new Membership(temporaryMembership);
            temporaryMembershipService.delete(id);
            membershipService.save(newMembership);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/decline/{id}")
    @Operation(summary = "Decline a membership invitation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invitation successfully declined.",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request. Invalid membership ID or unauthorized action.",
                    content = @Content)
    })
    public ResponseEntity<HttpStatus> declineInvitation(@PathVariable Long id) {
        User user = AuthHelper.getUser();
        TemporaryMembership temporaryMembership = temporaryMembershipService.get(id);

        if (temporaryMembership.getUser().getId().equals(user.getId())) {
            temporaryMembershipService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/invitations")
    @Operation(summary = "Get all pending membership invitations")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of invitations.",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TemporaryMembership.class))))
    public ResponseEntity<List<InvitationDTO>> getInvitations() {
        User user = AuthHelper.getUser();
        return new ResponseEntity<>(temporaryMembershipService.getByUserId(user.getId()), HttpStatus.OK);
    }

    @GetMapping("/sentInvitations")
    @Operation(summary = "Retrieves invitations sent by logged user")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of invitations.",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TemporaryMembership.class))))
    public ResponseEntity<List<InvitationDTO>> getSentInvitations() {
        return new ResponseEntity<>(temporaryMembershipService.getBySenderId(AuthHelper.getUser().getId()), HttpStatus.OK);
    }


}
