package upc.edu.pe.hungerfoodbackend.iservices;

import upc.edu.pe.hungerfoodbackend.entities.FoodDonation;
import upc.edu.pe.hungerfoodbackend.entities.User;

import java.util.List;
import java.util.Optional;

public interface IFoodDonationService {
    //save
    public FoodDonation save(FoodDonation foodDonation);

    //delete
    public void delete(Long id);

    //findall
    public List<FoodDonation> findall();

    //update
    public FoodDonation update(FoodDonation foodDonation);


}
