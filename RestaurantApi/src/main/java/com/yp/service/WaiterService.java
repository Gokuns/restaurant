package com.yp.service;

import com.yp.converter.MediaConverter;
import com.yp.converter.WaiterConverter;
import com.yp.dto.WaiterDto;
import com.yp.entity.Waiter;
import com.yp.repos.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WaiterService {
    @Autowired
    private WaiterRepository waiterRepository;


    public List<WaiterDto> getAllWaiters(){
        List<Waiter> waiters = waiterRepository.findAll();
        List<WaiterDto> waiterDtos = new ArrayList<>();
        waiters.forEach(waiter -> {
            WaiterDto waiterDto = WaiterConverter.convertToWaiterDto(waiter);
            waiterDtos.add(waiterDto);
        });
        return waiterDtos;
    }

    public WaiterDto getWaiter(Long id) {
        Waiter waiter = waiterRepository.findById(id).orElse(null);
        return WaiterConverter.convertToWaiterDto(waiter);
    }

    public Waiter addWaiter(WaiterDto waiterDto) {
        Waiter waiter = WaiterConverter.convertToWaiter(waiterDto);
        return waiterRepository.save(waiter);
    }

    public Waiter updateWaiter(Long id, WaiterDto waiterDto) {
        Waiter waiter = waiterRepository.findById(id).orElse(null);
        Waiter newWaiter = WaiterConverter.convertToWaiter(waiterDto);
        waiter.setName(newWaiter.getName());
        waiter.setSurname(newWaiter.getSurname());
        waiter.setDateOfBirth(newWaiter.getDateOfBirth());
        waiter.setPhone(waiterDto.getPhone());
        waiter.setMail(waiterDto.getMail());
        waiter.setMedia(MediaConverter.convertToMedia(waiterDto.getMedia()));
        return waiterRepository.save(waiter);

    }
    public void deleteWaiter(Long id) {
        waiterRepository.deleteById(id);
    }




}
