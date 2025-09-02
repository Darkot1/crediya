package com.pragma.api.helper;

import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class ValidationHelper {

    private final Validator validator;

    public ValidationHelper(Validator validator) {
        this.validator = validator;
    }

    public <T> void validField(T dto) {
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dto, dto.getClass().getName());
        validator.validate(dto, errors);
        if (errors.hasErrors()) {
            throw new WebExchangeBindException(null, errors);
        }
    }

    public <T> Mono<T> validFieldReactive(T dto) {
        return Mono.fromCallable(() -> {
            validField(dto);
            return dto;
        }).subscribeOn(Schedulers.boundedElastic());
    }
}