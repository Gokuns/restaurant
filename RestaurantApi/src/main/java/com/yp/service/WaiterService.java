package com.yp.service;

import com.yp.dto.WaiterDto;
import com.yp.entity.Waiter;
import com.yp.exception.BusinessRuleException;
import com.yp.exception.ContentNotFoundException;
import com.yp.mapper.MediaMapper;
import com.yp.mapper.WaiterMapper;
import com.yp.repos.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WaiterService {
    @Autowired
    private WaiterRepository waiterRepository;
    @Autowired
    private WaiterMapper waiterMapper;
    @Autowired
    private MediaMapper mediaMapper;
    @Autowired
    private MessageSource messageSource;
    private static final  String BUSINESS_RULE_EXCEPTION = "BusinessRuleException";
    private static final String CONTENT_NOT_FOUND = "ContentNotFound";




    public List<WaiterDto> getAllWaiters(){
        return waiterRepository.findAll().stream().map(waiterMapper::toDto).collect(Collectors.toList());
    }

    public WaiterDto getWaiter(Long id, String lang) {
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        Optional<Waiter> optionalWaiter = waiterRepository.findById(id);
        if(optionalWaiter.isEmpty()){
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
        return waiterMapper.toDto(optionalWaiter.get());
    }

    public Waiter addWaiter(WaiterDto waiterDto, String lang) {
        if(waiterDto==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        Waiter waiter = waiterMapper.toEntity(waiterDto);
        return waiterRepository.save(waiter);
    }

    public Waiter updateWaiter(Long id, WaiterDto waiterDto, String lang) {
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        if(waiterDto==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }

        Optional<Waiter> optionalWaiter = waiterRepository.findById(id);
        if(optionalWaiter.isEmpty()){
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
        Waiter waiter = optionalWaiter.get();
        Waiter newWaiter = waiterMapper.toEntity(waiterDto);
        if(!waiter.getName().equals(newWaiter.getName())){
            waiter.setName(newWaiter.getName());
        }
        if(!waiter.getWaiterId().equals(newWaiter.getWaiterId())){
            waiter.setWaiterId(newWaiter.getWaiterId());
        }
        if(!waiter.getSurname().equals(newWaiter.getSurname())){
            waiter.setSurname(newWaiter.getSurname());
        }
        if(!waiter.getDateOfBirth().equals(newWaiter.getDateOfBirth())){
            waiter.setDateOfBirth(newWaiter.getDateOfBirth());
        }
        if(!waiter.getPhone().equals(newWaiter.getPhone())){
            waiter.setPhone(waiterDto.getPhone());
        }
        if(!waiter.getMail().equals(newWaiter.getMail())){
            waiter.setMail(newWaiter.getMail());
        }
        if(!waiter.getMedia().equals(newWaiter.getMedia())){
            waiter.setMedia(mediaMapper.toEntity(waiterDto.getMedia()));
        }
        return waiterRepository.save(waiter);

    }
    public void deleteWaiter(Long id, String lang) {
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        waiterRepository.deleteById(id);
    }




}
