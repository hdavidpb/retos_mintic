/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domingo_Reto3.Reto3;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service
public class ServiciosBike {
     @Autowired
    private RepositorioBike metodosCrud;

    public List<Bike> getAll(){
        return metodosCrud.getAll();
    }

    public Optional<Bike> getOrtopedic(int bikeId) {
        return metodosCrud.getBike(bikeId);
    }

    public Bike save(Bike ortopedic){
        if(ortopedic.getId()==null){
            return metodosCrud.save(ortopedic);
        }else{
            Optional<Bike> e=metodosCrud.getBike(ortopedic.getId());
            if(e.isEmpty()){
                return metodosCrud.save(ortopedic);
            }else{
                return ortopedic;
            }
        }
    }

    public Bike update(Bike ortopedic){
        if(ortopedic.getId()!=null){
            Optional<Bike> e=metodosCrud.getBike(ortopedic.getId());
            if(!e.isEmpty()){
                if(ortopedic.getName()!=null){
                    e.get().setName(ortopedic.getName());
                }
                if(ortopedic.getBrand()!=null){
                    e.get().setBrand(ortopedic.getBrand());
                }
                if(ortopedic.getYear()!=null){
                    e.get().setYear(ortopedic.getYear());
                }
                if(ortopedic.getDescription()!=null){
                    e.get().setDescription(ortopedic.getDescription());
                }
                if(ortopedic.getCategory()!=null){
                    e.get().setCategory(ortopedic.getCategory());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return ortopedic;
            }
        }else{
            return ortopedic;
        }
    }


    public boolean deleteOrtopedic(int bikeId) {
        Boolean aBoolean = getOrtopedic(bikeId).map(bike -> {
            metodosCrud.delete(bike);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
