package com.web.todo.update;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateService {

    private final UpdateRepository updateRepository;

    public UpdateService(UpdateRepository updateRepository) {
        this.updateRepository = updateRepository;
    }


}
