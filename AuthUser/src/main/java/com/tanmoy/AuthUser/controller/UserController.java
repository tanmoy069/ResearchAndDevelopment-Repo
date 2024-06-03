package com.tanmoy.AuthUser.controller;

import com.tanmoy.AuthUser.constant.ApiUrlConstant;
import com.tanmoy.AuthUser.request.UserRequest;
import com.tanmoy.AuthUser.response.BaseResponse;
import com.tanmoy.AuthUser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiUrlConstant.USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(ApiUrlConstant.FIND)
    public ResponseEntity<BaseResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.getSuccessResponse(userService.findById(id)));
    }

    @PostMapping(ApiUrlConstant.SAVE)
    public ResponseEntity<BaseResponse> save(@RequestBody UserRequest request) {
        return ResponseEntity.ok(BaseResponse.getSuccessResponse(userService.saveUser(request)));
    }


    @PutMapping(ApiUrlConstant.UPDATE)
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UserRequest request) {
        return ResponseEntity.ok(BaseResponse.getSuccessResponse(userService.updateUser(id, request)));
    }

    @DeleteMapping(ApiUrlConstant.DELETE)
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.ok(BaseResponse.getSuccessResponse());
    }
}

