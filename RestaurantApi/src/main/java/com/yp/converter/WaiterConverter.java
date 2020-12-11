package com.yp.converter;

import com.yp.dto.WaiterDto;
import com.yp.entity.Media;
import com.yp.entity.Waiter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WaiterConverter {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static Waiter convertToWaiter(WaiterDto waiterDto){
        Waiter waiter = new Waiter();
        waiter.setId(waiterDto.getId());
        waiter.setName(waiterDto.getName());
        waiter.setSurname(waiterDto.getSurname());
        LocalDate date = LocalDate.parse(waiterDto.getDateOfBirth(), formatter);
        waiter.setDateOfBirth(date);
        waiter.setPhone(waiterDto.getPhone());
        waiter.setMail(waiterDto.getMail());
        waiter.setMedia(MediaConverter.convertToMedia(waiterDto.getMedia()));
        return waiter;
    }

    public static WaiterDto convertToWaiterDto(Waiter waiter){
        WaiterDto waiterDto = new WaiterDto();
        waiterDto.setId(waiter.getId());
        waiterDto.setName(waiter.getName());
        waiterDto.setSurname(waiter.getSurname());
        waiterDto.setDateOfBirth(waiter.getDateOfBirth().toString());
        waiterDto.setPhone(waiter.getPhone());
        waiterDto.setMail(waiter.getMail());
        waiterDto.setMedia(MediaConverter.convertToMediaDto(waiter.getMedia()));
        return waiterDto;

    }
}
