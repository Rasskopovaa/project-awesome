package ru.bass2000.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    @Override
    public String getBookName() {
        return "Ololo";
    }
}
