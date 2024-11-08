package com.star.galeri_web_app.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.galeri_web_app.dto.DtoAddress;
import com.star.galeri_web_app.dto.DtoGallerist;
import com.star.galeri_web_app.dto.DtoGalleristIU;
import com.star.galeri_web_app.exception.BaseException;
import com.star.galeri_web_app.exception.ErrorMessage;
import com.star.galeri_web_app.exception.MessageType;
import com.star.galeri_web_app.model.Address;
import com.star.galeri_web_app.model.Gallerist;
import com.star.galeri_web_app.repository.AddressRepository;
import com.star.galeri_web_app.repository.GalleristRepository;
import com.star.galeri_web_app.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {

        Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
        if (optAddress.isEmpty()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXİST, dtoGalleristIU.getAddressId().toString()));
        }

        Gallerist gallerist = new Gallerist();

        gallerist.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoGalleristIU, gallerist);
        gallerist.setAddress(optAddress.get());
        return gallerist;
    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {

        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));
        BeanUtils.copyProperties(savedGallerist, dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
        dtoGallerist.setAddress(dtoAddress);

        return dtoGallerist;
    }
}
