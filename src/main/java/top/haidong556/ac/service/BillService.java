package top.haidong556.ac.service;

import top.haidong556.ac.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    private BillRepository billRepository;
    @Autowired
    private void setBillRepository(){}




}
