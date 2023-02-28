package com.ticketaka.reservation.controller;

import com.ticketaka.reservation.dto.StatusCode;
import com.ticketaka.reservation.dto.request.RsvMemberDTO;
import com.ticketaka.reservation.dto.request.ReservationDTO;
import com.ticketaka.reservation.dto.response.BaseResponse;
import com.ticketaka.reservation.dto.response.ReservationListDTO;
import com.ticketaka.reservation.service.MemberService;
import com.ticketaka.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final MemberService memberService;

    @PostMapping("/create/member")
    public BaseResponse createMember(@RequestBody RsvMemberDTO dto) {
        memberService.createMember(dto);
        return new BaseResponse(StatusCode.OK);
    }


    @PostMapping("/create")
    public BaseResponse reservation(@RequestBody ReservationDTO dto) {
//        emailService.sendMail(dto);
        reservationService.reservation(dto);
        return new BaseResponse(StatusCode.OK);
    }

    @GetMapping("/lists/{member_id}")
    public BaseResponse reservationList(
            @PathVariable("member_id") Long memberId) {
        List<ReservationListDTO> data = reservationService.getReservationList(memberId);
        return new BaseResponse(StatusCode.OK, data);
    }

    @GetMapping("/list/{rsv_id}")
    public BaseResponse reservationInfo(
            @PathVariable("rsv_id") Long reservationId) {
        ReservationListDTO data = reservationService.getReservation(reservationId);
        return new BaseResponse(StatusCode.OK, data);
    }

    @DeleteMapping("/delete/{rsv_id}")
    public BaseResponse deleteReservation(
            @PathVariable("rsv_id") Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return new BaseResponse(StatusCode.OK);
    }
}
