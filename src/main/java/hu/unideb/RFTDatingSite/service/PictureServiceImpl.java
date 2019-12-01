package hu.unideb.RFTDatingSite.service;

import hu.unideb.RFTDatingSite.Model.Picture;
import hu.unideb.RFTDatingSite.Model.User;
import hu.unideb.RFTDatingSite.exception.ResourceNotFoundException;
import hu.unideb.RFTDatingSite.repository.PictureRepository;
import hu.unideb.RFTDatingSite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PictureServiceImpl implements PictureService{

    @Autowired
    private PictureRepository pictureRepository;


    @Override
    public Picture getPictureById(int id) {

        Optional<Picture> pictureDb = pictureRepository.findById(id);
        if (pictureDb.isPresent()) {
            return pictureDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found; ID:" + id);

        }
    }
}
